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
            clienteService.registrarCliente(cliente);
            responseData.setMessage("Success: El Cliente se ha creado correctamente");
            responseData.setHttpCode(201);
            valueResponse = ResponseEntity.status(HttpStatus.CREATED).body(responseData);

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
                responseData.setHttpCode(422);
                responseData.setMessage("No hay clientes registrados");
                valueResponse = ResponseEntity.status(HttpStatus.NO_CONTENT).body(responseData);
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
                responseData.setMessage("Bad Request: No hay clientes con este rfc:= " + rfc);
                responseData.setHttpCode(422);
            } else {
                responseData.setData(clienteService.getCliente(rfc));
                responseData.setMessage("Success: Si hay cliente con este RFC:" + rfc);
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

        authentication.auth(request);

        clienteService.updateCliente(cliente, rfc);
        responseData.setMessage("OK: Successful update");
        responseData.setHttpCode(201);
        valueResponse = ResponseEntity.status(HttpStatus.CREATED).body(responseData);

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
                responseData.setMessage("Not Found: No hay clientes con este rfc:= " + rfc);
            } else {
                clienteService.deleteCliente(rfc);
                responseData.setMessage("OK: Delete Successful");
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
