package com.movimentacaobancaria.usecase;

import com.movimentacaobancaria.entities.BankingMovement;
import com.movimentacaobancaria.gateway.PaymentListGateway;
import com.movimentacaobancaria.repository.IFileRepository;

import java.util.List;


public class CollectTotalTransactionsUseCase {
    private PaymentListGateway paymentListGateway;
    private IFileRepository fileRepository;

    public CollectTotalTransactionsUseCase(IFileRepository fileRepository) {
        this.paymentListGateway = new PaymentListGateway(fileRepository);
    }

    public Double get() throws Exception {
        List<BankingMovement> bankingMovements = this.paymentListGateway.listPayments();
        double total = 0.0;
        for (BankingMovement payment: bankingMovements) {
            total += Math.abs(payment.getValor());
        }
        return total;
    }
}
