package com.movimentacaobancaria.command;

import com.movimentacaobancaria.helpers.PaymentMovementHelper;
import com.movimentacaobancaria.usecase.ExpensesSumaryUseCase;
import com.movimentacaobancaria.usecase.GetTotalSpentUseCase;
import com.movimentacaobancaria.usecase.Strategy.GroupByCategoryStrategy;
import com.movimentacaobancaria.entities.BankingMovement;

import java.util.List;
import java.util.Map;

public class PrintTotalSpentCommand extends BankingMovementCommand {
    private static final String CUSTOMER_TOTAL_SPENT = "CUSTOMER TOTAL EXPENSES: %s";

    public PrintTotalSpentCommand(){
    }

    @Override
    public void execute() throws Exception {
        List<BankingMovement> bankingMovements = getBankingMovements();
        Map<String, Double> expenses = new ExpensesSumaryUseCase().group(bankingMovements, new GroupByCategoryStrategy());
        Double totalSpent = new GetTotalSpentUseCase().get(bankingMovements, expenses);
        PaymentMovementHelper.printHeader("   TOTAL SPENT");
        PaymentMovementHelper.print(String.format(CUSTOMER_TOTAL_SPENT, totalSpent));
    }
}
