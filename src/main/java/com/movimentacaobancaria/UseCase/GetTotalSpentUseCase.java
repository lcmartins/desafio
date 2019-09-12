package com.movimentacaobancaria.UseCase;

import com.movimentacaobancaria.entities.BankingMovement;

import java.util.List;

public class GetTotalSpentUseCase {

    public Double getTotalTransactions(List<BankingMovement> bankingMovements ) {
        Double total = 0.0;
        for (BankingMovement payment: bankingMovements) {
            total += Math.abs(payment.getValor());
        }
        return total;
    }
}
