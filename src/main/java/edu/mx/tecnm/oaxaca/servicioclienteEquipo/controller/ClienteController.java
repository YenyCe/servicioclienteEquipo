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
    public CustomResponse registroCliente(@Valid @RequestBody ClienteModel cliente) {
        CustomResponse customResponse = new CustomResponse();
        clienteService.registrarCliente(cliente);
        return customResponse;

    }

    @GetMapping("/")
    public CustomResponse getClientess() {
        CustomResponse customResponse = new CustomResponse();
        customResponse.setData(clienteService.getClientes());
        return customResponse;
    }

    @GetMapping("/{rfc}")
    public CustomResponse getClientes(@PathVariable String rfc) {
        CustomResponse customResponse = new CustomResponse();
        customResponse.setData(clienteService.getCliente(rfc));
        return customResponse;
    }

    @PutMapping("/{rfc}")
    public CustomResponse updateCliente(@RequestBody ClienteModel cliente, @PathVariable String rfc) {
        CustomResponse customResponse = new CustomResponse();
        clienteService.updateCliente(cliente, rfc);
        return customResponse;
    }

    @DeleteMapping("/{rfc}")
    public CustomResponse deleteCliente(@PathVariable String rfc) {
        CustomResponse customResponse = new CustomResponse();
        clienteService.deleteCliente(rfc);
        return customResponse;
    }
}
