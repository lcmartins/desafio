package com.movimentacaobancaria.usecase;

import com.movimentacaobancaria.usecase.mock.FileRepositoryMock;
import com.movimentacaobancaria.entities.BankingMovement;
import com.movimentacaobancaria.entities.PaymentBankingMovement;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class PaymentListUseCaseTest {

    private PaymentListUseCase paymentListUseCase = new PaymentListUseCase(new FileRepositoryMock());

    @Test
    public void dado_que_receba_uma_lista_de_array_de_pagamentos_como_strings_deve_compilar_estas_strings_em_pagamentos() {
        try {
            List<BankingMovement> payments = paymentListUseCase.listPayments();
            Assert.assertEquals(7, payments.size());
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void dado_que_compile_o_arquivo_em_pagamentos_deve_separar_pagamento_de_recebimento() {
        try {
            List<BankingMovement> movements = paymentListUseCase.listPayments();

            List<PaymentBankingMovement> payments = new ArrayList<>();
            for (BankingMovement p : movements) {
                if (p.getValor() < 0.0) {
                    payments.add((PaymentBankingMovement)p);
                }
            }
            Assert.assertNotNull(payments.get(0).getCategoria());
            Assert.assertNotNull(payments.get(1).getCategoria());
            Assert.assertNotNull(payments.get(2).getCategoria());
            Assert.assertNotNull(payments.get(3).getCategoria());
            Assert.assertNotNull(payments.get(4).getCategoria());
            Assert.assertEquals(5, payments.size());
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void dado_que_o_valor_do_elemento_seja_positivo_entao_deve_indicar_um_pagamento() {
        boolean result = paymentListUseCase.isReceipt("10");
        Assert.assertTrue(result);
    }

    @Test
    public void dado_que_o_valor_do_elemento_seja_negativo_entao_nao_deve_indicar_um_pagamento() {
        boolean result = paymentListUseCase.isReceipt("-10");
        Assert.assertFalse(result);
    }
}
