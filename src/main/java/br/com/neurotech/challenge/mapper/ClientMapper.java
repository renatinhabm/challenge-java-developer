package br.com.neurotech.challenge.mapper;

import br.com.neurotech.challenge.entity.NeurotechClient;
import br.com.neurotech.challenge.model.ClientDTO;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {

    public NeurotechClient convertClientDTOToNeurotechClient(ClientDTO clientDTO){
        NeurotechClient client = new NeurotechClient();
        client.setName(clientDTO.getName());
        client.setAge(clientDTO.getAge());
        client.setIncome(clientDTO.getIncome());
        return client;
    }

    public ClientDTO convertNeurotechClientToClientDTO(NeurotechClient client){
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setName(client.getName());
        clientDTO.setAge(client.getAge());
        clientDTO.setIncome(client.getIncome());
        return clientDTO;
    }
}
