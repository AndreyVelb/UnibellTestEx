package ru.unibell.unibelltestex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.unibell.unibelltestex.model.dto.ContactDto;
import ru.unibell.unibelltestex.model.entity.Contact;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

    @Query(value = "select c " +
            "from Contact c " +
            "where c.client.id = :clientId")
    List<Contact> findAllContactsByClientId(@Param("clientId") Long clientId);


    @Query(value = "select c " +
            "from Contact c " +
            "where c.client.id = :clientId " +
            "and c.phoneNumber is not null")
    List<Contact> findAllPhoneNumbersByClientId(@Param("clientId") Long clientId);


    @Query(value = "select c " +
            "from Contact c " +
            "where c.client.id = :clientId and c.email is not null")
    List<Contact> findAllEmailsByClientId(@Param("clientId") Long clientId);

    @Query(value = "select c " +
            "from Contact c " +
            "where c.client.id = :clientId and c.contactName = :contactName")
    Optional<Contact> findByClientIdAndContactName(@Param("clientId") Long clientId,
                                                   @Param("contactName") String contactName);
}
