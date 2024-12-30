package tech.jes.smartstock.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class FileNotFoundException extends SmartStockException {

    private final String detail;

    public FileNotFoundException(String detail) {
        super(detail);
        this.detail = detail;
    }

    @Override
    public ProblemDetail toProblemDetail() {
        var pd = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);

        pd.setTitle("File NotFound Exception");
        pd.setDetail(detail);

        return super.toProblemDetail();
    }
}
