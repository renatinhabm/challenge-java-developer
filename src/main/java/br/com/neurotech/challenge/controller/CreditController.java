package br.com.neurotech.challenge.controller;

import br.com.neurotech.challenge.api.CreditApi;
import br.com.neurotech.challenge.entity.ValidationCredit;
import br.com.neurotech.challenge.entity.VehicleModel;
import br.com.neurotech.challenge.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
public class CreditController implements CreditApi{

    @Autowired
    private CreditService creditService;

    /*
    Endpoint para definir se um determinado cliente está apto a oferecer um crédito automotivo
    para determinado modelo de veículo.
    Hatch: Renda entre R$ 5000,00 e R$15000,00.
    SUV: Renda acima de R$8000,00 e idade superior a 20 anos.
     */
    public ResponseEntity<ValidationCredit> validateCredit(String clientId,
                                                           VehicleModel model) throws Exception{
        if (clientId.isEmpty() || clientId.isBlank() || model == null){
            throw new Exception("Favor informar o id do cliente!");
        }
        if (!this.creditService.checkCredit(clientId, model)){
            return new ResponseEntity<>(ValidationCredit.INAPTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(ValidationCredit.APTO, HttpStatus.OK);
    }
}
