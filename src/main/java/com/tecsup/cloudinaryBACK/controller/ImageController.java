package com.tecsup.cloudinaryBACK.controller;

import com.tecsup.cloudinaryBACK.dto.Mensaje;
import com.tecsup.cloudinaryBACK.entity.Image;
import com.tecsup.cloudinaryBACK.service.CloudinaryService;
import com.tecsup.cloudinaryBACK.service.ImageService;
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
public class ImageController {

	 @Autowired
	    CloudinaryService cloudinaryService;

	    @Autowired
	    ImageService imageService;

	    @GetMapping("/list")
	    public ResponseEntity<List<Image>> list(){
	        List<Image> list = imageService.list();
	        return new ResponseEntity(list, HttpStatus.OK);
	    }

	    @PostMapping("/upload")
	    public ResponseEntity<?> upload(@RequestParam MultipartFile multipartFile)throws IOException {
	        BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
	        if(bi == null){
	            return new ResponseEntity(new Mensaje("imagen no valida"), HttpStatus.BAD_REQUEST);
	        }
	        Map result = cloudinaryService.upload(multipartFile);
	        Image i =
	                new Image((String)result.get("original_filename"),
	                        (String)result.get("url"),
	                        (String)result.get("public_id"));
	        imageService.save(i);
	        return new ResponseEntity(new Mensaje("imagen subida"), HttpStatus.OK);
	    }

	    @DeleteMapping("/delete/{id}")
	    public ResponseEntity<?> delete(@PathVariable("id") int id)throws IOException {
	        if(!imageService.exists(id))
	            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
	        Image i = imageService.getOne(id).get();
	        Map result = cloudinaryService.delete(i.getImagenId());
	        imageService.delete(id);
	        return new ResponseEntity(new Mensaje("imagen eliminada"), HttpStatus.OK);
	    }
}
