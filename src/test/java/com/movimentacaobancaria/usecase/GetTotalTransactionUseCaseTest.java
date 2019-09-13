package com.movimentacaobancaria.usecase;
import com.movimentacaobancaria.usecase.mock.FileRepositoryMock;
import com.movimentacaobancaria.entities.BankingMovement;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static junit.framework.TestCase.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class GetTotalTransactionUseCaseTest {

    private PaymentListUseCase paymentListUseCase = new PaymentListUseCase(new FileRepositoryMock());
    private GetTotalTransactionsUseCase getTotalTransactionUseCase = new GetTotalTransactionsUseCase();

    @Test
    public void dado_que_receba_uma_lista_pagamentos_deve_devolver_o_total_das_transacoes_somando_todas_as_entradas_como_valore_positivos() {

        try{
            List<BankingMovement> movements = this.paymentListUseCase.listPayments();
            Double totalFromUseCase = getTotalTransactionUseCase.get(movements);
            assertEquals(totalFromUseCase, 759.1);
        } catch (Exception e) {
            Assert.fail();
        }
    }

}
