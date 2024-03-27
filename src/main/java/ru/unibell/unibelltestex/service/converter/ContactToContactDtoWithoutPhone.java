package ru.unibell.unibelltestex.service.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.unibell.unibelltestex.model.dto.ContactDto;
import ru.unibell.unibelltestex.model.entity.Contact;

@Component
public class ContactToContactDtoWithoutPhone implements Converter<Contact, ContactDto>, ContactConverter {


    @Override
    public ContactDto convertContact(Contact contact) {
        return convert(contact);
    }


    @Override
    public ContactDto convert(Contact contact) {
        return ContactDto.builder()
                .id(contact.getId())
                .contactName(contact.getContactName())
                .phoneNumber(null)
                .email(contact.getEmail())
                .build();
    }
}
