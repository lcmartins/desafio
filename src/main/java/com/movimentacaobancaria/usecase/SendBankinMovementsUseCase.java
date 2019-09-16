package com.movimentacaobancaria.usecase;

import com.movimentacaobancaria.boundaries.PaymentsApiBoundaryContract;
import com.movimentacaobancaria.entities.BankingMovement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class SendBankinMovementsUseCase {
    private static final int HTTP_ERROR_STATUS_CODE = 500;
    private PaymentsApiBoundaryContract apiBoundary;

    public SendBankinMovementsUseCase(PaymentsApiBoundaryContract apiBoundary) {
        this.apiBoundary = apiBoundary;
    }

    public Map<BankingMovement, Integer> savePayment(List<BankingMovement> movements){
        AtomicInteger id = new AtomicInteger(1);
        Map<BankingMovement, Integer> sendingPostResults = new HashMap<>();
        movements.stream().forEach(m->{
            m.setId(id.get());
            try {
                sendingPostResults.put(m, apiBoundary.sendHttpPost(m));
            } catch (Exception e) {
                sendingPostResults.put(m, HTTP_ERROR_STATUS_CODE);
            }
            id.set(id.get() + 1);
        });

        return sendingPostResults;
    }
}
