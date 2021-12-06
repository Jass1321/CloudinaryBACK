package com.tecsup.cloudinaryBACK.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Producto {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String producto;
    private String descripcion;
    private float precio;
    private Date fechaVencimiento;
    private String imagenUrl;
    
    public Producto() {
    	
    }

	public Producto(String producto, String descripcion, float precio, Date fechaVencimiento, String imagenUrl) {
		super();
		this.producto = producto;
		this.descripcion = descripcion;
		this.precio = precio;
		this.fechaVencimiento = fechaVencimiento;
		this.imagenUrl = imagenUrl;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public String getImagenUrl() {
		return imagenUrl;
	}

	public void setImagenUrl(String imagenUrl) {
		this.imagenUrl = imagenUrl;
	}

	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
	
	
	
	

}
