package com.movimentacaobancaria.usecase;

import com.movimentacaobancaria.repository.IFileRepository;
import com.movimentacaobancaria.usecase.Strategy.GroupExpenseStrategy;
import javafx.util.Pair;

import java.util.Map;

public class CollectMoreExpensiveUseCase extends ExpensesSumaryUseCase {

    public CollectMoreExpensiveUseCase(IFileRepository fileRepository) {
        super(fileRepository);
    }

    public Pair<String, Double> get(GroupExpenseStrategy groupExpenseStrategy) throws Exception {
        Map<String, Double> expenses = this.groupPayments(groupExpenseStrategy);
        Pair<String, Double> moreExpensiveCategory = new Pair("", 0.0);
        for (Map.Entry<String, Double> category : expenses.entrySet()) {
            String key = category.getKey().toUpperCase();
            Double value = category.getValue();
            if(value < moreExpensiveCategory.getValue()) {
                moreExpensiveCategory = new Pair<>(key, value);
            }
        }
        return moreExpensiveCategory;
    }
}
