package com.movimentacaobancaria.command;

import com.movimentacaobancaria.helpers.PaymentMovementHelper;
import com.movimentacaobancaria.repository.FileRepository;
import com.movimentacaobancaria.usecase.CollectMoreExpensiveUseCase;
import com.movimentacaobancaria.usecase.Strategy.GroupExpenseStrategy;
import javafx.util.Pair;


public class PrintMoreExpensiveCommand extends BankingMovementCommand {
    private static final String TOTAL_LABEL = "TOTAL:            ";
    private static final String EMPTY_STRING = "    ";
    private GroupExpenseStrategy groupExpenseStrategy;

    public PrintMoreExpensiveCommand(GroupExpenseStrategy groupExpenseStrategy){
        this.groupExpenseStrategy = groupExpenseStrategy;
    }

    @Override
    public void execute() throws Exception {
        Pair<String, Double> moreExpensiveCategory = new CollectMoreExpensiveUseCase(new FileRepository())
                .get(this.groupExpenseStrategy);
        PaymentMovementHelper.printHeader(EMPTY_STRING + this.groupExpenseStrategy.getCategoryDescription() + moreExpensiveCategory.getKey());
        PaymentMovementHelper.print(TOTAL_LABEL + moreExpensiveCategory.getValue());
    }
}
