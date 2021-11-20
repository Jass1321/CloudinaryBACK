package com.tecsup.cloudinaryBACK.controller;

import com.tecsup.cloudinaryBACK.dto.Mensaje;
import com.tecsup.cloudinaryBACK.entity.Imagen;
import com.tecsup.cloudinaryBACK.service.CloudinaryService;
import com.tecsup.cloudinaryBACK.service.ImagenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cloudinary")
@CrossOrigin
public class ImagenController {

	 @Autowired
	    CloudinaryService cloudinaryService;

	    @Autowired
	    ImagenService imageService;

	    @GetMapping("/list")
	    public ResponseEntity<List<Imagen>> list(){
	        List<Imagen> list = imageService.list();
	        return new ResponseEntity(list, HttpStatus.OK);
	    }

	    @PostMapping("/upload")
	    public ResponseEntity<?> upload(@RequestParam MultipartFile multipartFile)throws IOException {
	        BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
	        if(bi == null){
	            return new ResponseEntity(new Mensaje("imagen no valida"), HttpStatus.BAD_REQUEST);
	        }
	        Map result = cloudinaryService.upload(multipartFile);
	        Imagen i =
	                new Imagen((String)result.get("original_filename"),
	                        (String)result.get("url"),
	                        (String)result.get("public_id"));
	        imageService.save(i);
	        return new ResponseEntity(new Mensaje("imagen subida"), HttpStatus.OK);
	    }

	    @DeleteMapping("/delete/{id}")
	    public ResponseEntity<?> delete(@PathVariable("id") int id)throws IOException {
	        if(!imageService.exists(id))
	            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
	        Imagen i = imageService.getOne(id).get();
	        Map result = cloudinaryService.delete(i.getImagenId());
	        imageService.delete(id);
	        return new ResponseEntity(new Mensaje("imagen eliminada"), HttpStatus.OK);
	    }
}
