package Expense.example.ExpenseTrackerSystem.Repo;

import Expense.example.ExpenseTrackerSystem.Model.Expense;
import Expense.example.ExpenseTrackerSystem.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface ExpenseRepo extends JpaRepository<Expense,Long> {

    List<Expense> findBycategoryAndUser(String category,User user);

    List<Expense> findBydateAndUser(Date date,User user);

    List<Expense> findByUser(User user);


}
