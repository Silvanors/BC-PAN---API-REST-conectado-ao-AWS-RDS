package Hateoas.Exceptions;

import ApiRest.EmployeeNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


public class OrderNotFoundExceptionHateoas extends RuntimeException {

    public OrderNotFoundExceptionHateoas(long id){
        super("Could not found the order: "+id);
    }
}
