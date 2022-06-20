/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mx.tecnm.oaxaca.servicioclienteEquipo.constans;

/**
 *
 * @author herna
 */
public class AuthenticationConstans {

    public static final String URL_AUTH = "https://autenticacion-t.herokuapp.com/login/auth/token/";
    public static final String INVALID_TOKEN_MENSAJE_EXCEPTION = "La sesion es invalida";

    public static final String ERROR_EXTERNAL_MENSAJE_EXCEPTION = "Problemas con la comunicacion al microservicio de autenticacion";

    private AuthenticationConstans() {
    }
}
