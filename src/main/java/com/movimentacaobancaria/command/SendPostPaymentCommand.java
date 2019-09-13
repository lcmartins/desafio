package com.movimentacaobancaria.command;

import com.movimentacaobancaria.boundaries.PaymentsApiBoundary;
import com.movimentacaobancaria.entities.BankingMovement;
import com.movimentacaobancaria.helpers.PaymentMovementHelper;
import com.movimentacaobancaria.repository.FileRepository;
import com.movimentacaobancaria.usecase.PaymentListUseCase;
import com.movimentacaobancaria.usecase.SendBankinMovementsUseCase;

import java.util.List;
import java.util.Map;

public class SendPostPaymentCommand extends BankingMovementCommand {

    private static final String CREATING_PAYMENT_STATUS_CODE_FORMAT_MSG = "CREATING PAYMENT FOR SERVER: %s HAD STATUS CODE %S";

    @Override
    public void execute() throws Exception {
        List<BankingMovement> movementList = new PaymentListUseCase(new FileRepository())
                .listPayments();
        PaymentsApiBoundary boundary = new PaymentsApiBoundary();
        Map<BankingMovement, Integer> results = new SendBankinMovementsUseCase(boundary)
                .savePayment(movementList);
        for (Map.Entry<BankingMovement, Integer> entry : results.entrySet()) {
            PaymentMovementHelper.print(String.format(CREATING_PAYMENT_STATUS_CODE_FORMAT_MSG, entry.getKey().toString(), entry.getValue()));
        }
    }
}
