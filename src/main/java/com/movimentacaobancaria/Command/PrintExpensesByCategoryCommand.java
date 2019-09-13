package com.movimentacaobancaria.Command;

import com.movimentacaobancaria.Helpers.PaymentMovementHelper;
import com.movimentacaobancaria.UseCase.ExpensesSumaryUseCase;
import com.movimentacaobancaria.UseCase.Strategy.GroupByCategoryStrategy;
import com.movimentacaobancaria.entities.BankingMovement;
import java.util.List;
import java.util.Map;

public class PrintExpensesByCategoryCommand extends PaymentCommand {
    private static final String PRINT_PATTERN = "%s  ->  %s";
    public static final String HEADER_TITLE = "  TOTAL SPENT BY CATEGORY                                 ";
    private List<BankingMovement> bankingMovements;

    public PrintExpensesByCategoryCommand(List<BankingMovement> bankingMovements) {
        this.bankingMovements = bankingMovements;
    }

    @Override
    public void execute() {
        Map<String, Double> expenses = new ExpensesSumaryUseCase().group(this.bankingMovements, new GroupByCategoryStrategy());
        printHeader();
        for (Map.Entry<String, Double> entry : expenses.entrySet()) {
           PaymentMovementHelper.print(String.format(PRINT_PATTERN, entry.getKey(), entry.getValue()));
        }
    }

    private void printHeader() {
        PaymentMovementHelper.printHeader(HEADER_TITLE);
    }
}
