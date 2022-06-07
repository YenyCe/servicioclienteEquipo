/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mx.tecnm.oaxaca.servicioclienteEquipo.model;

import javax.persistence.*;

/**
 *
 * @author yeny
 */
@Entity
@Table(name = "cliente")
public class ClienteModel {

    @Id
    @Column //Todos los atributos son columnas
    private String rfc;
    private int id;
    private String nombre;
    private String apellidos;
    private String direccion;
    private String correo_electronico;
    private String no_telefono;
    private int PIN;
    private String estatus;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
