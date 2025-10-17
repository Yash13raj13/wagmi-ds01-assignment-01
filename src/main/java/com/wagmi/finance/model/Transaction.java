package main.java.com.wagmi.finance.model;

import java.time.LocalDate;

public class Transaction {
    private final String id;
    private final String description;
    private final double amount;
    private final String category;
    private final LocalDate date;
    private final boolean income;

    public Transaction(String id, String description, double amount, String category, LocalDate date, boolean income) {
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.category = category;
        this.date = date;
        this.income = income;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    public LocalDate getDate() {
        return date;
    }

    public boolean isIncome() {
        return income;
    }
}