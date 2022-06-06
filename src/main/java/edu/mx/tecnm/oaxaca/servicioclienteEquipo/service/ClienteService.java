/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mx.tecnm.oaxaca.servicioclienteEquipo.service;

import edu.mx.tecnm.oaxaca.servicioclienteEquipo.model.ClienteModel;
import java.util.List;
public interface ClienteService {
    public void registrarCliente(ClienteModel cliente);
    public List getClientes();
    public ClienteModel getCliente(String rfc);
    public void updateCliente(ClienteModel clienteModel, String rfc);
    public void deleteCliente(String rfc);
    
}
