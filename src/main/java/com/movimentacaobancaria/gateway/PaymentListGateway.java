package com.movimentacaobancaria.gateway;

import com.movimentacaobancaria.entities.BankingMovement;
import com.movimentacaobancaria.entities.PaymentBankingMovement;
import com.movimentacaobancaria.helpers.PaymentMovementHelper;
import com.movimentacaobancaria.repository.IFileRepository;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PaymentListGateway implements PaymentList{

    private IFileRepository repository;

    public PaymentListGateway(IFileRepository fileRepository) {
        this.repository = fileRepository;
    }

    private String getIndexValue(String[] values, int index) {
        return values.length > index ? values[index] : "";
    }

    public List<BankingMovement> listPayments() throws IOException {
        List<String[]> fileResult = this.repository.readFile();
        List<BankingMovement> bankingMovementResult = new ArrayList<>();

        fileResult.stream().filter(line -> fileResult.indexOf(line) > 0 && line.length > 0).forEach(line -> {
            String rawValue = getIndexValue(line, 2);
            String descriptionValue = getIndexValue(line, 1);
            String rawDate = getIndexValue(line, 0);
            LocalDate sortableDate = PaymentMovementHelper.buildSortableDate(rawDate);

            BankingMovement bankingMovement;

            if (isReceipt(rawValue)) {
                bankingMovement = new BankingMovement.Builder(rawDate)
                        .withDescription(descriptionValue)
                        .withValue(rawValue)
                        .withMoeda()
                        .withSortableDate(sortableDate)
                        .build();
            } else {
                String rawCategory = getIndexValue(line, 3);
                bankingMovement = new PaymentBankingMovement.Builder(rawDate)
                        .withDescription(descriptionValue)
                        .withValue(rawValue)
                        .withCategoria(rawCategory)
                        .withMoeda()
                        .withSortableDate(sortableDate)
                        .build();
            }
            bankingMovementResult.add(bankingMovement);
        });
        return bankingMovementResult;
    }

    public boolean isReceipt(String rawValue) {
        return Double.parseDouble(rawValue.replace(".","").replace(",", ".")) > 0.0;
    }
}
