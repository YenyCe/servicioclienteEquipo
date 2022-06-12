package edu.mx.tecnm.oaxaca.servicioclienteEquipo.utils;

import java.util.LinkedList;
import org.springframework.http.HttpStatus;

/**
 *
 * @author yeny
 */
public class CustomResponse {

   // private Integer httpCode;
    private Object data;
    public Object mensaje;
    private String httpCodeMessage;
    public int code;
    private HttpStatus httpCode;

      public CustomResponse() {
    }
    public CustomResponse(HttpStatus httpCode, int code) {
        this.httpCode = httpCode;
        data = new LinkedList();
        this.mensaje = new LinkedList();
    }

    public HttpStatus getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(HttpStatus httpCode) {
        this.httpCode = httpCode;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getMensaje() {
        return mensaje;
    }

    public void setMensaje(Object mensaje) {
        this.mensaje = mensaje;
    }
    
      public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
