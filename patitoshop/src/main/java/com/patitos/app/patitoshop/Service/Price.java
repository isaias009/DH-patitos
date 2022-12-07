package com.patitos.app.patitoshop.Service;

import java.util.ArrayList;
import java.util.List;

import com.patitos.app.patitoshop.Model.Factura;
import com.patitos.app.patitoshop.Model.Pedido;

public class Price {

    private List<String> detalles;
    private double precioTotal;

    public Price(){
        detalles = new ArrayList<String>();
        precioTotal = 0;
    }

    public static Price get(Pedido pedido, Factura factura){
        Price price = new Price();

        Integer cantidad = pedido.getCantidad();
        double precioTotal = pedido.getPatito().getPrecio() * cantidad;
        double precioAux = precioTotal;

        price.detalles.add(precioTotal+" costo total de los patitotos");

        precioTotal = price.costoMaterial(precioTotal, factura.getPaquete());
        price.detalles.add("+ "+(precioTotal - precioAux)+" costos del material del paquete");
        precioAux = precioTotal;

        precioTotal = price.costoEnvio(precioTotal, pedido.getDestino());
        price.detalles.add("+ "+(precioTotal - precioAux)+" costos de envio");
        precioAux = precioTotal;

        precioTotal = price.costoTransporteEnvio(precioTotal, pedido.getTipo(), cantidad);
        price.detalles.add("+ "+(precioTotal - precioAux)+" costos por el tipo de transporte");
        precioAux = precioTotal;

        if(cantidad > 100){
            precioTotal = price.descuento(precioTotal, 0.2);
            price.detalles.add("- "+(precioAux - precioTotal)+" descuento por mas de 100 unidades de patitos");
        }

        price.setPrecio(precioTotal);
        price.detalles.add(precioTotal+" precio total");

        return price;
    }

    public String getDetalles(){
        String res = "";
        int index = 0;
        for (String item : detalles) {
            res += item;
            if(index < detalles.size()-1){
                res += ", ";
            }
            index++;
        }
        return res;
    }

    public double getPrecio(){
        return this.precioTotal;
    }

    public void setPrecio(double precio){
        this.precioTotal = precio;
    }


    public double costoMaterial(double precio, String paquete){
        switch (paquete){
            case "madera": precio = agregarCosto(precio, 0.05);
            break;

            case "plastico": precio = agregarCosto(precio, 0.10);
            break;

            case "carton": precio = agregarCosto(precio, 0.01);
            break;
        }
        return precio;
    }

    public double costoEnvio(double precio, String destino){
        destino = destino.toLowerCase();
        switch (destino){
            case "usa": precio = agregarCosto(precio, 0.18);
            break;

            case "bolivia": precio = agregarCosto(precio, 0.13);
            break;

            case "india": precio = agregarCosto(precio, 0.19);
            break;

            default: precio = agregarCosto(precio, 0.15);
            break;
        }
        return precio;
    }

    public double costoTransporteEnvio(double precio, String transporte, Integer cantidad){
        switch (transporte){
            case "Tierra": precio = precio + (10 * cantidad);
            break;

            case "Aire":
                double costo = 30*cantidad;
                if(cantidad > 1000){
                    costo = descuento(costo, 0.15);
                }
                precio += costo;
            break;

            case "Mar": precio += 400;
            break;
        }
        return precio;
    }

    public double descuento(double precio, double porc){
        return precio - precio*porc;
    }

    public double agregarCosto(double precio, double porc){
        return precio + precio*porc;
    }

}
