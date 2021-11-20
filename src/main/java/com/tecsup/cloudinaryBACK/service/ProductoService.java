package com.tecsup.cloudinaryBACK.service;

import com.tecsup.cloudinaryBACK.entity.Producto;
import com.tecsup.cloudinaryBACK.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductoService {

	@Autowired
    ProductoRepository productoRepository;

    public List<Producto> list(){
        return productoRepository.findByOrderById();
    }

    public Optional<Producto> getOne(int id){
        return productoRepository.findById(id);
    }

    public void save(Producto producto){
    	productoRepository.save(producto);
    }

    public void delete(int id){
    	productoRepository.deleteById(id);
    }

    public boolean existsById(int id){
        return productoRepository.existsById(id);
    }
}
