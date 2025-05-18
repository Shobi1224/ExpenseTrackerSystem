package Expense.example.ExpenseTrackerSystem.Service;
import Expense.example.ExpenseTrackerSystem.Error.ExpenseNotFoundException;
import Expense.example.ExpenseTrackerSystem.Model.Expense;

import java.sql.Date;
import java.util.List;

public interface ExpenseService {
    List<Expense> getExpenses();

    void deleteExpense(Long id) throws ExpenseNotFoundException;

    List<Expense> getByCategory(String category);

    void addExpenses(Expense expense);

    List<Expense> getExpenseByDate(Date date);

    void updateExpense(Expense expense) throws ExpenseNotFoundException;
}
