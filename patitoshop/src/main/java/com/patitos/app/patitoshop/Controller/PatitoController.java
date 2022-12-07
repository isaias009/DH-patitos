package com.patitos.app.patitoshop.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.patitos.app.patitoshop.Repository.PatitoRepo;
import com.patitos.app.patitoshop.Model.Option;
import com.patitos.app.patitoshop.Model.Patito;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class PatitoController {
    @Autowired
    private PatitoRepo repo;

    @GetMapping(value = "/patitos")
    public List<Patito> getPatitos(){
        return repo.findAll();
    }

    @GetMapping(value = "/patito/{id}")
    public Patito getPatitos(@PathVariable long id){
        return repo.findById(id).get();
    }

    @GetMapping(value = "/patitos/orderby/cantidad")
    public List<Patito> getPatitosByCantidad(){
        return repo.findPublicDucks();
    }

    @PostMapping(value = "/patitos/create")
    public ResponseEntity<Object> createPatito(@RequestBody Patito patito){
        Map<String, Object> map = new HashMap<String, Object>();
        Patito search = repo.existAdd(patito.getPrecio(), patito.getColor(), patito.getSize());

        if(search != null){
            search.setCantidad(search.getCantidad() + patito.getCantidad());
            repo.save(search);

            map.put("message", "El patito fue agregado");
            map.put("status", HttpStatus.OK);
            return new ResponseEntity<Object>(map, HttpStatus.OK);
        }else{

            if(!Option.colors().contains(patito.getColor())){
                map.put("message", "solo se permiten los colores: " + Option.colors().toString());
                map.put("status", "error");
                return new ResponseEntity<Object>(map, HttpStatus.OK);
            }

            if(!Option.sizes().contains(patito.getSize())){
                map.put("message", "solo se permiten los tamaños: " + Option.sizes().toString());
                map.put("status", "error");
                return new ResponseEntity<Object>(map, HttpStatus.OK);
            }

            patito.setBorrado(false);
            repo.save(patito);
            map.put("message", "El patito fue creado");
            map.put("status", HttpStatus.CREATED);
            return new ResponseEntity<Object>(map, HttpStatus.CREATED);
        }
    }

    @PutMapping(value = "/patitos/update/{id}")
    public ResponseEntity<Object> updatePatito(@PathVariable long id, @RequestBody Patito patito){
        Map<String, Object> map = new HashMap<String, Object>();
        Patito search = repo.findById(id).get();
        if(search != null){
            search.setCantidad(patito.getCantidad());
            search.setPrecio(patito.getPrecio());
            repo.save(search);

            map.put("message", "Datos modificados correctamente");
            map.put("status", HttpStatus.OK);
            return new ResponseEntity<Object>(map, HttpStatus.OK);
        }else{
            map.put("message", "No se pudo modificar los datos del patitos");
            map.put("status", "error");
            return new ResponseEntity<Object>(map, HttpStatus.OK);
        }
    }

    @DeleteMapping(value = "/patitos/delete/{id}")
    public ResponseEntity<Object> deletePatito(@PathVariable long id){
        Map<String, Object> map = new HashMap<String, Object>();
        Patito search = repo.findById(id).get();

        if(search != null){
            search.setBorrado(true);
            repo.save(search);

            map.put("message", "El patito fue eliminado");
            map.put("status", HttpStatus.OK);
            return new ResponseEntity<Object>(map, HttpStatus.OK);
        }else{
            map.put("message", "No se pudo eliminar el patito");
            map.put("status", "error");
            return new ResponseEntity<Object>(map, HttpStatus.OK);
        }
    }
}
