package com.movimentacaobancaria.UseCase;

import com.movimentacaobancaria.entities.BankingMovement;
import com.movimentacaobancaria.entities.PaymentBankingMovement;
import com.movimentacaobancaria.repository.FileRepository;
import com.movimentacaobancaria.repository.IFileRepository;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PaymentListUseCase {
    private static final String YEAR_BASE = "2019";
    private static final String DATE_SEPARATOR = "-";
    private IFileRepository repository;

    public PaymentListUseCase(IFileRepository fileRepository) {
        this.repository = fileRepository;
    }

    private String getIndexValue(String[] values, int index) {
        return values.length > index ? values[index] : "";
    }

    public LocalDate buildSortableDate(String data) {
        String[] dateParts = data.split("-");
        String dia = dateParts[0];
        String mes = dateParts[1];
        StringBuilder dataBuilder = new StringBuilder();
        dataBuilder.append(YEAR_BASE)
                .append(DATE_SEPARATOR);

        switch (mes.toUpperCase()) {
            case "JAN":
                dataBuilder.append("01").append(DATE_SEPARATOR);
                break;
            case "FEB":
                dataBuilder.append("02").append(DATE_SEPARATOR);
                break;
            case "MAR":
                dataBuilder.append("03").append(DATE_SEPARATOR);
                break;
            case "APR":
                dataBuilder.append("04").append(DATE_SEPARATOR);
                break;
            case "MAY":
                dataBuilder.append("05").append(DATE_SEPARATOR);
                break;
            case "JUN":
                dataBuilder.append("06").append(DATE_SEPARATOR);
                break;
            case "JUL":
                dataBuilder.append("07").append(DATE_SEPARATOR);
                break;
            case "AUG":
                dataBuilder.append("08").append(DATE_SEPARATOR);
                break;
            case "SEP":
                dataBuilder.append("09").append(DATE_SEPARATOR);
                break;
            case "OCT":
                dataBuilder.append("10").append(DATE_SEPARATOR);
                break;
            case "NOV":
                dataBuilder.append("11").append(DATE_SEPARATOR);
                break;
                default:
                    dataBuilder.append("12").append(DATE_SEPARATOR);
                    break;
        }
        dataBuilder.append(dia);
        return LocalDate.parse(dataBuilder.toString());
    }

    public List<BankingMovement> getData() throws IOException {
        List<String[]> fileResult = this.repository.readFile();
        List<BankingMovement> bankingMovementResult = new ArrayList<>();

        fileResult.stream().filter(line -> fileResult.indexOf(line) > 0 && line.length > 0).forEach(line -> {
            String rawValue = getIndexValue(line, 2);
            String descriptionValue = getIndexValue(line, 1);
            String rawDate = getIndexValue(line, 0);
            LocalDate sortableDate = this.buildSortableDate(rawDate);

            BankingMovement bankingMovement;

            if (isMovimentacaoPagamento(rawValue)) {
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
         Collections.sort(bankingMovementResult);
        return bankingMovementResult;
    }

    public boolean isMovimentacaoPagamento(String rawValue) {
        return Double.parseDouble(rawValue.replace(".","").replace(",", ".")) > 0.0;
    }
}
