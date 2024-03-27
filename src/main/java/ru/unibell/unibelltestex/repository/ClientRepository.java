package ru.unibell.unibelltestex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.unibell.unibelltestex.model.dto.ClientDto;
import ru.unibell.unibelltestex.model.entity.Client;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query(value = "select c from Client c")
    List<ClientDto> findAllClients();

    Optional<Client> findByName(String name);

}
