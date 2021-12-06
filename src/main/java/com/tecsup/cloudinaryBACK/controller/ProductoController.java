package com.tecsup.cloudinaryBACK.controller;

import com.tecsup.cloudinaryBACK.dto.Mensaje;
import com.tecsup.cloudinaryBACK.dto.ProductoDto;
import com.tecsup.cloudinaryBACK.entity.Producto;
import com.tecsup.cloudinaryBACK.service.CloudinaryService;
import com.tecsup.cloudinaryBACK.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

@RestController
@RequestMapping("/producto")
@CrossOrigin
public class ProductoController {
	
    @Autowired
    ProductoService productoService;

    @GetMapping("/list")
    public ResponseEntity<List<Producto>> list(){
        List<Producto> list = productoService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Producto> getById(@PathVariable("id") int id){
        if(!productoService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Producto producto = productoService.getOne(id).get();
        return new ResponseEntity(producto, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody ProductoDto productoDto) {
        Producto p = new Producto(
        		productoDto.getProducto(),
        		productoDto.getDescripcion(),
        		productoDto.getPrecio(), 
        		productoDto.getFechaVencimiento(),
        		productoDto.getImagenUrl() );
        productoService.save(p);
        return new ResponseEntity(new Mensaje("producto creado"), HttpStatus.OK);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody ProductoDto productoDto){
        
        Producto producto = productoService.getOne(id).get();
        producto.setProducto(productoDto.getProducto());
        producto.setDescripcion(productoDto.getDescripcion());
        producto.setPrecio(productoDto.getPrecio());
        producto.setFechaVencimiento(productoDto.getFechaVencimiento());
        producto.setImagenUrl(productoDto.getImagenUrl());
        productoService.save(producto);
        return new ResponseEntity(new Mensaje("producto actualizado"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id)throws IOException {
        if(!productoService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        productoService.delete(id);
        return new ResponseEntity(new Mensaje("producto eliminada"), HttpStatus.OK);
    }
}
