package br.com.neurotech.challenge.controller;

import br.com.neurotech.challenge.api.ClientApi;
import br.com.neurotech.challenge.entity.NeurotechClient;
import br.com.neurotech.challenge.entity.VehicleModel;
import br.com.neurotech.challenge.mapper.ClientMapper;
import br.com.neurotech.challenge.model.ClientDTO;
import br.com.neurotech.challenge.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@Validated
@RestController
public class ClientController implements ClientApi {

    @Autowired
    private ClientService clientService;

    @Autowired
    private ClientMapper clientMapper;

    /*Endpoint para cadastro de clientes: Deve receber Informações como Nome, idade, renda.
    Como retorno, uma entrada no header da resposta contendo a URL que identifica o cliente
    (Ex: http://localhost/api/client/050). O nome do header deve ser “Location”.
     */
    public ResponseEntity<ClientDTO> createClient(ClientDTO clientDTO) throws Exception {

        if (Objects.isNull(clientDTO)){
            throw new Exception("Favor informar os dados do cliente!");
        }

        NeurotechClient client = clientMapper.convertClientDTOToNeurotechClient(clientDTO);

        String clientId = this.clientService.save(client);

        if (clientId.isEmpty() || clientId.isBlank()){
            System.out.println("Houve algum problema.Não foi possível recuperar o Id");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        String clientUrl = String.format("http://localhost:5000/api/client/%s", clientId);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Location", clientUrl);

        return ResponseEntity.ok().headers(httpHeaders).body(clientDTO);

    }


    /*Endpoint para retornar os dados do cliente de acordo com seu ID, indicado na URL
    (Ex: http://localhost/api/client/050).
    O retorno deve ser um objeto JSON contendo os dados do cliente. Por exemplo:
    {
      "Name": "Bob",
      "Age": 40,
      "Income": 10000
     }
     */
    public ResponseEntity<ClientDTO> getClient(String clientId) throws Exception {
        if (clientId.isEmpty() || clientId.isBlank()){
            throw new Exception("Favor informar o id do cliente!");
        }

        NeurotechClient client = this.clientService.get(clientId);
        if (client == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        ClientDTO clientDTO = clientMapper.convertNeurotechClientToClientDTO(client);
        return new ResponseEntity<>(clientDTO, HttpStatus.OK);
    }

}
