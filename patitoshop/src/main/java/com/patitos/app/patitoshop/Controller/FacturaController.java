package com.patitos.app.patitoshop.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.patitos.app.patitoshop.Model.Factura;
import com.patitos.app.patitoshop.Model.Pedido;
import com.patitos.app.patitoshop.Repository.FacturaRepo;
import com.patitos.app.patitoshop.Repository.PedidoRepo;
import com.patitos.app.patitoshop.Service.Price;
import com.patitos.app.patitoshop.Service.SendResponse;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class FacturaController {
    @Autowired
    private FacturaRepo facturarepo;

    @Autowired
    private PedidoRepo pedidorepo;

    @GetMapping(value = "/facturas")
    public List<Factura> getFacturas(){
        return facturarepo.findAll();
    }

    @PostMapping(value = "/factura/create/{id}")
    public Factura createFactura(@PathVariable long id){
        Factura factura = new Factura();
        Pedido pedido = pedidorepo.findById(id).get();
        if(pedido != null){
            factura.setId(1);
            factura.setPedido(pedido);
            factura.setPaquete(SendResponse.empaquetado(pedido.getPatito().getSize()));
            factura.setProteccion(SendResponse.proteccion(pedido.getPatito().getSize(), pedido.getTipo()));
            factura.setPrecio(Price.get(pedido, factura).getPrecio());
            factura.setDetalle(Price.get(pedido, factura).getDetalles());
            facturarepo.save(factura);
            return factura;
        }else{
            return null;
        }
    }
}
