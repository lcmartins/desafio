package com.movimentacaobancaria.Command;

import com.movimentacaobancaria.Helpers.PaymentMovementHelper;
import com.movimentacaobancaria.UseCase.getTotalTransactionsUseCase;
import com.movimentacaobancaria.entities.BankingMovement;

import java.util.List;

public class PrintTotalTransactionsCommand extends PaymentCommand {
    List<BankingMovement> bankingMovements;

    public PrintTotalTransactionsCommand(List<BankingMovement> bankingMovements) {
        this.bankingMovements = bankingMovements;
    }

    @Override
    public void execute() {
        Double total = new getTotalTransactionsUseCase().get(bankingMovements);
        PaymentMovementHelper.printHeader("   TOTAL OF TRANSACTIONS");
        PaymentMovementHelper.print(String.format("Total of transactions: %s", total));
    }

}
