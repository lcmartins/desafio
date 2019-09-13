package com.movimentacaobancaria.command;

import com.movimentacaobancaria.helpers.PaymentMovementHelper;
import com.movimentacaobancaria.entities.BankingMovement;
import com.movimentacaobancaria.repository.FileRepository;
import com.movimentacaobancaria.usecase.PaymentListUseCase;
import java.util.List;

public class PrintMovementsCommand extends BankingMovementCommand {

    @Override
    public void execute() throws Exception  {
        PaymentListUseCase paymentListUseCase = new PaymentListUseCase(new FileRepository());
        List<BankingMovement> bankingMovements = paymentListUseCase.listPayments();
        PaymentMovementHelper.printHeader("   SORTING PAYMENTS: ");
        bankingMovements.forEach(payment -> PaymentMovementHelper.print(payment.toString()));
    }
}
