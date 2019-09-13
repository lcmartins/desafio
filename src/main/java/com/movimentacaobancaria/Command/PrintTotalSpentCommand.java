package com.movimentacaobancaria.Command;

import com.movimentacaobancaria.Helpers.PaymentMovementHelper;
import com.movimentacaobancaria.UseCase.ExpensesSumaryUseCase;
import com.movimentacaobancaria.UseCase.GetTotalSpentUseCase;
import com.movimentacaobancaria.UseCase.Strategy.GroupByCategoryStrategy;
import com.movimentacaobancaria.entities.BankingMovement;

import java.util.List;
import java.util.Map;

public class PrintTotalSpentCommand extends PaymentCommand {
    private static final String CUSTOMER_TOTAL_SPENT = "CUSTOMER TOTAL EXPENSES: %s";
    private List<BankingMovement> bankingMovements;

    public PrintTotalSpentCommand(List<BankingMovement> bankingMovements) {
        this.bankingMovements = bankingMovements;
    }

    @Override
    public void execute() {
        Map<String, Double> expenses = new ExpensesSumaryUseCase().group(bankingMovements, new GroupByCategoryStrategy());
        Double totalSpent = new GetTotalSpentUseCase().get(bankingMovements, expenses);
        PaymentMovementHelper.printHeader("   TOTAL SPENT");
        PaymentMovementHelper.print(String.format(CUSTOMER_TOTAL_SPENT, totalSpent));
    }
}
