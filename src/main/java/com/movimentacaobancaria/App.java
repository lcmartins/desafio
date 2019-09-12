package com.movimentacaobancaria;

import com.movimentacaobancaria.UseCase.ExpensesByCategoryUseCase;
import com.movimentacaobancaria.UseCase.PaymentSourceException;
import com.movimentacaobancaria.UseCase.PaymentListUseCase;
import com.movimentacaobancaria.UseCase.Strategy.GroupByCategoryStrategy;
import com.movimentacaobancaria.UseCase.Strategy.GroupByMonthStrategy;
import com.movimentacaobancaria.UseCase.Strategy.GroupExpenseStrategy;
import com.movimentacaobancaria.entities.BankingMovement;
import com.movimentacaobancaria.repository.FileRepository;
import javafx.util.Pair;

import java.util.List;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class App 
{

    public static final String TOTAL_LABEL = "TOTAL:            ";

    public static void main(String[] args ) {
        PaymentListUseCase readPaymentUseCase = new PaymentListUseCase(new FileRepository());
        try {
            Integer option = -1;

            if(args.length > 0) {
                try {
                    option = Integer.parseInt(args[0]);
                } catch (Exception e){
                    System.out.println("erro setando argumentos escolha entre 1 e 3");
                    return;
                }
            }
            List<BankingMovement> bankingMovements = readPaymentUseCase.getData();

            if(option == 1) {
                printMovements(bankingMovements);
            } else if(option == 2) {
                printExpensesByCatetory(bankingMovements);
            } else if(option == 3) {
                printMoreExpensive(bankingMovements, new GroupByCategoryStrategy());
            } else if(option == 4) {
                printMoreExpensive(bankingMovements, new GroupByMonthStrategy());
            } else if(option == 5){
                System.out.println("TOTAL DE GASTOS DO CLIENTE: " + getTotalSpent(bankingMovements));
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
        Map<String, Double> expenses = getStringDoubleMap(bankingMovements);

        for (Map.Entry<String, Double> entry : expenses.entrySet()) {
            System.out.println("VALOR GASTO POR: " + entry.getKey() + " -> " + entry.getValue());
        }
    }

    private static Map<String, Double> getStringDoubleMap(List<BankingMovement> bankingMovements) {
        ExpensesByCategoryUseCase expensesByCategoryUseCase = new ExpensesByCategoryUseCase();
        return expensesByCategoryUseCase.group(bankingMovements, new GroupByCategoryStrategy());
    }

    private static void printMoreExpensive(List<BankingMovement> bankingMovements, GroupExpenseStrategy groupExpenseStrategy) {
        ExpensesByCategoryUseCase expensesByCategoryUseCase = new ExpensesByCategoryUseCase();
        Map<String, Double> expenses = expensesByCategoryUseCase.group(bankingMovements, groupExpenseStrategy);
        Pair<String, Double> moreExpensiveCategory = expensesByCategoryUseCase.getMoreExpensiveCategory(expenses);

        System.out.println(groupExpenseStrategy.getCategoryDescription() + moreExpensiveCategory.getKey());
        System.out.println(TOTAL_LABEL + moreExpensiveCategory.getValue());
    }

    private static Double getTotalSpent(List<BankingMovement> bankingMovements) {
        Map<String, Double> expenses = getStringDoubleMap(bankingMovements);
        return expenses.values().stream().reduce(0.0, (total, current) -> total + current );
    }
}
