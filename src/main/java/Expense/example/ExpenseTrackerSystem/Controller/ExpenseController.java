package Expense.example.ExpenseTrackerSystem.Controller;

import Expense.example.ExpenseTrackerSystem.Error.ExpenseNotFoundException;
import Expense.example.ExpenseTrackerSystem.Model.Expense;
import Expense.example.ExpenseTrackerSystem.Service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @GetMapping()
    public List<Expense> getExpenses(){
        return expenseService.getExpenses();

    }
  @DeleteMapping("/{id}")
  public String deleteExpense(@PathVariable Long id) throws ExpenseNotFoundException {
        expenseService.deleteExpense(id);
        return "EXpense deleted successfully";
  }

  @GetMapping("/category/{category}")
    public List<Expense> getByCategory(@PathVariable(name="category") String category){
        return expenseService.getByCategory(category);
  }

  @PostMapping("/add")
    public ResponseEntity<String> addExpanses(@RequestBody Expense expense){
        expenseService.addExpenses(expense);
        return ResponseEntity.ok("Expenses added successfully");
  }

  @GetMapping("/date/{date}")
    public List<Expense> getExpenseByDate(@PathVariable Date date){
        return expenseService.getExpenseByDate(date);
  }

  @PutMapping()
    public ResponseEntity<String> updateExpense(@RequestBody Expense expense) throws ExpenseNotFoundException {
        expenseService.updateExpense(expense);
        return ResponseEntity.ok("Expense Updated successfully");
  }
}
