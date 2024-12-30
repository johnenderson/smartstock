package tech.jes.smartstock.service;

import org.springframework.stereotype.Service;
import tech.jes.smartstock.client.AuthClient;
import tech.jes.smartstock.client.dto.AuthRequest;
import tech.jes.smartstock.config.SmartStockConfig;
import tech.jes.smartstock.exception.GenerateTokenException;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class AuthService {

    private static final String GRANT_TYPE = "client_credentials";
    private static String token;
    private static LocalDateTime expiresIn;

    private final AuthClient authClient;
    private final SmartStockConfig smartStockConfig;

    public AuthService(AuthClient authClient,
                       SmartStockConfig smartStockConfig) {
        this.authClient = authClient;
        this.smartStockConfig = smartStockConfig;
    }

    public String getToken() {
        if (Objects.isNull(token)) {
            generateToken();
        } else if (expiresIn.isBefore(LocalDateTime.now())) {
            generateToken();
        }

        return token;
    }

    private void generateToken() {

        var request = new AuthRequest(
                GRANT_TYPE,
                smartStockConfig.getClientId(),
                smartStockConfig.getClientSecret()
        );

        var response = authClient.authenticate(request);
        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new GenerateTokenException("cannot generate token, " +
                    "status: " + response.getStatusCode() +
                    "response " + response.getBody());
        }

        token = response.getBody().accessToken();
        expiresIn = LocalDateTime.now().plusSeconds(response.getBody().expiresIn());
    }
}
