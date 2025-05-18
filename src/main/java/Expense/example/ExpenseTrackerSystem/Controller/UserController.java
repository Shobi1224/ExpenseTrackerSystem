package Expense.example.ExpenseTrackerSystem.Controller;


import Expense.example.ExpenseTrackerSystem.Model.User;
import Expense.example.ExpenseTrackerSystem.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<User> getUsers()
    {
        return userService.getAllUsers();

    }

    @PostMapping("/register")
    public User registerUser(@RequestBody User user){
        return userService.registerUser(user );
    }

    @PostMapping("register_Admin")
    public User registerAdmin(@RequestBody User user){
        return userService.registerAdmin(user);
    }

    @PostMapping("/login")
        public String loginUser(@RequestBody User user){
           return userService.login(user);
        }

     @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("{id}")
    public Optional<User> getUserId(@PathVariable Long id){
        return userService.getUserBYId(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public String deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return "User deleted successfully";
    }
}
