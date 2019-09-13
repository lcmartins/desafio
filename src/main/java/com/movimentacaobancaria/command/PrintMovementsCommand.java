package com.movimentacaobancaria.command;

import com.movimentacaobancaria.helpers.PaymentMovementHelper;
import com.movimentacaobancaria.entities.BankingMovement;

import java.util.List;

public class PrintMovementsCommand extends BankingMovementCommand {

    @Override
    public void execute() throws Exception  {
        List<BankingMovement> bankingMovements = getBankingMovements();
        bankingMovements.forEach(payment -> {
            PaymentMovementHelper.print(payment.toString());
        });
    }
}
