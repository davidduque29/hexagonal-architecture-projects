package co.com.flypass.models.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomerConstants {
    public static final String EMAIL_REGEX =
            "^[a-zA-Z0-9_+&*\\-.]+(?:\\.[a-zA-Z0-9_+&*\\-.]+)*@(?:[a-zA-Z0-9]+\\.)+[a-zA-Z]{2,7}$";
}
