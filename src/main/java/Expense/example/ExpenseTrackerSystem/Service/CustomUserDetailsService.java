package Expense.example.ExpenseTrackerSystem.Service;

import Expense.example.ExpenseTrackerSystem.Model.User;
import Expense.example.ExpenseTrackerSystem.Model.UserPrincipal;
import Expense.example.ExpenseTrackerSystem.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {


       private final UserRepo userRepo;

        public CustomUserDetailsService(UserRepo userRepo){
             this.userRepo=userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepo.findByusername(username);
        if(user==null){
            System.out.println("User not available");
            throw new UsernameNotFoundException("User not available");
        }
        return new UserPrincipal(user);

    }
}
