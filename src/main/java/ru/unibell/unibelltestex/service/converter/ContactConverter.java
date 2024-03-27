package ru.unibell.unibelltestex.service.converter;

import ru.unibell.unibelltestex.model.dto.ContactDto;
import ru.unibell.unibelltestex.model.entity.Contact;

public interface ContactConverter {

    ContactDto convertContact(Contact contact);

}
