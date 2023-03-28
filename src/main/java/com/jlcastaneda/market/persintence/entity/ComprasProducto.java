package com.jlcastaneda.market.persintence.entity;


import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "compras_productos")
public class ComprasProducto {

    @EmbeddedId
    private comprasProductoPK id;
    private BigDecimal cantidad;
    private BigDecimal total;
    private Boolean estado;

    public comprasProductoPK getId() {
        return id;
    }

    public void setId(comprasProductoPK id) {
        this.id = id;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}
