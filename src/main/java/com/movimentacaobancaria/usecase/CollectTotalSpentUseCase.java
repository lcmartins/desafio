package com.movimentacaobancaria.usecase;

import com.movimentacaobancaria.repository.IFileRepository;
import com.movimentacaobancaria.usecase.Strategy.GroupExpenseStrategy;

import java.util.Map;

public class CollectTotalSpentUseCase extends ExpensesSumaryUseCase {

    public CollectTotalSpentUseCase(IFileRepository fileRepository) {
        super(fileRepository);
    }

    public Double collectTotalSpent(GroupExpenseStrategy groupExpenseStrategy) throws Exception {
        Map<String, Double> expenses = this.group(groupExpenseStrategy);
        return expenses
                .values()
                .stream()
                .reduce(0.0, (total, current) -> total + current);
    }
}
