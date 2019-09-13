package com.movimentacaobancaria.usecase;

import com.movimentacaobancaria.usecase.Strategy.GroupByCategoryStrategy;
import com.movimentacaobancaria.usecase.Strategy.GroupByMonthStrategy;
import com.movimentacaobancaria.usecase.mock.FileRepositoryMock;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Map;

@RunWith(MockitoJUnitRunner.class)
public class ExpensesSumaryUseCaseTest {

    @Test
    public void dado_que_passe_a_estrategia_de_agrupamento_por_mes_deve_trazer_compo_agrupamento_os_meses() {
        try {
            ExpensesSumaryUseCase expensesSumaryUseCase = new ExpensesSumaryUseCase(new FileRepositoryMock());
            Map<String, Double> group = expensesSumaryUseCase.groupPayments(new GroupByMonthStrategy());

            Assert.assertTrue(group.containsKey("mar"));
            Assert.assertTrue(group.containsKey("jun"));
            Assert.assertTrue(group.containsKey("jan"));
            Assert.assertTrue(group.containsKey("feb"));

        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void dado_que_passe_a_estrategia_de_agrupamento_por_categoria_e_um_pagamento_nao_esteja_categorizado_deve_trazer_categoria_UNCATEGORIZED() {
        final String UNCATEGORIZED_IDENTIFIER = "UNCATEGORIZED";
        try {
            ExpensesSumaryUseCase expensesSumaryUseCase = new ExpensesSumaryUseCase(new FileRepositoryMock());
            Map<String, Double> group = expensesSumaryUseCase.groupPayments(new GroupByCategoryStrategy());

            Assert.assertTrue(group.containsKey(UNCATEGORIZED_IDENTIFIER));

        } catch (Exception e) {
            Assert.fail();
        }
    }


}
