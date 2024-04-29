package co.com.flypass.jpa.postgresql.entities;

import jakarta.persistence.*;

import java.util.Objects;


@Entity
@Table(name = "client")
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator", sequenceName = "id_generator", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "identification_type", nullable = false)
    private String identificationType;

    @Column(name = "identification_number", nullable = false)
    private Long identificationNumber;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email_address", nullable = false)
    private String emailAddress;

    @Column(name = "dateOf_birth", nullable = false)
    private String dateOfBirth;

    @Column(name = "creation_date", nullable = false)
    private String creationDate;

    @Column(name = "modification_date", nullable = true)
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClientEntity that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(identificationType, that.identificationType) &&
                Objects.equals(identificationNumber, that.identificationNumber) && Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) && Objects.equals(emailAddress, that.emailAddress) &&
                Objects.equals(dateOfBirth, that.dateOfBirth) && Objects.equals(creationDate, that.creationDate) &&
                Objects.equals(modificationDate, that.modificationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, identificationType, identificationNumber, firstName,
                lastName, emailAddress, dateOfBirth, creationDate, modificationDate);
    }
}
