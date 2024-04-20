package br.com.neurotech.challenge.api;

import br.com.neurotech.challenge.entity.ValidationCredit;
import br.com.neurotech.challenge.entity.VehicleModel;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Api(
        value = "CreditApi"
)
public interface CreditApi {

    @GetMapping(
            value = {"/api/credit/validation/{clientId}/{veiculo}"},
            produces = {"application/json"}
    )
    public ResponseEntity<ValidationCredit> validateCredit(@PathVariable("clientId") String clientId,
                                                           @PathVariable("veiculo") VehicleModel model) throws Exception;
}
