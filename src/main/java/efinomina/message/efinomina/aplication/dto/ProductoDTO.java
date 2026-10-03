package efinomina.message.efinomina.aplication.dto;

import java.time.LocalDateTime;

public class ProductoDTO {
    private Integer id;
    private String codigo;
    private String nombre;
    private String descripcion;
    private CategoriaDTO categoria;
    private Integer unidadMedidaId;
    private Integer stockMinimo;
    private Integer stockMaximo;
    private Boolean estado;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ProductoDTO() {}

    public ProductoDTO(Integer id, String codigo, String nombre, String descripcion, CategoriaDTO categoria, Integer unidadMedidaId, Integer stockMinimo, Integer stockMaximo, Boolean estado, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.unidadMedidaId = unidadMedidaId;
        this.stockMinimo = stockMinimo;
        this.stockMaximo = stockMaximo;
        this.estado = estado;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public CategoriaDTO getCategoria() { return categoria; }
    public void setCategoria(CategoriaDTO categoria) { this.categoria = categoria; }
    public Integer getUnidadMedidaId() { return unidadMedidaId; }
    public void setUnidadMedidaId(Integer unidadMedidaId) { this.unidadMedidaId = unidadMedidaId; }
    public Integer getStockMinimo() { return stockMinimo; }
    public void setStockMinimo(Integer stockMinimo) { this.stockMinimo = stockMinimo; }
    public Integer getStockMaximo() { return stockMaximo; }
    public void setStockMaximo(Integer stockMaximo) { this.stockMaximo = stockMaximo; }
    public Boolean getEstado() { return estado; }
    public void setEstado(Boolean estado) { this.estado = estado; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}

