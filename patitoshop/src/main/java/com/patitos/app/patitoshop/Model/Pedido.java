package com.patitos.app.patitoshop.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "pedidos")
public class Pedido {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Patito patito;

    @Column
    private Integer cantidad;

    @Column
    private String destino;

    @Column
    private String tipo;


    /* -- id -- */
    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id = id;
    }


    /* -- patito -- */
    public Patito getPatito(){
        return patito;
    }

    public void setPatito(Patito patito){
        this.patito = patito;
    }


    /* -- tipo -- */
    public Integer getCantidad(){
        return cantidad;
    }

    public void setCantidad(Integer cantidad){
        this.cantidad = cantidad;
    }


    /* -- destino -- */
    public String getDestino(){
        return destino;
    }

    public void setDestino(String destino){
        this.destino = destino;
    }


    /* -- tipo -- */
    public String getTipo(){
        return tipo;
    }

    public void setTipo(String tipo){
        this.tipo = tipo;
    }
}
