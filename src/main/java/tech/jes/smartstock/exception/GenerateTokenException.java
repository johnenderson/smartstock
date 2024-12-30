package tech.jes.smartstock.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class GenerateTokenException extends SmartStockException {

    private final String detail;

    public GenerateTokenException(String detail) {
        super(detail);
        this.detail = detail;
    }

    @Override
    public ProblemDetail toProblemDetail() {
        var pd = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);

        pd.setTitle("Generate Token Exception");
        pd.setDetail(detail);

        return super.toProblemDetail();
    }
}
