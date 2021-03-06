/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mx.tecnm.oaxaca.servicioclienteEquipo.controller;

import edu.mx.tecnm.oaxaca.servicioclienteEquipo.model.ClienteModel;
import edu.mx.tecnm.oaxaca.servicioclienteEquipo.service.ClienteService;
import edu.mx.tecnm.oaxaca.servicioclienteEquipo.utils.CustomResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@RequestMapping("/api/cliente")
@CrossOrigin(origins = "*")
//@CrossOrigin(origins = "/**")
//@CrossOrigin(origins = "http://127.0.0.1:5500")

public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping("/")
    public CustomResponse registroCliente(@RequestBody ClienteModel cliente) {
        CustomResponse customResponse = new CustomResponse();
        if (cliente.getRfc().isEmpty()) {
            customResponse.setMensaje("El atributo RFC no puede ir vacío");
            customResponse.setHttpCode(HttpStatus.UNPROCESSABLE_ENTITY);
            customResponse.setCode(422);
        } else if (cliente.getNombre().isEmpty() || cliente.getApellidos().isEmpty()) {
            customResponse.setMensaje("El atributo no puede ir vacío");
            customResponse.setHttpCode(HttpStatus.UNPROCESSABLE_ENTITY);
            customResponse.setCode(422);
        } else if (cliente.getRfc().length() == 13) {
            clienteService.registrarCliente(cliente);
            customResponse.setHttpCode(HttpStatus.CREATED);
            customResponse.setCode(201);
            customResponse.setMensaje("Success");
        } else {
            customResponse.setMensaje("Su RFC es incorrecto");
            customResponse.setHttpCode(HttpStatus.UNPROCESSABLE_ENTITY);
            customResponse.setCode(422);
        }

        return customResponse;

    }

    @GetMapping("/")
    public CustomResponse getClientess() {
        CustomResponse customResponse = new CustomResponse();
        if (clienteService.getClientes().isEmpty()) {
            customResponse.setHttpCode(HttpStatus.NO_CONTENT);
            customResponse.setMensaje("No hay clientes registrados");
        } else {

            customResponse.setData(clienteService.getClientes());
            customResponse.setHttpCode(HttpStatus.OK);
            customResponse.setCode(200);
            customResponse.setMensaje("Todos los registros existentes:");

        }
        return customResponse;
    }

    @GetMapping("/{rfc}")
    public CustomResponse getClientes(@PathVariable String rfc) {
        CustomResponse customResponse = new CustomResponse();
        if (clienteService.getCliente(rfc) == null) {
            customResponse.setHttpCode(HttpStatus.NOT_FOUND);
            customResponse.setMensaje("No hay clientes con este rfc:= " + rfc);
        } else {
            customResponse.setData(clienteService.getCliente(rfc));
            customResponse.setHttpCode(HttpStatus.OK);
            customResponse.setCode(200);
            customResponse.setMensaje("Exitoso, si hay cliente con este RFC:" + rfc);
        }
        return customResponse;
    }

    @PutMapping("/{rfc}")
    public CustomResponse updateCliente(@RequestBody ClienteModel cliente, @PathVariable String rfc) {
        CustomResponse customResponse = new CustomResponse();

        if (clienteService.getCliente(rfc) == null) {
            customResponse.setHttpCode(HttpStatus.NOT_FOUND);
            customResponse.setMensaje("No hay clientes con este rfc:= " + rfc);
        } else {
            clienteService.updateCliente(cliente, rfc);
            customResponse.setHttpCode(HttpStatus.CREATED);
            customResponse.setCode(201);
            customResponse.setMensaje("Successful update");
        }

        return customResponse;
    }

    @DeleteMapping("/{rfc}")
    public CustomResponse deleteCliente(@PathVariable String rfc) {
        CustomResponse customResponse = new CustomResponse();

        if (clienteService.getCliente(rfc) == null) {
            customResponse.setHttpCode(HttpStatus.NOT_FOUND);
            customResponse.setMensaje("No hay clientes con este rfc:= " + rfc);
        } else {
            clienteService.deleteCliente(rfc);
            customResponse.setHttpCode(HttpStatus.OK);
            customResponse.setCode(204);
            customResponse.setMensaje(" delete Successful");
        }
        return customResponse;
    }
}
