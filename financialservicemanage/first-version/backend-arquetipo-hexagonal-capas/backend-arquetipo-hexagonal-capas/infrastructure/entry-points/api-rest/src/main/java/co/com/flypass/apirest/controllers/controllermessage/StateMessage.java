package co.com.flypass.apirest.controllers.controllermessage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class StateMessage {
    int statusCode;
    String status;
    String title;
    String detail;

}
