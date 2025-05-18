package Expense.example.ExpenseTrackerSystem.Service;

import Expense.example.ExpenseTrackerSystem.Model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();

    User registerUser(User user);

    Optional<User> getUserBYId(Long id);

    void deleteUser(Long id);

    String login(User user);

    User registerAdmin(User user);
}
