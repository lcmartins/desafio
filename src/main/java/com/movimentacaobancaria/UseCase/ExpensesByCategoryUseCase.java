package com.movimentacaobancaria.UseCase;

import com.movimentacaobancaria.entities.BankingMovement;
import com.movimentacaobancaria.entities.PaymentBankingMovement;

import java.io.IOException;
import java.text.Normalizer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpensesByCategoryUseCase {

    private static final String UNCATEGORIZED_IDENTIFIER = "UNCATEGORIZED";

    public Map<String, Double> reduce(List<BankingMovement> movements) {
        Map<String, Double> expenses = new HashMap<>();
        movements.stream().forEach(movement->{
            if(movement.getValor() < 0.0) {
                PaymentBankingMovement paymentBankingMovement = (PaymentBankingMovement)movement;
                String categoryKey = paymentBankingMovement.getCategoria();
                categoryKey = Normalizer
                        .normalize(categoryKey, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "")
                        .trim()
                        .toLowerCase();
                if(categoryKey.trim().length() == 0) {
                    categoryKey = UNCATEGORIZED_IDENTIFIER;
                }
                if(!expenses.containsKey(categoryKey)) {
                    expenses.put(categoryKey, paymentBankingMovement.getValor());
                } else {
                    Double currentValue = expenses.get(categoryKey) + paymentBankingMovement.getValor();
                    expenses.replace(categoryKey, currentValue);
                }
            }
        });
        return expenses;
    }
}
