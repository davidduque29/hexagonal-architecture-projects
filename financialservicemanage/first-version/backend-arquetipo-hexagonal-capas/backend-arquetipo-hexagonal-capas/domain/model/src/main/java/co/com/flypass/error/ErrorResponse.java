package co.com.flypass.error;

public class ErrorResponse {

    private String statusCode;
    private String message;
    private String technicalDetail;

    public ErrorResponse() {
    }

    public ErrorResponse(String statusCode, String message, String technicalDetail) {
        this.statusCode = statusCode;
        this.message = message;
        this.technicalDetail = technicalDetail;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTechnicalDetail() {
        return technicalDetail;
    }

    public void setTechnicalDetail(String technicalDetail) {
        this.technicalDetail = technicalDetail;
    }

    public static ErrorResponseBuilder builder() {
        return new ErrorResponseBuilder();
    }

    public ErrorResponseBuilder toBuilder() {
        return new ErrorResponseBuilder().statusCode(this.statusCode).message(this.message).technicalDetail(this.technicalDetail);
    }


    public static class ErrorResponseBuilder {
        private String statusCode;
        private String message;
        private String technicalDetail;

        ErrorResponseBuilder() {
        }

        public ErrorResponseBuilder statusCode(String statusCode) {
            this.statusCode = statusCode;
            return this;
        }

        public ErrorResponseBuilder message(String message) {
            this.message = message;
            return this;
        }

        public ErrorResponseBuilder technicalDetail(String technicalDetail) {
            this.technicalDetail = technicalDetail;
            return this;
        }

        public ErrorResponse build() {
            return new ErrorResponse(this.statusCode, this.message, this.technicalDetail);
        }

        public String toString() {
            return "ErrorResponse.ErrorResponseBuilder(statusCode=" + this.statusCode + ", message=" + this.message + ", technicalDetail=" + this.technicalDetail + ")";
        }
    }

}
