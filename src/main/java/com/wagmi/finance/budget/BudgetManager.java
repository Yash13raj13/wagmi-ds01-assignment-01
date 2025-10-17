package main.java.com.wagmi.finance.budget;

import main.java.com.wagmi.finance.model.Transaction;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class BudgetManager {
    // FIX #1: Lower the threshold to 40% to match the test expectations.
    private static final double APPROACHING_LIMIT_THRESHOLD = 0.40;

    private final Set<String> validCategories;
    private final Map<String, Double> budgetLimits;
    private final Map<String, Double> spentAmounts;

    public BudgetManager() {
        this.validCategories = new HashSet<>();
        this.budgetLimits = new HashMap<>();
        this.spentAmounts = new HashMap<>();
    }

    public void addValidCategory(String category) {
        if (category == null || category.isBlank()) {
            throw new IllegalArgumentException("Category cannot be null or blank.");
        }
        validCategories.add(category);
    }

    public boolean isValidCategory(String category) {
        return category != null && validCategories.contains(category);
    }

    public void setBudgetLimit(String category, double limit) {
        if (isValidCategory(category)) {
            budgetLimits.put(category, limit);
        }
    }

    public double getBudgetLimit(String category) {
        return budgetLimits.getOrDefault(category, 0.0);
    }

    public void applyTransaction(Transaction tx) {
        if (tx == null || !isValidCategory(tx.getCategory())) {
            return;
        }
        if (!tx.isIncome()) {
            String category = tx.getCategory();
            double expenseAmount = tx.getAmount();
            double currentSpending = spentAmounts.getOrDefault(category, 0.0);
            spentAmounts.put(category, currentSpending + expenseAmount);
        }
    }

    public double getSpending(String category) {
        return spentAmounts.getOrDefault(category, 0.0);
    }

    public boolean isApproachingLimit(String category) {
        if (!isValidCategory(category)) {
            return false;
        }
        double limit = getBudgetLimit(category);
        if (limit <= 0) {
            return false;
        }
        double spending = getSpending(category);
        return (spending / limit) >= APPROACHING_LIMIT_THRESHOLD;
    }

    public boolean isOverLimit(String category) {
        if (!isValidCategory(category)) {
            return false;
        }
        double limit = getBudgetLimit(category);
        if (limit < 0) {
            return false;
        }
        // FIX #2: Change from > to >= to include cases where spending equals the limit.
        return getSpending(category) >= limit;
    }
}