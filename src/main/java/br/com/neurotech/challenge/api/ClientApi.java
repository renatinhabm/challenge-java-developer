package br.com.neurotech.challenge.api;

import br.com.neurotech.challenge.model.ClientDTO;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Api(
        value = "ClientApi"
)
public interface ClientApi {

    @PostMapping(
            value = {"/api/client"},
            produces = {"application/json"},
            consumes = {"application/json"}
    )
    public ResponseEntity<ClientDTO> createClient(@RequestBody ClientDTO clientDTO) throws Exception;

    @GetMapping(
            value = {"/api/client/{clientId}"},
            produces = {"application/json"}
    )
    public ResponseEntity<ClientDTO> getClient(@PathVariable("clientId") String id) throws Exception;

}
