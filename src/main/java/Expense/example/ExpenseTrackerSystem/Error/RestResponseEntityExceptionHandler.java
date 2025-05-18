package Expense.example.ExpenseTrackerSystem.Error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import Expense.example.ExpenseTrackerSystem.Model.ErrorMessage;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@ResponseStatus
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ExpenseNotFoundException.class)
    public ResponseEntity<ErrorMessage> expenseNotFoundException(ExpenseNotFoundException exception){
        ErrorMessage message =new ErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);




    }
}
