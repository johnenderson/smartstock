package tech.jes.smartstock.service;

import org.springframework.stereotype.Service;
import tech.jes.smartstock.domain.CsvStockItem;
import tech.jes.smartstock.entity.PurchaseRequestEntity;
import tech.jes.smartstock.exception.FileNotFoundException;
import tech.jes.smartstock.repository.PurchaseRequestRepository;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

@Service
public class SmartStockService {

    private final ReportService reportService;
    private final PurchaseSectorService purchaseSectorService;
    private final PurchaseRequestRepository purchaseRequestRepository;

    public SmartStockService(ReportService reportService, PurchaseSectorService purchaseSectorService, PurchaseRequestRepository purchaseRequestRepository) {
        this.reportService = reportService;
        this.purchaseSectorService = purchaseSectorService;
        this.purchaseRequestRepository = purchaseRequestRepository;
    }

    public void start(String reportPath) {

        fileExists(reportPath);


        try {
            // 1. ler o arquivo csv
            var items = reportService.readStockReport(reportPath);

            items.forEach(item -> {
                if (item.getQuantity() < item.getReorderThreshold()) {

                    // 1. calcular a quantidade a ser recomprada
                    var reorderQuantity = calculateReorderQuantity(item);

                    // 2. para cada item do csv chamar a api do setor de compras
                    var purchasedWithSuccess = purchaseSectorService.sendPurchaseRequest(item, reorderQuantity);

                    // 3. salvar no mongodb os itens que foram comprados
                    persist(item, reorderQuantity, purchasedWithSuccess);

                }
            });

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    private void persist(CsvStockItem item,
                         Integer reorderQuantity,
                         boolean purchasedWithSuccess) {

        var entity = new PurchaseRequestEntity();
        entity.setItemId(item.getItemId());
        entity.setItemName(item.getItemName());
        entity.setQuantityOnStock(item.getQuantity());
        entity.setReorderThreshold(item.getReorderThreshold());
        entity.setSupplierName(item.getSupplierName());
        entity.setSupplierEmail(item.getSupplierEmail());
        entity.setLastStockUpdateTime(LocalDateTime.parse(item.getLastStockUpdateTime()));

        entity.setPurchaseQuantity(reorderQuantity);
        entity.setPurchasedWithSuccess(purchasedWithSuccess);
        entity.setPurchaseDateTime(LocalDateTime.now());

        purchaseRequestRepository.save(entity);
    }

    private Integer calculateReorderQuantity(CsvStockItem item) {
        return item.getReorderThreshold() + ((int) Math.ceil(item.getReorderThreshold() * 0.2));
    }

    private void fileExists(String reportPath) {
        File file = new File(reportPath);
        boolean exists = file.exists();

        if (!exists) {
            throw new FileNotFoundException("File not found in path: " + reportPath);
        }
    }
}
