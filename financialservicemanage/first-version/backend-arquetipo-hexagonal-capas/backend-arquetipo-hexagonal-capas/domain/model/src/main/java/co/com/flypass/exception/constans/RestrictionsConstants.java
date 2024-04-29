package co.com.flypass.exception.constans;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RestrictionsConstants {
    public static final int LIMIT_DOCUMENT_TYPE = 12;
    public static final int LIMIT_MIN_NAME= 2;
    public static final int LIMIT_MAX_DOCUMENT_NUMBER = 15;

}

