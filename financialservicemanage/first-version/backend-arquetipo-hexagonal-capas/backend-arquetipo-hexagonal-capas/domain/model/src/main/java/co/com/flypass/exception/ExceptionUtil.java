package co.com.flypass.exception;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExceptionUtil {
    public static final String LOAD_NOT_ACCEPTABLE_EXCEPTION_STATUS = "loadNotAcceptableException";
    public static final String LOAD_NOT_ACCEPTABLE_EXCEPTION_DETAIL = "Existen parámetros nulos o vacíos";
    public static final String LOAD_NOT_ACCEPTABLE_EXCEPTION_CODE = "406";

    public static final String LOAD_LENGTH_REQUIRED_EXCEPTION_STATUS = "LoadLengthRequiredException";
    public static final String LOAD_LENGTH_REQUIRED_EXCEPTION_DETAIL = "El campo no puede ser menor a dos caracteres";
    public static final String LOAD_LENGTH_REQUIRED_EXCEPTION_CODE = "400";

    public static final String INVALID_EMAIL_FORMAT_EXCEPTION_STATUS = "InvalidEmailFormatException";
    public static final String INVALID_EMAIL_FORMAT_EXCEPTION_DETAIL =  "El formato del correo no es válido";
    public static final String INVALID_EMAIL_FORMAT_EXCEPTION_CODE = "400";

    public static final String IS_A_MINOR_EXCEPTION_STATUS = "IsAMinorException";
    public static final String IS_A_MINOR_EXCEPTION_DETAIL = "La persona es menor de edad y no se puede registrar";
    public static final String IS_A_MINOR_EXCEPTION_CODE = "400";

    public static final String EXISTING_USER_EXCEPTION_STATUS = "ExistinUserException";
    public static final String EXISTING_USER_EXCEPTION_DETAIL = "La persona existe en la base de datos y no se puede registrar";
    public static final String EXISTING_USER_EXCEPTION_CODE = "400";

}
