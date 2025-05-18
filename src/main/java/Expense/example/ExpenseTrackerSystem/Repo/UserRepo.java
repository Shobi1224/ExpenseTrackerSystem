package Expense.example.ExpenseTrackerSystem.Repo;


import Expense.example.ExpenseTrackerSystem.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
    User findByusername(String username);
}
