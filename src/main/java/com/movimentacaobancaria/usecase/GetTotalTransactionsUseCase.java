package com.movimentacaobancaria.usecase;

import com.movimentacaobancaria.entities.BankingMovement;

import java.util.List;

public class GetTotalTransactionsUseCase {

    public Double get(List<BankingMovement> bankingMovements ) {
        Double total = 0.0;
        for (BankingMovement payment: bankingMovements) {
            total += Math.abs(payment.getValor());
        }
        return total;
    }
}
