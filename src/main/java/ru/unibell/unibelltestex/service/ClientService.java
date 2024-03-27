package ru.unibell.unibelltestex.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.unibell.unibelltestex.exception.ClientAlreadyExistedException;
import ru.unibell.unibelltestex.exception.ClientNotFoundException;
import ru.unibell.unibelltestex.model.dto.ClientCreateDto;
import ru.unibell.unibelltestex.model.dto.ClientDto;
import ru.unibell.unibelltestex.model.dto.ClientDtoImpl;
import ru.unibell.unibelltestex.model.entity.Client;
import ru.unibell.unibelltestex.repository.ClientRepository;
import ru.unibell.unibelltestex.util.ExceptionMessage;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;


    public List<ClientDto> getAllClients() {
        return clientRepository.findAllClients();
    }


    public ClientDto getClient(Long clientId) {
        Client client = clientRepository.findById(clientId).orElseThrow(()
                -> new ClientNotFoundException(ExceptionMessage.CLIENT_NOT_FOUND));
        return ClientDtoImpl.builder()
                .id(client.getId())
                .name(client.getName())
                .build();
    }


    public Long createClient(ClientCreateDto dto) {
        Optional<Client> optionalClient = clientRepository.findByName(dto.getName());

        if (optionalClient.isEmpty()) {
            return clientRepository.save(
                    Client.builder()
                            .name(dto.getName())
                            .build()).getId();
        } else throw new ClientAlreadyExistedException(ExceptionMessage.CLIENT_NOT_FOUND);
    }


    public boolean isClientExisted(Long clientId) {
        return clientRepository.findById(clientId).isPresent();
    }


}
