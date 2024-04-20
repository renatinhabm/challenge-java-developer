package br.com.neurotech.challenge;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Optional;

import br.com.neurotech.challenge.dao.ClientRepository;
import br.com.neurotech.challenge.entity.NeurotechClient;
import br.com.neurotech.challenge.service.ClientServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class ClientServiceImplTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientServiceImpl clientService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private static NeurotechClient neurotechClientMock() {
        NeurotechClient clientToSave = new NeurotechClient();
        clientToSave.setId("1");
        clientToSave.setName("José da Silva");
        clientToSave.setAge(30);
        clientToSave.setIncome(5000.0);
        return clientToSave;
    }

    @Test
    void testSave() {

        NeurotechClient clientToSave = neurotechClientMock();
        when(clientRepository.save(any())).thenReturn(clientToSave);

        String clientId = clientService.save(clientToSave);

        verify(clientRepository, times(1)).save(any());

        assertEquals("1", clientId);
    }

    @Test
    void testGet() throws Exception {

        NeurotechClient mockClient = neurotechClientMock();

        when(clientRepository.findById("mocked_id")).thenReturn(Optional.of(mockClient));

        NeurotechClient retrievedClient = clientService.get("mocked_id");

        verify(clientRepository, times(1)).findById("mocked_id");

        assertEquals(mockClient, retrievedClient);
    }
}
