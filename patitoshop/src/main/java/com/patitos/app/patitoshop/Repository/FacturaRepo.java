package com.patitos.app.patitoshop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.patitos.app.patitoshop.Model.Factura;


@Repository
public interface FacturaRepo extends JpaRepository <Factura, Long>{
    
}
