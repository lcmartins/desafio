package com.movimentacaobancaria.usecase;

import com.movimentacaobancaria.usecase.mock.FileRepositoryMock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class GetTotalSpentUseCaseTest {
    private PaymentListUseCase paymentListUseCase = new PaymentListUseCase(new FileRepositoryMock());
    private GetTotalSpentUseCase getTotalTransactionUseCase = new GetTotalSpentUseCase();

    @Test
    public void dado_que_receba_uma_lista_pagamentos_deve_devolver_o_total_das_transacoes_somando_todas_as_entradas_como_valore_positivos() {

    }

}
