package tech.jes.smartstock.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public abstract class SmartStockException extends RuntimeException {

    public SmartStockException(String message) {
        super(message);
    }

    public SmartStockException(Throwable cause) {
        super(cause);
    }

    public ProblemDetail toProblemDetail() {
        var pd = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);

        pd.setTitle("Smart Stock Internal Server Error");
        pd.setDetail("Contact SmartStock Support");

        return pd;
    }

}