package com.tecsup.cloudinaryBACK.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tecsup.cloudinaryBACK.entity.Imagen;

@Repository
public interface ImagenRepository  extends JpaRepository<Imagen, Integer> {
    List<Imagen> findByOrderById();
}
