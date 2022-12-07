package com.patitos.app.patitoshop.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.patitos.app.patitoshop.Model.Factura;
import com.patitos.app.patitoshop.Model.Option;
import com.patitos.app.patitoshop.Model.Patito;
import com.patitos.app.patitoshop.Model.Pedido;
import com.patitos.app.patitoshop.Repository.PatitoRepo;
import com.patitos.app.patitoshop.Repository.PedidoRepo;
import com.patitos.app.patitoshop.Service.SendResponse;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class PedidoController {
    @Autowired
    private PedidoRepo pedidorepo;

    @Autowired
    private PatitoRepo patitorepo;

    @GetMapping(value = "/pedidos")
    public List<Pedido> getPatitos(){
        return pedidorepo.findAll();
    }

    @PostMapping(value = "/pedido/add/{id}")
    public String createPedido(@PathVariable long id, @RequestBody Pedido pedido){
        Patito patito = patitorepo.findById(id).get();
        if(patito != null){
            if(!Option.modes().contains(pedido.getTipo())){
                return "Solo se permiten los tipos: " + Option.modes().toString();
            }

            if((pedido.getCantidad() > patito.getCantidad()) || (pedido.getCantidad() <= 0)){
                return "Patitos insufucientes, " + patito.getCantidad() + " patitos disponibles";
            }

            pedido.setPatito(patito);
            patito.setCantidad(patito.getCantidad() - pedido.getCantidad());
            patitorepo.save(patito);
            pedidorepo.save(pedido);
            return "Pedido registrado";
        }else{
            return "El pedido no pudo ser registrado";
        }
    }

    @PostMapping(value = "/pedido/factura/preview/{id}")
    public Factura previewFactura(@PathVariable long id){
        Pedido pedido = pedidorepo.findById(id).get();
        Factura f = new Factura();
        f.setId(1);
        f.setPedido(pedido);
        f.setPaquete(SendResponse.empaquetado(pedido.getPatito().getSize()));
        f.setProteccion(SendResponse.proteccion(pedido.getPatito().getSize(), pedido.getTipo()));
        f.setPrecio(2.3);
        return f;
    }

}
