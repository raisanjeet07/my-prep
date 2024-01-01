package com.san.lld.split_wise_lld.entity;

public class Expense {

    private static int idGenerator = 0;

    private int expenseId;
    private double amount;
    private User paidByUser;
    private ExpenseShare share;
    private User[] contributor;
    private double[] sareAmounts;


    public Expense(double amount, User paidByUser, ExpenseShare share, User[] contributor) {
        this.expenseId = idGenerator++;
        this.amount = amount;
        this.paidByUser = paidByUser;
        this.share = share;
        this.contributor = contributor;
    }

    public Expense(double amount, User paidByUser, ExpenseShare share, User[] contributor, double[] sareAmounts) {
        this.expenseId = idGenerator++;
        this.amount = amount;
        this.paidByUser = paidByUser;
        this.share = share;
        this.contributor = contributor;
        this.sareAmounts = sareAmounts;
    }

    public static int getIdGenerator() {
        return idGenerator;
    }

    public int getExpenseId() {
        return expenseId;
    }

    public double getAmount() {
        return amount;
    }

    public User getPaidByUser() {
        return paidByUser;
    }

    public ExpenseShare getShare() {
        return share;
    }

    public User[] getContributor() {
        return contributor;
    }

    public double[] getSareAmounts() {
        return sareAmounts;
    }
}
