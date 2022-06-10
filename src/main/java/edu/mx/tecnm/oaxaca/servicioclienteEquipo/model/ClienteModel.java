/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mx.tecnm.oaxaca.servicioclienteEquipo.model;

import javax.persistence.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;


/**
 *
 * @author yeny
 */
@Entity
@Table(name = "cliente")
public class ClienteModel {

    @Id
    @Column //Todos los atributos son columnas
    @NotEmpty(message="El RFC es obligatorio")
    private String rfc;
    @NotEmpty(message="El nombre no puede estar vacio")
    private String nombre;
    private String apellidos;
    private String direccion;
    private String correo_electronico;
    private String no_telefono;
    @NotEmpty(message="El PIN no puede estar vacio")
    private int PIN;
    private String estatus;

    public ClienteModel(@NotEmpty(message="El RFC es obligatorio")String rfc, @NotEmpty(message="El nombre no puede estar vacio")String nombre, String apellidos, String direccion, String correo_electronico, String no_telefono, @NotEmpty(message="El PIN no puede estar vacio")int PIN, String estatus) {
        this.rfc = rfc;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.correo_electronico = correo_electronico;
        this.no_telefono = no_telefono;
        this.PIN = PIN;
        this.estatus = estatus;
    }

   

    
    public String getRfc() {
        return rfc;
    }

    public String getNo_telefono() {
        return no_telefono;
    }

    public void setNo_telefono(String no_telefono) {
        this.no_telefono = no_telefono;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo_electronico() {
        return correo_electronico;
    }

    public void setCorreo_electronico(String correo_electronico) {
        this.correo_electronico = correo_electronico;
    }

    public int getPIN() {
        return PIN;
    }

    public void setPIN(int PIN) {
        this.PIN = PIN;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }
    

}
