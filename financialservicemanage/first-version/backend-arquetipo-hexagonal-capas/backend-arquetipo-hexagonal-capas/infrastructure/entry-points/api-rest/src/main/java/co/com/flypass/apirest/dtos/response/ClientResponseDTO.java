package co.com.flypass.apirest.dtos.response;


import java.util.Objects;

public class ClientResponseDTO {
    private Long id;
    private String identificationType;
    private Long identificationNumber;
    private String firstName;
    private String lastName;
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

    @Override
    public String toString() {
        return "ClientResponseDTO{" +
                "dateOfBirth='" + dateOfBirth + '\'' +
                ", creationDate='" + creationDate + '\'' +
                ", modificationDate='" + modificationDate + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", identificationNumber=" + identificationNumber +
                ", identificationType='" + identificationType + '\'' +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClientResponseDTO that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(identificationType, that.identificationType) && Objects.equals(identificationNumber, that.identificationNumber) && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(emailAddress, that.emailAddress) && Objects.equals(dateOfBirth, that.dateOfBirth) && Objects.equals(creationDate, that.creationDate) && Objects.equals(modificationDate, that.modificationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, identificationType, identificationNumber, firstName, lastName, emailAddress, dateOfBirth, creationDate, modificationDate);
    }
}
