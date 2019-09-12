package com.movimentacaobancaria.UseCase.Strategy;

import com.movimentacaobancaria.entities.BankingMovement;

public class GroupByMonthStrategy implements GroupExpenseStrategy {
    @Override
    public String getKey(BankingMovement bankingMovement) {
        String[] dateParts = bankingMovement.getData().split("-");
        return dateParts[1].toUpperCase();
    }

    @Override
    public String getCategoryDescription() {
        return "NOME DO MES COM O MAIOR GASTO: ";
    }
}
