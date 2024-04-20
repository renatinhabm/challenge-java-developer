package br.com.neurotech.challenge.service;

import br.com.neurotech.challenge.dao.ClientRepository;
import br.com.neurotech.challenge.entity.NeurotechClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService{

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public String save(NeurotechClient client) {
        //TODO: validações
        NeurotechClient clientSaved = clientRepository.save(client);

        return clientSaved.getId();
    }

    @Override
    public NeurotechClient get(String id) throws Exception {
        Optional<NeurotechClient> client = this.clientRepository.findById(id);
        if (client.isEmpty()){
            return null;
        }
        return client.get();
    }
}
