package com.movimentacaobancaria.Helpers;

import java.time.LocalDate;

public class PaymentMovementHelper {
    private static final String YEAR_BASE = "2019";
    private static final String DATE_SEPARATOR = "-";

    public static LocalDate buildSortableDate(String data) {
        String[] dateParts = data.split("-");
        String day = dateParts[0];
        String month = dateParts[1];
        StringBuilder dataBuilder = new StringBuilder();
        dataBuilder.append(YEAR_BASE)
                .append(DATE_SEPARATOR);

        switch (month.toUpperCase()) {
            case "JAN":
                dataBuilder.append("01").append(DATE_SEPARATOR);
                break;
            case "FEB":
                dataBuilder.append("02").append(DATE_SEPARATOR);
                break;
            case "MAR":
                dataBuilder.append("03").append(DATE_SEPARATOR);
                break;
            case "APR":
                dataBuilder.append("04").append(DATE_SEPARATOR);
                break;
            case "MAY":
                dataBuilder.append("05").append(DATE_SEPARATOR);
                break;
            case "JUN":
                dataBuilder.append("06").append(DATE_SEPARATOR);
                break;
            case "JUL":
                dataBuilder.append("07").append(DATE_SEPARATOR);
                break;
            case "AUG":
                dataBuilder.append("08").append(DATE_SEPARATOR);
                break;
            case "SEP":
                dataBuilder.append("09").append(DATE_SEPARATOR);
                break;
            case "OCT":
                dataBuilder.append("10").append(DATE_SEPARATOR);
                break;
            case "NOV":
                dataBuilder.append("11").append(DATE_SEPARATOR);
                break;
            default:
                dataBuilder.append("12").append(DATE_SEPARATOR);
                break;
        }
        dataBuilder.append(day);
        return LocalDate.parse(dataBuilder.toString());
    }

    public static void print(String message) {
        System.out.println(message);
    }

    public static void printHeader(String header) {
        PaymentMovementHelper.print("|||||||||||||||||||||||||||||                        |||||||||||||||||||||||||||||||||");
        PaymentMovementHelper.print(header);
        PaymentMovementHelper.print("|||||||||||||||||||||||||||||                        |||||||||||||||||||||||||||||||||");
    }

}
