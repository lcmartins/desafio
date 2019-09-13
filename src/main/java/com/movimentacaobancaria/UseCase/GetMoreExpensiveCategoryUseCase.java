package com.movimentacaobancaria.UseCase;

import javafx.util.Pair;

import java.util.Map;

public class GetMoreExpensiveCategoryUseCase {

    public Pair<String, Double> collect(Map<String, Double> expenses) {
        Pair<String, Double> moreExpensiveCategory = new Pair("", 0.0);
        for (Map.Entry<String, Double> category : expenses.entrySet()) {
            String key = category.getKey().toUpperCase();
            Double value = category.getValue();
            if(value < moreExpensiveCategory.getValue()) {
                moreExpensiveCategory = new Pair<>(key, value);
            }
        }
        return moreExpensiveCategory;
    }
}
