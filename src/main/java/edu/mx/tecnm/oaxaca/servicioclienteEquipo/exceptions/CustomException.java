package edu.mx.tecnm.oaxaca.servicioclienteEquipo.exceptions;
/**
 *
 * @author herna
 */

import java.util.HashMap;
import java.util.Map;

public abstract class CustomException extends Exception {

    public CustomException(String string) {
        super(string);
    }

    public Map<String, Object> toJSON() {
        Map<String, Object> exception = new HashMap();
        exception.put("errorName", getClass().getSimpleName());
        exception.put("cause", getMessage());
        return exception;
    }
}
