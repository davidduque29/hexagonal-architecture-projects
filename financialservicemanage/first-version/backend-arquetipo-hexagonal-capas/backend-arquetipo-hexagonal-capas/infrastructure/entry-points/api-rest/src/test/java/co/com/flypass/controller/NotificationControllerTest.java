package co.com.flypass.controller;

import co.com.flypass.apirest.controllers.ClientController;
import co.com.flypass.apirest.dtos.request.client.ClientRequestDTO;
import co.com.flypass.apirest.dtos.request.client.ClientUpdateRequestDTO;
import co.com.flypass.apirest.dtos.response.ClientResponseDTO;
import co.com.flypass.apirest.handlers.IClientHandler;
import static org.assertj.core.api.Assertions.assertThat;

import co.com.flypass.constants.Constants;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ClientController.class)
class NotificationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private IClientHandler clientHandler;

    @Test
    void testAddClientWithReturnedResponseAsObject() throws Exception {
        ClientRequestDTO requestDTO = getClientRequestDTO();
        ClientResponseDTO expectedResponse = getClientResponseDTO();
        when(clientHandler.save(Mockito.any(ClientRequestDTO.class))).thenReturn(expectedResponse);

        MvcResult result = mockMvc.perform(post("/api/v1/client")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(requestDTO)))
                .andExpect(status().isCreated())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        ClientResponseDTO actualResponse = new ObjectMapper().readValue(content, ClientResponseDTO.class);

        assertThat(actualResponse).isEqualTo(expectedResponse);
        verify(clientHandler, times(1)).save(any(ClientRequestDTO.class));

    }

    @Test
    void testUpdateClientWithReturnedSomeProperties() throws Exception {
        ClientUpdateRequestDTO requestDTO = getClientUpdateRequestDTO();
        ClientResponseDTO responseDTO = getClientResponseDTO();
        when(clientHandler.update(Mockito.any(ClientUpdateRequestDTO.class))).thenReturn(responseDTO);

        mockMvc.perform(put("/api/v1/client/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(requestDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Doe"));

        verify(clientHandler, times(1)).update(any(ClientUpdateRequestDTO.class));
    }

    @Test
    void testDeleteClient() throws Exception {
        Long clientId = 1L;

        mockMvc.perform(delete("/api/v1/client/{clientId}", clientId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(Constants.CLIENT_DELETED_MESSAGE));
    }

    private String asJsonString(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    private static ClientRequestDTO getClientRequestDTO() {
        ClientRequestDTO notificationRequestDTO = new ClientRequestDTO();
        notificationRequestDTO.setIdentificationType("prueba");
        notificationRequestDTO.setIdentificationNumber(1234567L);
        notificationRequestDTO.setFirstName("pruebaName");
        notificationRequestDTO.setLastName("1234567890");
        notificationRequestDTO.setEmailAddress("prueba@email.com");
        notificationRequestDTO.setDateOfBirth("12-04-2023");
        return notificationRequestDTO;
    }

    private static ClientResponseDTO getClientResponseDTO() {
        ClientResponseDTO clientResponse = new ClientResponseDTO();
        clientResponse.setId(1L);
        clientResponse.setIdentificationType("DNI");
        clientResponse.setIdentificationNumber(123456789L);
        clientResponse.setFirstName("John");
        clientResponse.setLastName("Doe");
        clientResponse.setEmailAddress("johndoe@example.com");
        clientResponse.setDateOfBirth("1990-01-01");
        return clientResponse;
    }
    private static ClientUpdateRequestDTO getClientUpdateRequestDTO() {
        ClientUpdateRequestDTO clientUpdateRequest = new ClientUpdateRequestDTO();
        clientUpdateRequest.setId(1L);
        clientUpdateRequest.setIdentificationType("DNI");
        clientUpdateRequest.setIdentificationNumber(123456789L);
        clientUpdateRequest.setFirstName("John");
        clientUpdateRequest.setLastName("Doe");
        clientUpdateRequest.setEmailAddress("johndoe@example.com");
        clientUpdateRequest.setDateOfBirth("1990-01-01");
        return clientUpdateRequest;
    }
}