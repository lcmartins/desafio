package com.movimentacaobancaria.UseCase.Strategy;

import com.movimentacaobancaria.entities.BankingMovement;

public interface GroupExpenseStrategy {
    String getKey(BankingMovement bankingMovement);
    String getCategoryDescription();
}
