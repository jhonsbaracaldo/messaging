const express = require('express');
const qrcode = require('qrcode-terminal');
const { Client, LocalAuth } = require('whatsapp-web.js');

const PORT = process.env.PORT || 3001;

const client = new Client({
  authStrategy: new LocalAuth(),
  puppeteer: { args: ['--no-sandbox', '--disable-setuid-sandbox'] },
});

let isReady = false;

client.on('qr', (qr) => {
  console.log('Escanea este codigo QR desde WhatsApp > Dispositivos vinculados:');
  qrcode.generate(qr, { small: true });
});

client.on('ready', () => {
  isReady = true;
  console.log('Cliente de WhatsApp conectado y listo para enviar mensajes.');
});

client.on('disconnected', (reason) => {
  isReady = false;
  console.warn('WhatsApp se desconecto:', reason);
});

client.initialize();

function aChatId(phone) {
  const soloDigitos = String(phone).replace(/\D/g, '');
  return `${soloDigitos}@c.us`;
}

const app = express();
app.use(express.json());

app.post('/send-message', async (req, res) => {
  const { phone, message } = req.body || {};

  if (!phone || !message) {
    return res.status(400).json({ error: 'Los campos "phone" y "message" son obligatorios' });
  }

  if (!isReady) {
    return res.status(503).json({ error: 'El cliente de WhatsApp aun no esta listo. Escanea el QR e intenta de nuevo.' });
  }

  try {
    await client.sendMessage(aChatId(phone), message);
    res.status(200).json({ status: 'enviado' });
  } catch (error) {
    console.error('Error enviando mensaje de WhatsApp:', error);
    res.status(500).json({ error: 'No se pudo enviar el mensaje' });
  }
});

app.get('/status', (req, res) => {
  res.json({ ready: isReady });
});

app.listen(PORT, () => {
  console.log(`Microservicio de WhatsApp escuchando en el puerto ${PORT}`);
});
