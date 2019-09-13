package com.movimentacaobancaria.gateway;

import com.movimentacaobancaria.entities.BankingMovement;

import java.io.IOException;
import java.util.List;

public interface PaymentList {
    List<BankingMovement> listPayments() throws IOException;
}
