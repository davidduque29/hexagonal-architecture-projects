package co.com.flypass.apirest.controllers.customer.headers;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomerApiHeadersBuilder {

    public static HttpHeaders buildHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer your_access_token ECITOSO");
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Accept", "application/json PROBANDO");
        headers.add("User-Agent", "QuickFinances/1.0");
        headers.add("Cache-Control", "no-cache");

        return headers;
    }

}
