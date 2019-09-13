package com.movimentacaobancaria.usecase;

import com.movimentacaobancaria.entities.BankingMovement;
import com.movimentacaobancaria.gateway.PaymentListGateway;
import com.movimentacaobancaria.repository.IFileRepository;

import java.util.List;


public class GetTotalTransactionsUseCase {
    private PaymentListGateway paymentListGateway;
    private IFileRepository fileRepository;

    public GetTotalTransactionsUseCase(IFileRepository fileRepository) {
        this.paymentListGateway = new PaymentListGateway(fileRepository);
    }

    public Double getTotalTransactios() throws Exception {
        List<BankingMovement> bankingMovements = this.paymentListGateway.listPayments();
        double total = 0.0;
        for (BankingMovement payment: bankingMovements) {
            total += Math.abs(payment.getValor());
        }
        return total;
    }
}
