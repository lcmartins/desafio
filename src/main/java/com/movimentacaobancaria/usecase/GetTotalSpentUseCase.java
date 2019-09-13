package com.movimentacaobancaria.usecase;

import com.movimentacaobancaria.entities.BankingMovement;
import java.util.List;
import java.util.Map;

public class GetTotalSpentUseCase {
    public  Double get(List<BankingMovement> bankingMovements, Map<String, Double> expenses) {
        Double totalSpent = expenses
                .values()
                .stream()
                .reduce(0.0, (total, current) -> total + current);
        return totalSpent;
    }
}
