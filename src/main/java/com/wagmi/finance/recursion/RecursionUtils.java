package main.java.com.wagmi.finance.recursion;

import main.java.com.wagmi.finance.model.Transaction;

public final class RecursionUtils {
    private RecursionUtils() {}

    // --- Date Validation ---
    public static boolean isValidDateRecursive(String date) {
        if (date == null || date.length() != 10) {
            return false;
        }
        return parseYear(date);
    }

    private static boolean parseYear(String date) {
        int year = 0;
        for (int i = 0; i < 4; i++) {
            char c = date.charAt(i);
            if (!Character.isDigit(c)) return false;
            year = year * 10 + (c - '0');
        }
        if (date.charAt(4) != '-') return false;
        return parseMonth(date.substring(5), year);
    }

    private static boolean parseMonth(String date, int year) {
        if (date.length() != 5) return false;
        int month = 0;
        for (int i = 0; i < 2; i++) {
            char c = date.charAt(i);
            if (!Character.isDigit(c)) return false;
            month = month * 10 + (c - '0');
        }
        if (date.charAt(2) != '-') return false;
        return parseDay(date.substring(3), year, month);
    }

    private static boolean parseDay(String date, int year, int month) {
        if (date.length() != 2) return false;
        int day = 0;
        for (int i = 0; i < 2; i++) {
            char c = date.charAt(i);
            if (!Character.isDigit(c)) return false;
            day = day * 10 + (c - '0');
        }
        return isLogicallyValidDate(year, month, day);
    }

    private static boolean isLogicallyValidDate(int year, int month, int day) {
        if (year < 1 || month < 1 || month > 12 || day < 1) {
            return false;
        }
        int maxDay;
        switch (month) {
            case 2:
                boolean isLeap = ((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0);
                maxDay = isLeap ? 29 : 28;
                break;
            case 4: case 6: case 9: case 11:
                maxDay = 30;
                break;
            default:
                maxDay = 31;
        }
        return day <= maxDay;
    }

    // --- Category Total ---
    public static double categoryTotalRecursive(Transaction[] arr, String category) {
        if (arr == null || category == null) return 0.0;
        return categoryTotalHelper(arr, category, 0);
    }

    private static double categoryTotalHelper(Transaction[] arr, String category, int index) {
        if (index >= arr.length) return 0.0;
        double totalForRest = categoryTotalHelper(arr, category, index + 1);
        Transaction current = arr[index];
        double currentAmount = 0.0;
        if (current != null && category.equals(current.getCategory()) && !current.isIncome()) {
            currentAmount = current.getAmount();
        }
        return currentAmount + totalForRest;
    }

    // --- Budget Report Generation ---
    public static String generateBudgetReportRecursive(Transaction[] arr) {
        if (arr == null || arr.length == 0) return "No transactions to report.";
        return generateReportHelper(arr, 0);
    }

    private static String generateReportHelper(Transaction[] arr, int index) {
        if (index >= arr.length) return "";
        Transaction current = arr[index];
        String currentLine = String.format("ID: %s, Date: %s, Amount: %.2f, Category: %s",
                current.getId(), current.getDate().toString(), current.getAmount(), current.getCategory());
        String reportForRest = generateReportHelper(arr, index + 1);
        return reportForRest.isEmpty() ? currentLine : currentLine + "\n" + reportForRest;
    }
}