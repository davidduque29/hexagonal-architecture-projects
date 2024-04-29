package co.com.flypass.exception;


public class ValidationException extends RuntimeException {

    private final String statusCode;
    private final String technicalDetail;

    public ValidationException(String statusCode, String message, String technicalDetail) {
        super( message );
        this.statusCode = statusCode;
        this.technicalDetail = technicalDetail;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public String getTechnicalDetail() {
        return technicalDetail;
    }

    @Override
    public String toString() {
        return "ValidationException{" +
                "statusCode='" + statusCode + '\'' +
                ", technicalDetail='" + technicalDetail + '\'' +
                '}';
    }
}
