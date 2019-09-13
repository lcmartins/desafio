package com.movimentacaobancaria.usecase;

import com.movimentacaobancaria.boundaries.PaymentsApiBoundaryContract;
import com.movimentacaobancaria.entities.BankingMovement;
import com.movimentacaobancaria.helpers.PaymentMovementHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SendBankinMovementsUseCase {
    private static final int HTTP_ERROR_STATUS_CODE = 500;
    private PaymentsApiBoundaryContract apiBoundary;

    public SendBankinMovementsUseCase(PaymentsApiBoundaryContract apiBoundary) {
        this.apiBoundary = apiBoundary;
    }

    public Map<BankingMovement, Integer> savePayment(List<BankingMovement> movements){
        Integer id = 1;
        Map<BankingMovement, Integer> sendingPostResults = new HashMap<>();
        movements.stream().forEach(m->{
            m.setId(id);
            try {
                sendingPostResults.put(m, apiBoundary.sendHttpPost(m));
            } catch (Exception e) {
                sendingPostResults.put(m, HTTP_ERROR_STATUS_CODE);
            }
        });

        return sendingPostResults;
    }
}
