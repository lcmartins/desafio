package com.movimentacaobancaria.boundaries;

import com.movimentacaobancaria.entities.BankingMovement;

import java.io.IOException;

public interface PaymentsApiBoundaryContract {
    Integer sendHttpPost(BankingMovement bankingMovement) throws IOException;
}
