package com.movimentacaobancaria.Command;

import com.movimentacaobancaria.Helpers.PaymentMovementHelper;
import com.movimentacaobancaria.entities.BankingMovement;

import java.util.List;

public class PrintMovementsCommand extends PaymentCommand {
    private List<BankingMovement> bankingMovements;

    public PrintMovementsCommand(List<BankingMovement> bankingMovements) {
        this.bankingMovements = bankingMovements;
    }

    @Override
    public void execute() {
        bankingMovements.forEach(payment -> {
            PaymentMovementHelper.print(payment.toString());
        });
    }
}
