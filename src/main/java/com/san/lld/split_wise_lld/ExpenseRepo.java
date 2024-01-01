package com.san.lld.split_wise_lld;

import com.san.lld.split_wise_lld.entity.Expense;
import com.san.lld.split_wise_lld.entity.User;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class ExpenseRepo {
    private final Map<Integer, Expense> expenseTable = new ConcurrentHashMap<>();

    public void addExpense(Expense expense){
        expenseTable.put(expense.getExpenseId(), expense);
    }

    public Expense getById(int expenseId){
        return expenseTable.get(expenseId);
    }

    public List<Expense> getAllExpensesPaidByUser(User user){
        return expenseTable.values().stream().filter(expense -> expense.getPaidByUser().equals(user)).collect(Collectors.toList());
    }
}
