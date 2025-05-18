package Expense.example.ExpenseTrackerSystem.Service;
import java.sql.Date;
import java.util.List;



import Expense.example.ExpenseTrackerSystem.Error.ExpenseNotFoundException;
import Expense.example.ExpenseTrackerSystem.Model.Expense;
import Expense.example.ExpenseTrackerSystem.Model.User;
import Expense.example.ExpenseTrackerSystem.Repo.ExpenseRepo;
import Expense.example.ExpenseTrackerSystem.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class ExpenseServiceImpl implements ExpenseService{

    @Autowired
    private ExpenseRepo expenseRepo;

    @Autowired
    private UserRepo userRepo;


    public User getCurrentUser(){
       String username= SecurityContextHolder.getContext().getAuthentication().getName();
       return userRepo.findByusername(username);
    }



    @Override
    public List<Expense> getExpenses() {
        User user=getCurrentUser();
        return expenseRepo.findByUser(user);
    }

    @Override
    public void deleteExpense(Long id) throws ExpenseNotFoundException {
       Expense expense =expenseRepo.findById(id)
               .orElseThrow(()->new ExpenseNotFoundException("Expense not Found"));
       if(!expense.getUser().getUsername().equals(getCurrentUser().getUsername())){
           throw new RuntimeException("Unauthorized Access");
       }
       expenseRepo.deleteById(id);

    }

    @Override
    public List<Expense> getByCategory(String category) {
       User user=getCurrentUser();
       return expenseRepo.findBycategoryAndUser(category,user);
    }

    @Override
    public void addExpenses(Expense expense) {
        User user=getCurrentUser();
        expense.setUser(user);
        expenseRepo.save(expense);
    }

    @Override
    public List<Expense> getExpenseByDate(Date date) {
        User user=getCurrentUser();
       return expenseRepo.findBydateAndUser(date,user);
    }

    @Override
    public void updateExpense(Expense expense) throws ExpenseNotFoundException {
        Long id=expense.getId();
      Expense e=expenseRepo.findById(id)
              .orElseThrow(()->new ExpenseNotFoundException("Expense Not Found"));
        if(!expense.getUser().getUsername().equals(getCurrentUser().getUsername()) ){
            throw new RuntimeException("UnAuthorised Access");
        }
        e.setAmt(expense.getAmt());
        e.setCategory(expense.getCategory());
        e.setDate(expense.getDate());
        expenseRepo.save(e);
    }
}
