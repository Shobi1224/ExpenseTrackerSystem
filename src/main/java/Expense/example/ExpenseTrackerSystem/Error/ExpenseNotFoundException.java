package Expense.example.ExpenseTrackerSystem.Error;

public class ExpenseNotFoundException extends Exception{
     public ExpenseNotFoundException(){
         super();
     }

    public ExpenseNotFoundException(String message){
        super(message);
    }


}
