package com.patitos.app.patitoshop.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "patitos")
public class Patito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;// La documentacion recomienda que la id sea de tipo long

    @Column
    private String color;

    @Column
    private String size; // Se cambio tamaño por size por el caracter especial

    @Column
    private double precio;

    @Column
    private Integer cantidad;

    @Column
    private boolean borrado;


    /* -- id -- */
    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id = id;
    }


    /* -- color -- */
    public String getColor(){
        return color;
    }

    public void setColor(String color){
        this.color = color;
    }


    /* size - tamaño */
    public String getSize(){
        return size;
    }

    public void setSize(String size){
        this.size = size;
    }


    /* -- precio -- */
    public double getPrecio(){
        return precio;
    }

    public void setPrecio(double precio){
        this.precio = precio;
    }


    /* -- cantidad -- */
    public Integer getCantidad(){
        return cantidad;
    }

    public void setCantidad(Integer cantidad){
        this.cantidad = cantidad;
    }

    /* -- borrado (estado) -- */
    public boolean getBorrado(){
        return borrado;
    }

    public void setBorrado(boolean borrado){
        this.borrado = borrado;
    }

}
