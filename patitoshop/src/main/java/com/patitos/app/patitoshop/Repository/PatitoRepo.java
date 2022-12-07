package com.patitos.app.patitoshop.Repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.patitos.app.patitoshop.Model.Patito;

@Repository
public interface PatitoRepo extends JpaRepository <Patito, Long>{

    @Query(value = "SELECT * FROM patitos WHERE precio = :precio AND color = :color AND size = :size LIMIT 1", nativeQuery = true) 
    Patito existAdd(@Param("precio") double precio, @Param("color") String color, @Param("size") String size);

    @Query(value = "SELECT * FROM patitos WHERE borrado=0 ORDER BY cantidad ASC", nativeQuery = true) 
    List<Patito> findPublicDucks();
}
