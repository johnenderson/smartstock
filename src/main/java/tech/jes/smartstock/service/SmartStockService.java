package tech.jes.smartstock.service;

import org.springframework.stereotype.Service;
import tech.jes.smartstock.domain.CsvStockItem;
import tech.jes.smartstock.exception.FileNotFoundException;

import java.io.File;
import java.io.IOException;

@Service
public class SmartStockService {

    private final ReportService reportService;

    public SmartStockService(ReportService reportService) {
        this.reportService = reportService;
    }

    public void start(String reportPath) {

        fileExists(reportPath);


        try {
            // 1. ler o arquivo csv
            var items = reportService.readStockReport(reportPath);

            items.forEach(item -> {
                if (item.getQuantity() < item.getReorderThreshold()) {

                    // 2. calcular a quantidade a ser recomprada
                    var reorderQuantity = calculateReorderQuantity(item);

                    // 3. para cada item do csv chamar a api do setor de compras

                    // 4. salvar no mongodb os itens que foram comprados

                }
            });

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


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
