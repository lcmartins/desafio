package com.movimentacaobancaria.command;

import com.movimentacaobancaria.helpers.PaymentMovementHelper;
import com.movimentacaobancaria.repository.FileRepository;
import com.movimentacaobancaria.usecase.GetTotalTransactionsUseCase;

public class PrintTotalTransactionsCommand extends BankingMovementCommand {

    private static final String TOTAL_OF_TRANSACTIONS_MSG = "   TOTAL OF TRANSACTIONS";
    private static final String TOTAL_OF_TRANSACTIONS_MESSAGE_FORMAT = "Total of transactions: %s";

    @Override
    public void execute() throws Exception {
        Double total = new GetTotalTransactionsUseCase(new FileRepository()).getTotalTransactios();
        PaymentMovementHelper.printHeader(TOTAL_OF_TRANSACTIONS_MSG);
        PaymentMovementHelper.print(String.format(TOTAL_OF_TRANSACTIONS_MESSAGE_FORMAT, total));
    }
}
