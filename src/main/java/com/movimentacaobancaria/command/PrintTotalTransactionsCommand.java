package com.movimentacaobancaria.command;

import com.movimentacaobancaria.helpers.PaymentMovementHelper;
import com.movimentacaobancaria.usecase.GetTotalTransactionsUseCase;
import com.movimentacaobancaria.entities.BankingMovement;

import java.util.List;

public class PrintTotalTransactionsCommand extends BankingMovementCommand {

    public PrintTotalTransactionsCommand() { }

    @Override
    public void execute() throws Exception {
        List<BankingMovement> bankingMovements = getBankingMovements();
        Double total = new GetTotalTransactionsUseCase().get(bankingMovements);
        PaymentMovementHelper.printHeader("   TOTAL OF TRANSACTIONS");
        PaymentMovementHelper.print(String.format("Total of transactions: %s", total));
    }

}
