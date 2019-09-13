package com.movimentacaobancaria.command;

import com.movimentacaobancaria.usecase.PaymentListUseCase;
import com.movimentacaobancaria.entities.BankingMovement;
import com.movimentacaobancaria.repository.FileRepository;

import java.io.IOException;
import java.util.List;

public abstract class BankingMovementCommand {

    public List<BankingMovement> getBankingMovements() throws IOException {
        PaymentListUseCase paymentListUseCase = new PaymentListUseCase(new FileRepository());
        return paymentListUseCase.listPayments();
    }
    public abstract void execute() throws Exception;
}
