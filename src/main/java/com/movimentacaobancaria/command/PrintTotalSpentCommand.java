package com.movimentacaobancaria.command;

import com.movimentacaobancaria.helpers.PaymentMovementHelper;
import com.movimentacaobancaria.repository.FileRepository;
import com.movimentacaobancaria.usecase.CollectTotalSpentUseCase;
import com.movimentacaobancaria.usecase.Strategy.GroupByCategoryStrategy;

public class PrintTotalSpentCommand extends BankingMovementCommand {
    private static final String CUSTOMER_TOTAL_SPENT = "CUSTOMER TOTAL EXPENSES: %s";
    private static final String TOTAL_SPENT_MSG = "   TOTAL SPENT";

    @Override
    public void execute() throws Exception {
        Double totalSpent = new CollectTotalSpentUseCase(new FileRepository())
                .collectTotalSpent(new GroupByCategoryStrategy());
        PaymentMovementHelper.printHeader(TOTAL_SPENT_MSG);
        PaymentMovementHelper.print(String.format(CUSTOMER_TOTAL_SPENT, totalSpent));
    }
}
