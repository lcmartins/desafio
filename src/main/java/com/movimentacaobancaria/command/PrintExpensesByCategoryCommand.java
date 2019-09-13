package com.movimentacaobancaria.command;

import com.movimentacaobancaria.helpers.PaymentMovementHelper;
import com.movimentacaobancaria.repository.FileRepository;
import com.movimentacaobancaria.usecase.ExpensesSumaryUseCase;
import com.movimentacaobancaria.usecase.Strategy.GroupByCategoryStrategy;
import java.util.Map;

public class PrintExpensesByCategoryCommand extends BankingMovementCommand {
    private static final String PRINT_PATTERN = "%s  ->  %s";
    private static final String HEADER_TITLE = "  TOTAL SPENT BY CATEGORY                                 ";

    @Override
    public void execute() throws Exception {

            Map<String, Double> expenses = new ExpensesSumaryUseCase(new FileRepository())
                    .groupPayments(new GroupByCategoryStrategy());
            printHeader();
            for (Map.Entry<String, Double> entry : expenses.entrySet()) {
                PaymentMovementHelper.print(String.format(PRINT_PATTERN, entry.getKey(), entry.getValue()));
            }
    }

    private void printHeader() {
        PaymentMovementHelper.printHeader(HEADER_TITLE);
    }
}
