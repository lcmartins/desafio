package com.movimentacaobancaria.command;

import com.movimentacaobancaria.helpers.PaymentMovementHelper;
import com.movimentacaobancaria.usecase.ExpensesSumaryUseCase;
import com.movimentacaobancaria.usecase.Strategy.GroupByCategoryStrategy;
import com.movimentacaobancaria.entities.BankingMovement;
import java.util.List;
import java.util.Map;

public class PrintExpensesByCategoryCommand extends BankingMovementCommand {
    private static final String PRINT_PATTERN = "%s  ->  %s";
    private static final String HEADER_TITLE = "  TOTAL SPENT BY CATEGORY                                 ";

    public PrintExpensesByCategoryCommand()  { }

    @Override
    public void execute() throws Exception {
            List<BankingMovement> bankingMovements = getBankingMovements();
            Map<String, Double> expenses = new ExpensesSumaryUseCase().group(bankingMovements, new GroupByCategoryStrategy());
            printHeader();
            for (Map.Entry<String, Double> entry : expenses.entrySet()) {
                PaymentMovementHelper.print(String.format(PRINT_PATTERN, entry.getKey(), entry.getValue()));
            }
    }

    private void printHeader() {
        PaymentMovementHelper.printHeader(HEADER_TITLE);
    }
}
