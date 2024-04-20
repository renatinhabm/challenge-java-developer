package br.com.neurotech.challenge.service;

import br.com.neurotech.challenge.entity.NeurotechClient;
import br.com.neurotech.challenge.entity.VehicleModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreditServiceImpl implements CreditService{

    @Autowired
    private ClientService clientService;

    @Override
    public boolean checkCredit(String clientId, VehicleModel model) throws Exception {

        if (clientId.isEmpty() || clientId.isBlank() || model == null){
            throw new Exception("Dados Inválidos!");
        }
        NeurotechClient client = this.clientService.get(clientId);
        if (client == null){
            throw new Exception("Cliente Inexistente!");
        }

        switch (model){
            case VehicleModel.HATCH:
                if (client.getIncome() >= 5000.00 && client.getIncome() <= 15000.00){
                    return true;
                }
            case VehicleModel.SUV:
                if (client.getIncome() >= 8000.00 && client.getAge() > 20){
                    return true;
                }
        }

        return false;
    }
}
