package com.movimentacaobancaria.UseCase.Strategy;

import com.movimentacaobancaria.entities.BankingMovement;
import com.movimentacaobancaria.entities.PaymentBankingMovement;

public class GroupByCategoryStrategy implements GroupExpenseStrategy{
    public String getKey(BankingMovement bankingMovement) {

        return ((PaymentBankingMovement)bankingMovement).getCategoria();
    }

    @Override
    public String getCategoryDescription() {
        return "NOME DA CATEGORIA COM O MAIOR GASTO: ";
    }


}
