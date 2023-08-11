package repository;

import domain.models.Client;
import mapping.dtos.ClientDto;

import java.util.List;

public interface ClientRepository {
    List<ClientDto> listAllClient();

}
