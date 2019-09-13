package com.movimentacaobancaria.usecase;

import com.movimentacaobancaria.gateway.PaymentListGateway;
import com.movimentacaobancaria.repository.IFileRepository;
import com.movimentacaobancaria.usecase.Strategy.GroupExpenseStrategy;
import com.movimentacaobancaria.entities.BankingMovement;
import com.movimentacaobancaria.entities.PaymentBankingMovement;
import javafx.util.Pair;
import java.text.Normalizer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpensesSumaryUseCase {

    private static final String UNCATEGORIZED_IDENTIFIER = "UNCATEGORIZED";
    private PaymentListGateway paymentListGateway;
    private IFileRepository fileRepository;
    private List<BankingMovement> movements;

    public ExpensesSumaryUseCase(IFileRepository fileRepository) {
        this.fileRepository = fileRepository;
        this.paymentListGateway = new PaymentListGateway(this.fileRepository);
    }

    public Map<String, Double> group(GroupExpenseStrategy groupExpenseStrategy) throws Exception {
        movements = this.paymentListGateway.listPayments();
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
