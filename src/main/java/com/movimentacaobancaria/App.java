package com.movimentacaobancaria;

import com.movimentacaobancaria.command.*;
import com.movimentacaobancaria.helpers.PaymentMovementHelper;
import com.movimentacaobancaria.usecase.*;
import com.movimentacaobancaria.usecase.Strategy.GroupByCategoryStrategy;
import com.movimentacaobancaria.usecase.Strategy.GroupByMonthStrategy;

public class App 
{
    public static void main(final String[] args ) {

        try {
            int option = getOptionFromArgs(args);

            BankingMovementCommand command = new SendPostPaymentCommand();
            switch (option) {
                case 1:
                    command = new PrintMovementsCommand();
                    break;
                case 2:
                    command = new PrintExpensesByCategoryCommand();
                    break;
                case 3:
                    command = new PrintMoreExpensiveCommand(new GroupByCategoryStrategy());
                    break;
                case 4:
                    command = new PrintMoreExpensiveCommand(new GroupByMonthStrategy());
                    break;
                case 5:
                    command = new PrintTotalSpentCommand();
                    break;
                case 6:
                    command = new PrintTotalTransactionsCommand();
                    break;
                default:
                    break;
            }
            command.execute();
        } catch (PaymentSourceException e) {
            PaymentMovementHelper.print(e.getFriendlyMessage());
        } catch (Exception e) {
            PaymentMovementHelper.print(e.fillInStackTrace().getMessage());
        }
    }

    private static int getOptionFromArgs(final String[] args) {
        int result = -1;
        if(args.length > 0) {
            try {
                result = Integer.parseInt(args[0]);
            } catch (Exception e){
                PaymentMovementHelper.print(e.fillInStackTrace().getMessage());
            }
        }
        return  result;
    }
}
