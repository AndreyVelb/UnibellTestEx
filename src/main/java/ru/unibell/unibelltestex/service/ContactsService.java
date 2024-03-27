package ru.unibell.unibelltestex.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.unibell.unibelltestex.exception.ClientNotFoundException;
import ru.unibell.unibelltestex.model.dto.ContactCreateDto;
import ru.unibell.unibelltestex.model.dto.ContactDto;
import ru.unibell.unibelltestex.model.entity.Client;
import ru.unibell.unibelltestex.model.entity.Contact;
import ru.unibell.unibelltestex.repository.ClientRepository;
import ru.unibell.unibelltestex.repository.ContactRepository;
import ru.unibell.unibelltestex.service.converter.ContactConverter;
import ru.unibell.unibelltestex.service.converter.ContactToContactDto;
import ru.unibell.unibelltestex.service.converter.ContactToContactDtoWithoutEmail;
import ru.unibell.unibelltestex.service.converter.ContactToContactDtoWithoutPhone;
import ru.unibell.unibelltestex.util.ExceptionMessage;

import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContactsService {

    private final ClientService clientService;
    private final ContactRepository contactRepository;
    private final ClientRepository clientRepository;
    private final ContactToContactDto contactToContactDto;
    private final ContactToContactDtoWithoutPhone contactToContactDtoWithoutPhone;
    private final ContactToContactDtoWithoutEmail contactToContactDtoWithoutEmail;


    public List<ContactDto> getAllContacts(Long clientId, String contactType) {
        ContactConverter contactConverter;
        if (clientService.isClientExisted(clientId)) {
            if (contactType == null) {
                contactConverter = contactToContactDto;
                return mapToConvertDto(contactRepository.findAllContactsByClientId(clientId), contactConverter);
            } else {
                switch (contactType) {
                    case "EMAIL" -> {
                        contactConverter = contactToContactDtoWithoutPhone;
                        return mapToConvertDto(contactRepository.findAllEmailsByClientId(clientId), contactConverter);
                    }
                    case "PHONE" -> {
                        contactConverter = contactToContactDtoWithoutEmail;
                        return mapToConvertDto(contactRepository.findAllPhoneNumbersByClientId(clientId), contactConverter);
                    }
                    default -> throw new ConcurrentModificationException(ExceptionMessage.NON_VALID_CONTACT_TYPE);

                }
            }
        } else throw new ClientNotFoundException(ExceptionMessage.CLIENT_NOT_FOUND);

    }


    public Long createContact(Long clientId, ContactCreateDto dto) {
        Client client = clientRepository.findById(clientId).orElseThrow(()
                -> new ClientNotFoundException(ExceptionMessage.CLIENT_NOT_FOUND));

        Optional<Contact> optionalContact = contactRepository.findByClientIdAndContactName(clientId, dto.getContactName());
        if (optionalContact.isEmpty()) {
            return contactRepository.save(
                    Contact.builder()
                            .client(client)
                            .contactName(dto.getContactName())
                            .phoneNumber(dto.getPhoneNumber())
                            .email(dto.getEmail())
                            .build()).getId();
        } else {
            Contact contact = optionalContact.get();
            if (dto.getPhoneNumber() != null) contact.setPhoneNumber(dto.getPhoneNumber());
            if (dto.getEmail() != null) contact.setEmail(dto.getEmail());
            return contactRepository.save(contact).getId();
        }

    }


    private List<ContactDto> mapToConvertDto(List<Contact> list, ContactConverter converter) {
        return list.stream().map(converter::convertContact)
                .collect(Collectors.toList());
    }
}
