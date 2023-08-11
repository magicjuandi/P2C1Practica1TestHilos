package mapping.dtos;

import domain.enums.ClientType;

public record ClientDto(Long id,
                        String name,
                        ClientType level) {
}
