package ru.unibell.unibelltestex.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.unibell.unibelltestex.model.dto.ContactCreateDto;
import ru.unibell.unibelltestex.model.dto.ContactDto;
import ru.unibell.unibelltestex.model.dto.ContactType;
import ru.unibell.unibelltestex.service.ContactsService;
import ru.unibell.unibelltestex.util.SwaggerMessage;
import ru.unibell.unibelltestex.validation.ContactTypeValidation;


import java.net.URI;
import java.util.List;

@Tag(name = SwaggerMessage.CONTACT_CONTROLLER_NAME,description = SwaggerMessage.CONTACT_CONTROLLER_DESCRIPTION)
@RestController
@RequestMapping("/api/v1/clients/{clientId}/contacts")
@RequiredArgsConstructor
@Validated
public class ContactController {

    private final ContactsService contactsService;


    @Operation(
            summary = SwaggerMessage.CREATE_CONTACT_SUMMARY,
            description = SwaggerMessage.CREATE_CONTACT_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "created")
    })
    @PostMapping(value = "")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> create(@PathVariable Long clientId,
                                         @Valid @RequestBody ContactCreateDto contactCreateDto) {
        Long contactId = contactsService.createContact(clientId, contactCreateDto);
        URI location = URI.create("/api/v1/clients/" + clientId + "/contacts/" + contactId);
        return ResponseEntity.created(location).build();
    }


    @Operation(
            summary = SwaggerMessage.GET_CONTACTS_SUMMARY,
            description = SwaggerMessage.GET_CONTACTS_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<ContactDto> getAllByClientAndType(@PathVariable Long clientId,
                                                  @ContactTypeValidation @RequestParam(required = false) String contactType) {
        return contactsService.getAllContacts(clientId, contactType);
    }

}
