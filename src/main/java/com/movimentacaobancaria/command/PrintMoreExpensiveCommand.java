package com.movimentacaobancaria.command;

import com.movimentacaobancaria.helpers.PaymentMovementHelper;
import com.movimentacaobancaria.usecase.ExpensesSumaryUseCase;
import com.movimentacaobancaria.usecase.GetMoreExpensiveCategoryUseCase;
import com.movimentacaobancaria.usecase.Strategy.GroupExpenseStrategy;
import com.movimentacaobancaria.entities.BankingMovement;
import javafx.util.Pair;

import java.util.List;
import java.util.Map;

public class PrintMoreExpensiveCommand extends BankingMovementCommand {
    private static final String TOTAL_LABEL = "TOTAL:            ";
    private GroupExpenseStrategy groupExpenseStrategy;

    public PrintMoreExpensiveCommand(GroupExpenseStrategy groupExpenseStrategy){
        this.groupExpenseStrategy = groupExpenseStrategy;
    }

    @Override
    public void execute() throws Exception {
        List<BankingMovement> bankingMovements = getBankingMovements();
        Map<String, Double> expenses = getExpensesGroupedByCategory(bankingMovements, this.groupExpenseStrategy);
        Pair<String, Double> moreExpensiveCategory = new GetMoreExpensiveCategoryUseCase().collect(expenses);
        PaymentMovementHelper.printHeader("    " + groupExpenseStrategy.getCategoryDescription() + moreExpensiveCategory.getKey());
        PaymentMovementHelper.print(TOTAL_LABEL + moreExpensiveCategory.getValue());
    }

    private static Map<String, Double> getExpensesGroupedByCategory(List<BankingMovement> bankingMovements, GroupExpenseStrategy groupExpenseStrategy) {
        return new ExpensesSumaryUseCase().group(bankingMovements, groupExpenseStrategy);
    }
}
