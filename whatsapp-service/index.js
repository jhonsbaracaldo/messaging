const express = require('express');
const qrcode = require('qrcode-terminal');
const QRCode = require('qrcode');
const { Client, LocalAuth } = require('whatsapp-web.js');

const PORT = process.env.PORT || 3001;

const client = new Client({
  authStrategy: new LocalAuth(),
  puppeteer: {
    executablePath: process.env.PUPPETEER_EXECUTABLE_PATH || undefined,
    headless: true,
    args: [
      '--no-sandbox',
      '--disable-setuid-sandbox',
      '--disable-dev-shm-usage'
    ]
  },
});

let isReady = false;
let lastQr = null;

client.on('qr', (qr) => {
  lastQr = qr;
  console.log('Nuevo QR generado. Visita /qr para escanearlo.');
  qrcode.generate(qr, { small: true });
});

client.on('authenticated', () => {
  console.log('✅ WhatsApp AUTENTICADO correctamente.');
});

client.on('auth_failure', (msg) => {
  console.error('❌ AUTH_FAILURE:', msg);
});

client.on('loading_screen', (percent, message) => {
  console.log(`⏳ WhatsApp cargando ${percent}%: ${message}`);
});

client.on('change_state', (state) => {
  console.log('🔄 Estado WhatsApp:', state);
});

client.on('ready', () => {
  isReady = true;
  lastQr = null;
  console.log('✅ Cliente de WhatsApp CONECTADO Y LISTO.');
});

client.on('disconnected', (reason) => {
  isReady = false;
  console.warn('❌ WhatsApp DESCONECTADO:', reason);
});

client.initialize();

function aChatId(phone) {
  const soloDigitos = String(phone).replace(/\D/g, '');
  return `${soloDigitos}@c.us`;
}

const app = express();
app.use(express.json());
app.use(express.urlencoded({ extended: true }));

// ── QR visual ──────────────────────────────────────────────────────────────
app.get('/qr', async (req, res) => {
  if (isReady) {
    return res.send(`
      <!DOCTYPE html><html><head><title>WhatsApp QR</title>
      <style>body{font-family:sans-serif;text-align:center;padding:2rem}</style></head>
      <body><h2>&#9989; WhatsApp ya está conectado.</h2><p>No necesitas escanear el QR.</p></body></html>
    `);
  }
  if (!lastQr) {
    return res.send(`
      <!DOCTYPE html><html><head><title>WhatsApp QR</title>
      <meta http-equiv="refresh" content="3">
      <style>body{font-family:sans-serif;text-align:center;padding:2rem}</style></head>
      <body><h2>&#9203; Generando QR...</h2><p>Esta página se recarga automáticamente.</p></body></html>
    `);
  }
  try {
    const qrImage = await QRCode.toDataURL(lastQr);
    res.send(`
      <!DOCTYPE html>
      <html>
        <head>
          <title>WhatsApp QR</title>
          <meta http-equiv="refresh" content="30">
          <style>
            body { font-family: sans-serif; text-align: center; padding: 2rem; }
            img { width: 280px; height: 280px; border: 1px solid #ddd; padding: 1rem; border-radius: 8px; }
          </style>
        </head>
        <body>
          <h2>Escanea este QR desde WhatsApp</h2>
          <p>Abre WhatsApp &rarr; <strong>Dispositivos vinculados</strong> &rarr; <strong>Vincular un dispositivo</strong></p>
          <img src="${qrImage}" />
          <p><small>El QR expira en ~20s. Esta página se actualiza cada 30 segundos.</small></p>
        </body>
      </html>
    `);
  } catch (err) {
    res.status(500).send('Error generando el QR.');
  }
});

// ── Listar grupos ──────────────────────────────────────────────────────────
app.get('/groups', async (req, res) => {
  if (!isReady) {
    return res.status(503).json({ error: 'WhatsApp no está listo. Escanea el QR primero.' });
  }
  try {
    const chats = await client.getChats();
    const grupos = chats
      .filter(chat => chat.isGroup)
      .map(chat => ({ id: chat.id._serialized, name: chat.name }));
    res.json(grupos);
  } catch (err) {
    console.error('Error obteniendo grupos:', err);
    res.status(500).json({ error: 'No se pudieron obtener los grupos.' });
  }
});

