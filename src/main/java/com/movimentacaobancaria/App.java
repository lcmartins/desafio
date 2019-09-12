package com.movimentacaobancaria;

import com.movimentacaobancaria.UseCase.ExpensesSumaryUseCase;
import com.movimentacaobancaria.UseCase.GetTotalSpentUseCase;
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
        PaymentListUseCase paymentListUseCase = new PaymentListUseCase(new FileRepository());
        try {
            int option = getOptionFromArgs(args);
            if(option < 0 || option > 6) {
                return;
            }
            List<BankingMovement> bankingMovements = paymentListUseCase.getData();
            switch (option) {
                case 1:
                    printMovements(bankingMovements);
                    break;
                case 2:
                    printExpensesByCatetory(bankingMovements);
                    break;
                case 3:
                    printMoreExpensive(bankingMovements, new GroupByCategoryStrategy());
                    break;
                case 4:
                    printMoreExpensive(bankingMovements, new GroupByMonthStrategy());
                    break;
                case 5:
                    printTotalSpent(bankingMovements);
                    break;
                default:
                    printTotalTransactions(bankingMovements);
            }
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
    private static void printMovements(List<BankingMovement> bankingMovements) {
        bankingMovements.forEach(payment -> {
            System.out.println(payment.toString());
        });
    }
    private static void printExpensesByCatetory(List<BankingMovement> bankingMovements) {
        Map<String, Double> expenses = getExpensesGroupedByCategory(bankingMovements);

        for (Map.Entry<String, Double> entry : expenses.entrySet()) {
            System.out.println(VALUE_SPENT_BY + entry.getKey() + " -> " + entry.getValue());
        }
    }

    private static Map<String, Double> getExpensesGroupedByCategory(List<BankingMovement> bankingMovements) {
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

    private static void printTotalSpent(List<BankingMovement> bankingMovements) {
        Map<String, Double> expenses = getExpensesGroupedByCategory(bankingMovements);
        Double totalSpent = expenses
                .values()
                .stream()
                .reduce(0.0, (total, current) -> total + current);
        System.out.println(CUSTOMER_TOTAL_SPENT + totalSpent);
    }

    private static void printTotalTransactions(List<BankingMovement> bankingMovements) {
        GetTotalSpentUseCase getTotalSpentUseCase = new GetTotalSpentUseCase();
        System.out.println("Total of transactions: " + getTotalSpentUseCase.getTotalTransactions(bankingMovements));
    }
}
