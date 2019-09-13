package com.movimentacaobancaria.UseCase;

import com.movimentacaobancaria.UseCase.Strategy.GroupExpenseStrategy;
import com.movimentacaobancaria.entities.BankingMovement;
import com.movimentacaobancaria.entities.PaymentBankingMovement;
import java.text.Normalizer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpensesSumaryUseCase {

    private static final String UNCATEGORIZED_IDENTIFIER = "UNCATEGORIZED";

    public Map<String, Double> group(List<BankingMovement> movements, GroupExpenseStrategy groupExpenseStrategy) {
        Map<String, Double> expenses = new HashMap<>();

        movements.stream().forEach(movement->{
            if(isPayment(movement)) {
                PaymentBankingMovement paymentBankingMovement = (PaymentBankingMovement)movement;
                String key = groupExpenseStrategy.getKey(paymentBankingMovement);
                key = Normalizer
                        .normalize(key, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "")
                        .trim()
                        .toLowerCase();
                if(key.trim().length() == 0) {
                    key = UNCATEGORIZED_IDENTIFIER;
                }
                if(!expenses.containsKey(key)) {
                    expenses.put(key, paymentBankingMovement.getValor());
                } else {
                    Double currentValue = expenses.get(key) + paymentBankingMovement.getValor();
                    expenses.replace(key, currentValue);
                }
            }
        });

        return expenses;
    }

    private boolean isPayment(BankingMovement movement) {
        return movement.getValor() < 0.0;
    }
}
