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

    private static final String CREATING_PAYMENT_STATUS_CODE_FORMAT_MSG = " SENDING PAYMENTS | RECEIPTS TO BE CREATED, WAIT A MINUTE ...";
    private static final String SENDING_PAYMENTS_TO_BE_CREATED_WAIT_A_MINUTE_MSG = "  **SENDING PAYMENT FOR SERVER: %s HAD STATUS CODE %S";
    private static final String SENDING_RECEIPT_TO_BE_CREATED_WAIT_A_MINUTE_MSG = "  SENDING RECEIPT FOR SERVER: %s HAD STATUS CODE %S";

    @Override
    public void execute() throws Exception {
        List<BankingMovement> movementList = new PaymentListUseCase(new FileRepository())
                .listPayments();
        PaymentsApiBoundary boundary = new PaymentsApiBoundary();
        PaymentMovementHelper.printHeader(CREATING_PAYMENT_STATUS_CODE_FORMAT_MSG);
        Map<BankingMovement, Integer> results = new SendBankinMovementsUseCase(boundary)
                .savePayment(movementList);
        for (Map.Entry<BankingMovement, Integer> entry : results.entrySet()) {
            final String message = entry.getKey().isPayment() ? SENDING_PAYMENTS_TO_BE_CREATED_WAIT_A_MINUTE_MSG :  SENDING_RECEIPT_TO_BE_CREATED_WAIT_A_MINUTE_MSG;
            PaymentMovementHelper.print(String.format(message, entry.getKey().toString(), entry.getValue()));
        }
    }
}
