/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mx.tecnm.oaxaca.servicioclienteEquipo.implement.service;

import edu.mx.tecnm.oaxaca.servicioclienteEquipo.model.ClienteModel;
import edu.mx.tecnm.oaxaca.servicioclienteEquipo.repository.ClienteRepository;
import edu.mx.tecnm.oaxaca.servicioclienteEquipo.service.ClienteService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImplements implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public void registrarCliente(ClienteModel cliente) {
        clienteRepository.save(cliente);
    }

    @Override
    public List getClientes() {
        return clienteRepository.findAll();
    }

    @Override
    public ClienteModel getCliente(String rfc) {
        return clienteRepository.findByRfc(rfc);
    }

    @Override
    public void updateCliente(ClienteModel clienteModel, String rfc) {
        clienteModel.setRfc(rfc);
        clienteRepository.save(clienteModel);

    }

    @Override
    public void deleteCliente(String rfc) {
        clienteRepository.deleteById(rfc);

    }

}
