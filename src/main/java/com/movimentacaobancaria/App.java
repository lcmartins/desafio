package com.movimentacaobancaria;

import com.movimentacaobancaria.Command.*;
import com.movimentacaobancaria.UseCase.*;
import com.movimentacaobancaria.UseCase.Strategy.GroupByCategoryStrategy;
import com.movimentacaobancaria.UseCase.Strategy.GroupByMonthStrategy;
import com.movimentacaobancaria.entities.BankingMovement;
import com.movimentacaobancaria.repository.FileRepository;
import java.util.List;

public class App 
{
    public static void main(String[] args ) {

        try {
            int option = getOptionFromArgs(args);
            if(option < 0 || option > 6) {
                return;
            }
            PaymentListUseCase paymentListUseCase = new PaymentListUseCase(new FileRepository());
            List<BankingMovement> bankingMovements = paymentListUseCase.listPayments();
            PaymentCommand command = new PrintTotalTransactionsCommand(bankingMovements);
            switch (option) {
                case 1:
                    command = new PrintMovementsCommand(bankingMovements);
                    break;
                case 2:
                    command = new PrintExpensesByCategoryCommand(bankingMovements);
                    break;
                case 3:
                    command = new PrintMoreExpensiveCommand(bankingMovements, new GroupByCategoryStrategy());
                    break;
                case 4:
                    command = new PrintMoreExpensiveCommand(bankingMovements,  new GroupByMonthStrategy());
                    break;
                case 5:
                    command = new PrintTotalSpentCommand(bankingMovements);
                    break;
                default:
                    break;
            }
            command.execute();
        } catch (PaymentSourceException e) {
            System.out.println(e.getFriendlyMessage());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static int getOptionFromArgs(String[] args) {
        int result = -1;
        if(args.length > 0) {
            try {
                result = Integer.parseInt(args[0]);
            } catch (Exception e){
                System.out.println(e.fillInStackTrace().getMessage());
            }
        }
        return  result;
    }
}
