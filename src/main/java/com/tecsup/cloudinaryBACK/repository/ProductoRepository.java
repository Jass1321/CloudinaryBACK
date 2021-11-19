package com.tecsup.cloudinaryBACK.repository;

import com.tecsup.cloudinaryBACK.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
}
