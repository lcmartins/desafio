package com.movimentacaobancaria.usecase;

import com.movimentacaobancaria.gateway.PaymentListGateway;
import com.movimentacaobancaria.entities.BankingMovement;
import com.movimentacaobancaria.repository.IFileRepository;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class PaymentListUseCase {
    private IFileRepository repository;
    PaymentListGateway paymentListGateway;
    public PaymentListUseCase(IFileRepository fileRepository) {
        this.repository = fileRepository;
        this.paymentListGateway = new PaymentListGateway(this.repository);
    }

    public List<BankingMovement> listPayments() throws IOException {
        List<BankingMovement> bankingMovementResult = this.paymentListGateway.listPayments();
        Collections.sort(bankingMovementResult, Collections.reverseOrder());
        return bankingMovementResult;
    }

    public boolean isReceipt(String rawValue) {
        return Double.parseDouble(rawValue.replace(".","").replace(",", ".")) > 0.0;
    }
}
