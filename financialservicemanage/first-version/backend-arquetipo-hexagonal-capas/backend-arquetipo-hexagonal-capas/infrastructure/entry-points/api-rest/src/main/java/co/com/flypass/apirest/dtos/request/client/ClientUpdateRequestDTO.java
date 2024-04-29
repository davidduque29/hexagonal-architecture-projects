package co.com.flypass.apirest.dtos.request.client;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public class ClientUpdateRequestDTO {

    private Long id;
    @Valid
    @NotNull
    private String identificationType;
    private Long identificationNumber;
    @Valid
    @NotNull
    @Size(min=2,max=20)
    private String firstName;
    @Valid
    @NotNull
    @Size(min=2,max=20)
    private String lastName;
    @Valid
    @NotNull
    @Email
    private String emailAddress;
    private String dateOfBirth;
    private String creationDate;
    private String modificationDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdentificationType() {
        return identificationType;
    }

    public void setIdentificationType(String identificationType) {
        this.identificationType = identificationType;
    }

    public Long getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(Long identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(String modificationDate) {
        this.modificationDate = modificationDate;
    }
}
