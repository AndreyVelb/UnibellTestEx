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
import ru.unibell.unibelltestex.model.dto.ClientCreateDto;
import ru.unibell.unibelltestex.model.dto.ClientDto;
import ru.unibell.unibelltestex.service.ClientService;
import ru.unibell.unibelltestex.util.SwaggerMessage;

import java.net.URI;
import java.util.List;

@Tag(name = SwaggerMessage.CLIENT_CONTROLLER_NAME, description = SwaggerMessage.CLIENT_CONTROLLER_DESCRIPTION)
@RestController
@RequestMapping("/api/v1/clients")
@RequiredArgsConstructor
@Validated
public class ClientController {

    private final ClientService clientService;


    @Operation(
            summary = SwaggerMessage.GET_ALL_CLIENTS_SUMMARY,
            description = SwaggerMessage.GET_ALL_CLIENTS_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<ClientDto> getAll() {
        return clientService.getAllClients();
    }


    @Operation(
            summary = SwaggerMessage.GET_CLIENT_SUMMARY,
            description = SwaggerMessage.GET_CLIENT_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @GetMapping(value = "/{clientId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ClientDto get(@PathVariable Long clientId) {
        return clientService.getClient(clientId);
    }


    @Operation(
            summary = SwaggerMessage.CREATE_CLIENT_SUMMARY,
            description = SwaggerMessage.CREATE_CLIENT_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "created")
    })
    @PostMapping(value = "")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> create(@Valid @RequestBody ClientCreateDto clientCreateDto) {
        Long clientId = clientService.createClient(clientCreateDto);
        URI location = URI.create("/api/v1/clients/" + clientId);
        return ResponseEntity.created(location).build();
    }

}
