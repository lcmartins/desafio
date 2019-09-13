package com.movimentacaobancaria.Command;

import com.movimentacaobancaria.Helpers.PaymentMovementHelper;
import com.movimentacaobancaria.UseCase.ExpensesSumaryUseCase;
import com.movimentacaobancaria.UseCase.GetMoreExpensiveCategoryUseCase;
import com.movimentacaobancaria.UseCase.Strategy.GroupExpenseStrategy;
import com.movimentacaobancaria.entities.BankingMovement;
import javafx.util.Pair;

import java.util.List;
import java.util.Map;

public class PrintMoreExpensiveCommand extends PaymentCommand {
    private static final String TOTAL_LABEL = "TOTAL:            ";
    private List<BankingMovement> bankingMovements;
    private GroupExpenseStrategy groupExpenseStrategy;

    public PrintMoreExpensiveCommand(List<BankingMovement> bankingMovements, GroupExpenseStrategy groupExpenseStrategy) {
        this.bankingMovements = bankingMovements;
        this.groupExpenseStrategy = groupExpenseStrategy;
    }

    @Override
    public void execute() {
        Map<String, Double> expenses = getExpensesGroupedByCategory(this.bankingMovements, this.groupExpenseStrategy);
        Pair<String, Double> moreExpensiveCategory = new GetMoreExpensiveCategoryUseCase().collect(expenses);
        PaymentMovementHelper.printHeader("    " + groupExpenseStrategy.getCategoryDescription() + moreExpensiveCategory.getKey());
        System.out.println(TOTAL_LABEL + moreExpensiveCategory.getValue());
    }

    private static Map<String, Double> getExpensesGroupedByCategory(List<BankingMovement> bankingMovements, GroupExpenseStrategy groupExpenseStrategy) {
        return new ExpensesSumaryUseCase().group(bankingMovements, groupExpenseStrategy);
    }
}
