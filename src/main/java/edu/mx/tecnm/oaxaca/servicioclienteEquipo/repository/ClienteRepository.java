package edu.mx.tecnm.oaxaca.servicioclienteEquipo.repository;

import edu.mx.tecnm.oaxaca.servicioclienteEquipo.model.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteModel, String> {

    public ClienteModel findByRfc(String rfc);

}