// ── Envío manual (formulario) ──────────────────────────────────────────────
app.get('/send-manual', (req, res) => {
  const estado = isReady
    ? '<span style="color:green">&#9989; Conectado</span>'
    : '<span style="color:red">&#10060; No conectado &mdash; <a href="/qr">escanea el QR</a> primero</span>';

  res.send(`
    <!DOCTYPE html>
    <html>
      <head>
        <title>Enviar mensaje</title>
        <style>
          body { font-family: sans-serif; padding: 2rem; max-width: 480px; margin: auto; }
          label { display: block; margin-top: 1rem; font-weight: bold; }
          input, textarea, select { width: 100%; padding: 0.4rem; margin-top: 0.3rem; box-sizing: border-box; }
          .hint { font-size: 0.8rem; color: #666; margin-top: 0.2rem; }
          button { margin-top: 1.2rem; padding: 0.6rem 1.5rem; background: #25D366; color: white; border: none; border-radius: 4px; cursor: pointer; font-size: 1rem; }
          #resultado { margin-top: 1rem; padding: 0.8rem; border-radius: 4px; display: none; }
          .ok { background: #d4edda; color: #155724; }
          .err { background: #f8d7da; color: #721c24; }
        </style>
      </head>
      <body>
        <h2>Enviar mensaje de WhatsApp</h2>
        <p>Estado: ${estado}</p>

        <form id="frm">
          <label>Teléfono personal (con código de país)</label>
          <input type="text" name="phone" id="phone" placeholder="573001234567" />
          <p class="hint">Déjalo vacío si solo quieres enviar al grupo.</p>

          <label>Grupo de WhatsApp</label>
          <select name="groupId" id="groupId">
            <option value="">— Sin grupo —</option>
          </select>
          <p class="hint">Se carga automáticamente cuando WhatsApp está conectado.</p>

          <label>Mensaje</label>
          <textarea name="message" rows="4" required placeholder="Hola, tu cita está confirmada..."></textarea>

          <button type="submit">Enviar</button>
        </form>

        <div id="resultado"></div>

        <script>
          // Cargar grupos disponibles
          fetch('/groups')
            .then(r => r.json())
            .then(grupos => {
              const sel = document.getElementById('groupId');
              grupos.forEach(g => {
                const opt = document.createElement('option');
                opt.value = g.id;
                opt.textContent = g.name;
                sel.appendChild(opt);
              });
            })
            .catch(() => {});

          // Envío via fetch para mostrar resultado inline
          document.getElementById('frm').addEventListener('submit', async (e) => {
            e.preventDefault();
            const fd = new FormData(e.target);
            const body = {
              phone: fd.get('phone') || undefined,
              groupId: fd.get('groupId') || undefined,
              message: fd.get('message')
            };
            const res = await fetch('/send-message', {
              method: 'POST',
              headers: { 'Content-Type': 'application/json' },
              body: JSON.stringify(body)
            });
            const data = await res.json();
            const div = document.getElementById('resultado');
            div.style.display = 'block';
            if (res.ok) {
              const r = data.resultados || {};
              div.className = 'ok';
              div.innerHTML = '<strong>Enviado:</strong><br>' +
                (r.personal ? '&#9989; Número personal: ' + r.personal + '<br>' : '') +
                (r.grupo    ? '&#9989; Grupo: ' + r.grupo : '');
            } else {
              div.className = 'err';
              div.textContent = data.error || 'Error al enviar.';
            }
          });
        </script>
      </body>
    </html>
  `);
});

// ── API JSON principal (usada por Spring Boot) ─────────────────────────────
app.post('/send-message', async (req, res) => {
  const { phone, message, groupId } = req.body || {};

  if (!message) {
    return res.status(400).json({ error: 'El campo "message" es obligatorio' });
  }
  if (!phone && !groupId) {
    return res.status(400).json({ error: 'Debes indicar al menos "phone" o "groupId"' });
  }
  if (!isReady) {
    return res.status(503).json({ error: 'El cliente de WhatsApp aun no esta listo. Escanea el QR e intenta de nuevo.' });
  }

  const resultados = {};

  if (phone) {
    try {
      await client.sendMessage(aChatId(phone), message);
      resultados.personal = 'enviado';
    } catch (err) {
      console.error('Error enviando a número personal:', err);
      resultados.personal = 'error';
    }
  }

  if (groupId) {
    try {
      await client.sendMessage(groupId, message);
      resultados.grupo = 'enviado';
    } catch (err) {
      console.error('Error enviando al grupo:', err);
      resultados.grupo = 'error';
    }
  }

  res.status(200).json({ status: 'procesado', resultados });
});

// ── Estado ─────────────────────────────────────────────────────────────────
app.get('/status', (req, res) => {
  res.json({ ready: isReady });
});

app.listen(PORT, () => {
  console.log(`Microservicio de WhatsApp escuchando en el puerto ${PORT}`);
  console.log(`QR visual:     http://localhost:${PORT}/qr`);
  console.log(`Grupos:        http://localhost:${PORT}/groups`);
  console.log(`Envio manual:  http://localhost:${PORT}/send-manual`);
});
