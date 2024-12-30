package tech.jes.smartstock.service;

import org.springframework.stereotype.Service;
import tech.jes.smartstock.domain.CsvStockItem;

@Service
public class PurchaseSectorService {

    private final AuthService authService;

    public PurchaseSectorService(AuthService authService) {
        this.authService = authService;
    }

    public boolean sendPurchaseRequest(CsvStockItem item,
                                       Integer purchaseQuantity) {
        // 1. autenticacao na api para recuperar o token

        // 2. enviar solicitacao de compra com o token gerado na chamada anterior

        // 3. validar se a resposta veio com sucesso.
    }
}
