package com.movimentacaobancaria;

import com.movimentacaobancaria.UseCase.ExpensesByCategoryUseCase;
import com.movimentacaobancaria.UseCase.PaymentSourceException;
import com.movimentacaobancaria.UseCase.PaymentListUseCase;
import com.movimentacaobancaria.entities.BankingMovement;
import com.movimentacaobancaria.repository.FileRepository;
import java.util.List;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        PaymentListUseCase readPaymentUseCase = new PaymentListUseCase(new FileRepository());
        try {
            Integer option = -1;

            if(args.length > 0) {
                try {
                    option = Integer.parseInt(args[0]);
                } catch (Exception e){
                    System.out.println("ERROR GETTING MENU OPTON: " + e.getStackTrace());
                    return;
                }
            }
            List<BankingMovement> bankingMovements = readPaymentUseCase.getData();

            if(option == 1) {
                printMovements(bankingMovements);
            } else if(option == 2) {
                printExpensesByCatetory(bankingMovements);
            }
        } catch (PaymentSourceException e) {
            System.out.println(e.getFriendlyMessage());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static void printMovements(List<BankingMovement> bankingMovements) {
        bankingMovements.forEach(payment -> {
            System.out.println(payment.toString());
        });
    }
    private static void printExpensesByCatetory(List<BankingMovement> bankingMovements) {
        ExpensesByCategoryUseCase expensesByCategoryUseCase = new ExpensesByCategoryUseCase();
        Map<String, Double> expenses = expensesByCategoryUseCase.reduce(bankingMovements);

        for (Map.Entry<String, Double> entry : expenses.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            System.out.println("VALOR GASTO POR: " + key + " -> " + value);
        }
    }
}
