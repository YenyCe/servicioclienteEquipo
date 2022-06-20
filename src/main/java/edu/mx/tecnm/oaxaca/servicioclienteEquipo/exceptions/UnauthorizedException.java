
package edu.mx.tecnm.oaxaca.servicioclienteEquipo.exceptions;
import edu.mx.tecnm.oaxaca.servicioclienteEquipo.constans.AuthenticationConstans;
/**
 *
 * @author herna
 */
public class UnauthorizedException extends CustomException{
    
    public UnauthorizedException() {
        super(AuthenticationConstans.INVALID_TOKEN_MENSAJE_EXCEPTION);
    }
    
}
