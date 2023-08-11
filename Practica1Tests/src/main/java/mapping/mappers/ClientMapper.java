package mapping.mappers;

import domain.models.Client;
import mapping.dtos.ClientDto;

import java.util.List;

public class ClientMapper {
    public static ClientDto mapFrom(Client source){
        return new ClientDto(source.getId(), source.getName(),source.getLevel());
    }
    public static Client mapFrom(ClientDto source){
        return new Client(source.id(),source.name(),source.level());
    }
    public static List<ClientDto> mapFrom(List<Client> source){
        return source.stream().
                map(ClientMapper::mapFrom)
                .toList();
    }public static List<Client> mapFromDto(List<ClientDto> source){
        return source.stream().
                map(ClientMapper::mapFrom)
                .toList();
    }
}
