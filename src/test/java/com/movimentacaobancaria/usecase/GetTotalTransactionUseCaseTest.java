package com.movimentacaobancaria.usecase;
import com.movimentacaobancaria.usecase.mock.FileRepositoryMock;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import static junit.framework.TestCase.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class GetTotalTransactionUseCaseTest {

    private GetTotalTransactionsUseCase getTotalTransactionUseCase = new GetTotalTransactionsUseCase(new FileRepositoryMock());

    @Test
    public void dado_que_receba_uma_lista_pagamentos_deve_devolver_o_total_das_transacoes_somando_todas_as_entradas_como_valore_positivos() {

        try{
            Double totalFromUseCase = getTotalTransactionUseCase.getTotalTransactios();
            assertEquals(1189.5900000000001,totalFromUseCase);

        } catch (Exception e) {
            Assert.fail();
        }
    }

}
