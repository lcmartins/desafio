package com.movimentacaobancaria;

import com.movimentacaobancaria.UseCase.ExpensesSumaryUseCase;
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

public class App 
{
    private static final String TOTAL_LABEL = "TOTAL:            ";
    private static final String VALUE_SPENT_BY = "VALOR GASTO POR: ";
    private static final String CUSTOMER_TOTAL_SPENT = "TOTAL DE GASTOS DO CLIENTE: ";

    public static void main(String[] args ) {
        PaymentListUseCase readPaymentUseCase = new PaymentListUseCase(new FileRepository());
        try {
            Integer option = -1;

            if(args.length > 0) {
                try {
                    option = Integer.parseInt(args[0]);
                } catch (Exception e){
                    System.out.println(e.fillInStackTrace().getMessage());
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
                System.out.println(CUSTOMER_TOTAL_SPENT + getTotalSpent(bankingMovements));
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
        Map<String, Double> expenses = getPaymentsGrouped(bankingMovements);

        for (Map.Entry<String, Double> entry : expenses.entrySet()) {
            System.out.println(VALUE_SPENT_BY + entry.getKey() + " -> " + entry.getValue());
        }
    }

    private static Map<String, Double> getPaymentsGrouped(List<BankingMovement> bankingMovements) {
        ExpensesSumaryUseCase expensesByCategoryUseCase = new ExpensesSumaryUseCase();
        return expensesByCategoryUseCase.group(bankingMovements, new GroupByCategoryStrategy());
    }

    private static void printMoreExpensive(List<BankingMovement> bankingMovements, GroupExpenseStrategy groupExpenseStrategy) {
        ExpensesSumaryUseCase expensesByCategoryUseCase = new ExpensesSumaryUseCase();
        Map<String, Double> expenses = expensesByCategoryUseCase.group(bankingMovements, groupExpenseStrategy);
        Pair<String, Double> moreExpensiveCategory = expensesByCategoryUseCase.getMoreExpensiveCategory(expenses);

        System.out.println(groupExpenseStrategy.getCategoryDescription() + moreExpensiveCategory.getKey());
        System.out.println(TOTAL_LABEL + moreExpensiveCategory.getValue());
    }

    private static Double getTotalSpent(List<BankingMovement> bankingMovements) {
        Map<String, Double> expenses = getPaymentsGrouped(bankingMovements);
        return expenses.values().stream().reduce(0.0, (total, current) -> total + current );
    }
}
