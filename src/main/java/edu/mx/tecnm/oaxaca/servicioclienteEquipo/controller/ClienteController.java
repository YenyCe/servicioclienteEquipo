/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mx.tecnm.oaxaca.servicioclienteEquipo.controller;

import edu.mx.tecnm.oaxaca.servicioclienteEquipo.authentication.Authentication;
import edu.mx.tecnm.oaxaca.servicioclienteEquipo.exceptions.ExternalMicroserviceException;
import edu.mx.tecnm.oaxaca.servicioclienteEquipo.exceptions.UnauthorizedException;
import edu.mx.tecnm.oaxaca.servicioclienteEquipo.model.ClienteModel;
import edu.mx.tecnm.oaxaca.servicioclienteEquipo.service.ClienteService;
import edu.mx.tecnm.oaxaca.servicioclienteEquipo.utils.CustomResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cliente")
@CrossOrigin(origins = "*")
//@CrossOrigin(origins = "/**")
//@CrossOrigin(origins = "http://127.0.0.1:5500")

public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private Authentication authentication;

    @PostMapping("/")
    public ResponseEntity registroCliente(@RequestBody ClienteModel cliente, HttpServletRequest request) throws UnauthorizedException {
        ResponseEntity<CustomResponse> valueResponse = null;
        CustomResponse responseData = new CustomResponse();
        try {
            authentication.auth(request);
            if (cliente.getRfc().isEmpty() || cliente.getNombre().isEmpty() || cliente.getApellidos().isEmpty() || cliente.getDireccion().isEmpty() || cliente.getCorreo_electronico().isEmpty() || cliente.getNo_telefono().isEmpty() || cliente.getEstatus().isEmpty() || cliente.getPIN() == 0.0d) {
                responseData.setMessage("El atributo no puede ir vacío");
                responseData.setHttpCode(422);
                valueResponse = ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(responseData);
            } else if (clienteService.getCliente(cliente.getRfc()) != null) {
                responseData.setMessage("El RFC ya se encuentra registrado");
                responseData.setHttpCode(422);
                valueResponse = ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(responseData);
            } else if (cliente.getRfc().length() == 13) {
                clienteService.registrarCliente(cliente);
                responseData.setMessage("Success");
                responseData.setHttpCode(201);
                valueResponse = ResponseEntity.status(HttpStatus.CREATED).body(responseData);
            } else {
                responseData.setMessage("Su RFC es incorrecto");
                responseData.setHttpCode(422);
                valueResponse = ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(responseData);
            }
        } catch (UnauthorizedException ex) {
            responseData.setData(ex.toJSON());
            responseData.setHttpCode(401);
            valueResponse = ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseData);
        } catch (ExternalMicroserviceException ex) {
            responseData.setData(ex.toJSON());
            responseData.setHttpCode(503);
            valueResponse = ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(responseData);
        } catch (Exception ex) {
            responseData.setHttpCode(500);
            valueResponse = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        return valueResponse;

    }

    @GetMapping("/")
    public ResponseEntity getClientess(HttpServletRequest request) {
        ResponseEntity valueResponse = null;
        CustomResponse responseData = new CustomResponse();
        try {
            authentication.auth(request);
            if (clienteService.getClientes().isEmpty()) {
                valueResponse = ResponseEntity.status(HttpStatus.NO_CONTENT).body(responseData);
                responseData.setMessage("No hay clientes registrados");
            } else {

                responseData.setData(clienteService.getClientes());
                valueResponse = ResponseEntity.status(HttpStatus.OK).body(responseData);
                responseData.setHttpCode(200);
                responseData.setMessage("Todos los registros existentes:");
            }
        } catch (EntityNotFoundException e) {
            valueResponse = ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
        } catch (UnauthorizedException ex) {
            responseData.setData(ex.toJSON());
            responseData.setHttpCode(401);
            valueResponse = ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseData);
        } catch (ExternalMicroserviceException ex) {
            responseData.setData(ex.toJSON());
            responseData.setHttpCode(503);
        } catch (Exception ex) {
            valueResponse = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return valueResponse;
    }

    @GetMapping("/{rfc}")
    public ResponseEntity getClientes(@PathVariable String rfc, HttpServletRequest request) {
        ResponseEntity valueResponse = null;
        CustomResponse responseData = new CustomResponse();
        try {
            authentication.auth(request);
            if (clienteService.getCliente(rfc) == null) {
                valueResponse = ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
                responseData.setMessage("No hay clientes con este rfc:= " + rfc);
                responseData.setHttpCode(422);
            } else {
                responseData.setData(clienteService.getCliente(rfc));
                responseData.setMessage("Exitoso, si hay cliente con este RFC:" + rfc);
                responseData.setHttpCode(200);
                valueResponse = ResponseEntity.status(HttpStatus.OK).body(responseData);
            }
        } catch (EntityNotFoundException e) {
            valueResponse = ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
        } catch (UnauthorizedException ex) {
            responseData.setData(ex.toJSON());
            responseData.setHttpCode(401);
            valueResponse = ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseData);
        } catch (ExternalMicroserviceException ex) {
            responseData.setData(ex.toJSON());
            responseData.setHttpCode(503);
        } catch (Exception ex) {
            valueResponse = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        return valueResponse;
    }

    @PutMapping("/{rfc}")
    public ResponseEntity updateCliente(@RequestBody ClienteModel cliente, @PathVariable String rfc, HttpServletRequest request) {
        ResponseEntity valueResponse = null;
        CustomResponse responseData = new CustomResponse();
        try {
            authentication.auth(request);
            if (clienteService.getCliente(rfc) == null) {
                valueResponse = ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
                responseData.setMessage("No hay clientes con este rfc:= " + rfc);
            } else if (cliente.getRfc().isEmpty() || cliente.getNombre().isEmpty() || cliente.getApellidos().isEmpty() || cliente.getDireccion().isEmpty() || cliente.getCorreo_electronico().isEmpty() || cliente.getNo_telefono().isEmpty() || cliente.getEstatus().isEmpty() || cliente.getPIN() == 0.0d) {
                responseData.setMessage("El atributo no puede ir vacío");
                responseData.setHttpCode(422);
                valueResponse = ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(responseData);
            } else {
                clienteService.updateCliente(cliente, rfc);
                responseData.setMessage("Successful update");
                responseData.setHttpCode(201);
                valueResponse = ResponseEntity.status(HttpStatus.CREATED).body(responseData);
            }
        } catch (EntityNotFoundException e) {
            valueResponse = ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
        } catch (UnauthorizedException ex) {
            responseData.setData(ex.toJSON());
            responseData.setHttpCode(401);
            valueResponse = ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseData);
        } catch (ExternalMicroserviceException ex) {
            responseData.setData(ex.toJSON());
            responseData.setHttpCode(503);
        } catch (Exception ex) {
            valueResponse = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        return valueResponse;

    }

    @DeleteMapping("/{rfc}")
    public ResponseEntity deleteCliente(@PathVariable String rfc, HttpServletRequest request) {

        ResponseEntity valueResponse = null;
        CustomResponse responseData = new CustomResponse();
        try {
            authentication.auth(request);

            if (clienteService.getCliente(rfc) == null) {
                responseData.setHttpCode(401);
                valueResponse = ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
                responseData.setMessage("No hay clientes con este rfc:= " + rfc);
            } else {
                clienteService.deleteCliente(rfc);
                responseData.setMessage("delete Successful");
                responseData.setHttpCode(204);
                valueResponse = ResponseEntity.status(HttpStatus.OK).body(responseData);
            }

        } catch (EntityNotFoundException e) {
            valueResponse = ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
        } catch (UnauthorizedException ex) {
            responseData.setData(ex.toJSON());
            responseData.setHttpCode(401);
            valueResponse = ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseData);
        } catch (ExternalMicroserviceException ex) {
            responseData.setData(ex.toJSON());
            responseData.setHttpCode(503);
        } catch (Exception ex) {
            valueResponse = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        return valueResponse;
    }
}
