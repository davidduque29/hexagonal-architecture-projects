package co.com.flypass.models;


import co.com.flypass.exception.BussinessException;
import co.com.flypass.exception.ExceptionUtil;
import co.com.flypass.exception.constans.RestrictionsConstants;
import co.com.flypass.models.constants.CustomerConstants;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Customer {
    private Long id;
    private String tipoIdentificacion;
    private String numeroIdentificacion;
    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;
    private String correoElectronico;
    private Date fechaNacimiento;
    private Date fechaCreacion;
    private Date fechaModificacion;

    public void setId(Long id) {
        this.id = id;
    }

    public void setTipoIdentificacion(String tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public Long getId() {
        return id;
    }

    public String getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void validCustomer() {
        this.isNotNullOrEmpty();
        this.isValidateLenght();
        this.isValidEmail();
        this.isUnderAge();

    }

    private void isNotNullOrEmpty() {
        if (this.tipoIdentificacion == null || this.numeroIdentificacion == null || this.fechaNacimiento == null
                || this.tipoIdentificacion.isBlank() || this.numeroIdentificacion.isBlank()) {
            throw new BussinessException(ExceptionUtil.LOAD_NOT_ACCEPTABLE_EXCEPTION_STATUS
                    , ExceptionUtil.LOAD_NOT_ACCEPTABLE_EXCEPTION_DETAIL
                    , ExceptionUtil.LOAD_NOT_ACCEPTABLE_EXCEPTION_CODE);

        }

    }

    public boolean isValidateLenght() throws BussinessException {
        if (this.primerNombre.length() < RestrictionsConstants.LIMIT_MIN_NAME ||
                this.segundoNombre.length() < RestrictionsConstants.LIMIT_MIN_NAME ||
                this.primerApellido.length() < RestrictionsConstants.LIMIT_MIN_NAME ||
                this.segundoApellido.length() < RestrictionsConstants.LIMIT_MIN_NAME) {
            throw new BussinessException(ExceptionUtil.LOAD_LENGTH_REQUIRED_EXCEPTION_STATUS,
                    ExceptionUtil.LOAD_LENGTH_REQUIRED_EXCEPTION_DETAIL,
                    ExceptionUtil.LOAD_LENGTH_REQUIRED_EXCEPTION_CODE);
        }

        return true;
    }

    public boolean isValidEmail() throws BussinessException {
        if (!validateFormatEmail(this.correoElectronico)) {
            throw new BussinessException(ExceptionUtil.INVALID_EMAIL_FORMAT_EXCEPTION_STATUS,
                    ExceptionUtil.INVALID_EMAIL_FORMAT_EXCEPTION_DETAIL,
                    ExceptionUtil.INVALID_EMAIL_FORMAT_EXCEPTION_CODE);
        }

        return true;
    }

    public boolean validateFormatEmail(String email) {
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        return matcher.matches();
    }

    private static final Pattern EMAIL_PATTERN = Pattern.compile(CustomerConstants.EMAIL_REGEX);

    public boolean isUnderAge() throws BussinessException {
        if (isUnderAgeValidation(this.fechaNacimiento)) {
            throw new BussinessException(ExceptionUtil.IS_A_MINOR_EXCEPTION_STATUS,
                    ExceptionUtil.IS_A_MINOR_EXCEPTION_DETAIL,
                    ExceptionUtil.IS_A_MINOR_EXCEPTION_CODE);
        }
        return true;
    }

    private boolean isUnderAgeValidation(Date fechaNacimiento) {
        return validateAgeLimit(fechaNacimiento);
    }

    public boolean validateAgeLimit(Date bithDate) {
        Date currentDate = new Date(System.currentTimeMillis());
        long edadMillis = currentDate.getTime() - bithDate.getTime();
        long age = edadMillis / (1000L * 60 * 60 * 24 * 365);
        return age < 18;
    }

    public static CustomerBuilder builder() {
        return new CustomerBuilder();
    }

    public static class CustomerBuilder {
        private Long id;
        private String tipoIdentificacion;
        private String numeroIdentificacion;
        private String primerNombre;
        private String segundoNombre;
        private String primerApellido;
        private String segundoApellido;
        private String correoElectronico;
        private Date fechaNacimiento;
        private Date fechaCreacion;
        private Date fechaModificacion;

        CustomerBuilder() {
        }

        public CustomerBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public CustomerBuilder tipoIdentificacion(String tipoIdentificacion) {
            this.tipoIdentificacion = tipoIdentificacion;
            return this;
        }

        public CustomerBuilder numeroIdentificacion(String numeroIdentificacion) {
            this.numeroIdentificacion = numeroIdentificacion;
            return this;
        }

        public CustomerBuilder primerNombre(String primerNombre) {
            this.primerNombre = primerNombre;
            return this;
        }

        public CustomerBuilder segundoNombre(String segundoNombre) {
            this.segundoNombre = segundoNombre;
            return this;
        }

        public CustomerBuilder primerApellido(String primerApellido) {
            this.primerApellido = primerApellido;
            return this;
        }

        public CustomerBuilder segundoApellido(String segundoApellido) {
            this.segundoApellido = segundoApellido;
            return this;
        }

        public CustomerBuilder correoElectronico(String correoElectronico) {
            this.correoElectronico = correoElectronico;
            return this;
        }

        public CustomerBuilder fechaNacimiento(Date fechaNacimiento) {
            this.fechaNacimiento = fechaNacimiento;
            return this;
        }

        public CustomerBuilder fechaCreacion(Date fechaCreacion) {
            this.fechaCreacion = fechaCreacion;
            return this;
        }

        public CustomerBuilder fechaModificacion(Date fechaModificacion) {
            this.fechaModificacion = fechaModificacion;
            return this;
        }

        public Customer build() {
            Customer customer;
            customer = new Customer();
            customer.id = this.id;
            customer.tipoIdentificacion = this.tipoIdentificacion;
            customer.numeroIdentificacion = this.numeroIdentificacion;
            customer.primerNombre = this.primerNombre;
            customer.segundoNombre = this.segundoNombre;
            customer.primerApellido = this.primerApellido;
            customer.segundoApellido = this.segundoApellido;
            customer.correoElectronico = this.correoElectronico;
            customer.fechaNacimiento = this.fechaNacimiento;
            customer.fechaCreacion = this.fechaCreacion;
            customer.fechaModificacion = this.fechaModificacion;
            return customer;
        }

    }
}
