package com.patitos.app.patitoshop.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "facturas")
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Pedido pedido;

    @Column
    private String tipo_paquete;

    @Column
    private String tipo_proteccion;

    @Column
    private double precio_total;

    @Column
    private String detalle;


    /* -- id -- */
    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id = id;
    }


    /* -- pedido -- */
    public Pedido getPedido(){
        return pedido;
    }

    public void setPedido(Pedido pedido){
        this.pedido = pedido;
    }


    /* -- tipo_paquete -- */
    public String getPaquete(){
        return tipo_paquete;
    }

    public void setPaquete(String tipo_paquete){
        this.tipo_paquete = tipo_paquete;
    }


    /* -- tipo_proteccion -- */
    public String getProteccion(){
        return tipo_proteccion;
    }

    public void setProteccion(String tipo_proteccion){
        this.tipo_proteccion = tipo_proteccion;
    }


    /* -- total precio -- */
    public double getPrecio(){
        return precio_total;
    }

    public void setPrecio(double precio_total){
        this.precio_total = precio_total;
    }


    /* -- tipo_proteccion -- */
    public String getDetalle(){
        return detalle;
    }

    public void setDetalle(String detalle){
        this.detalle = detalle;
    }
}
