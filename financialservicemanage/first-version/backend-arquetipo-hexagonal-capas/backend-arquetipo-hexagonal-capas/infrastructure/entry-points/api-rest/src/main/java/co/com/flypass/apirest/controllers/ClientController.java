package co.com.flypass.apirest.controllers;

import co.com.flypass.apirest.dtos.request.client.ClientRequestDTO;
import co.com.flypass.apirest.dtos.request.client.ClientUpdateRequestDTO;
import co.com.flypass.apirest.dtos.response.ClientResponseDTO;
import co.com.flypass.apirest.handlers.IClientHandler;
import co.com.flypass.constants.Constants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1/client", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClientController {

    private final IClientHandler clientHandler;

    public ClientController(IClientHandler clientHandler) {
        this.clientHandler = clientHandler;
    }

    @Operation(
            summary = "Crear un cliente",
            responses = {
                    @ApiResponse(responseCode = "201", description = "cliente creado",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ClientResponseDTO.class, description = "respuesta de la solicitud"))),
                    @ApiResponse(responseCode = "422", description = "no se pudo procesar la operacion de guardar",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error")))
            })
    @PostMapping
    public ResponseEntity<ClientResponseDTO> add(@Valid @RequestBody ClientRequestDTO clientRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clientHandler.save(clientRequestDTO));
    }

    @Operation(summary = "actualizar cliente",
            responses = {
                    @ApiResponse(responseCode = "200", description = "client created",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ClientResponseDTO.class, description = "respuesta de la solicitud"))),
                    @ApiResponse(responseCode = "422", description = "no se pudo procesar la operacion de actualizar",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error"))
                    )
            })
    @PutMapping(path = "/update")
    public ResponseEntity<ClientResponseDTO> update(@Valid @RequestBody ClientUpdateRequestDTO clientUpdateRequestDTO) {

        return ResponseEntity.status(HttpStatus.OK).body(clientHandler.update(clientUpdateRequestDTO));
    }

    @Operation(summary = "Elimina un cliente por ID",
            parameters = {
                    @Parameter(name = "clientId", in = ParameterIn.PATH, required = true, description = "ID del cliente")
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "cliente eliminado",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map"))),
                    @ApiResponse(responseCode = "422", description = "no se pudo procesar la operacion de eliminado",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error"))
                    )
            })
    @DeleteMapping("{clientId}")
    public ResponseEntity<Map<String,String>> deleteUserById(@PathVariable Long clientId){
        clientHandler.deleteClientById(clientId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(Collections.singletonMap(Constants.RESPONSE_MESSAGE_KEY,Constants.CLIENT_DELETED_MESSAGE));
    }

}
