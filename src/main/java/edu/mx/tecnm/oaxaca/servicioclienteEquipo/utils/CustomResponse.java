package edu.mx.tecnm.oaxaca.servicioclienteEquipo.utils;

import java.util.LinkedList;

/**
 *
 * @author yeny
 */
public class CustomResponse {

    private Integer httpCode;
    private Object data;
    public String mensaje;
    private String httpCodeMessage;

    public CustomResponse() {
        this.httpCode = 200;
        data = new LinkedList();
        this.mensaje = "OK";
    }

    public Integer getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(Integer httpCode) {
        this.httpCode = httpCode;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
