package repository.impl;

import domain.enums.ClientType;
import domain.models.Client;
import mapping.dtos.ClientDto;
import mapping.mappers.ClientMapper;
import mapping.mappers.ProductMapper;
import repository.ClientRepository;

import java.util.ArrayList;
import java.util.List;


public class ClientRepositoryimpl implements ClientRepository {
    private List<Client> clients;

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public ClientRepositoryimpl() {

        Client client1 = new Client(1L, "Juan", ClientType.LEVEL1);
        Client client2 = new Client(2L, "Fernanda", ClientType.LEVEL2);
        Client client3 = new Client(3L, "Pedro", ClientType.LEVEL3);

        clients = new ArrayList<Client>();

        clients.add(client1);
        clients.add(client2);
        clients.add(client3);
    }

    @Override
    public List<ClientDto> listAllClient() {
        return ClientMapper.mapFrom(clients);
    }
}
