package Hateoas.Exceptions;

import ApiRest.EmployeeNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
@ControllerAdvice

//Após montar esta classe configurar a classe "LoadDB"
public class OrderNotFoundAdviceHateoas {
    @ResponseBody
    @ExceptionHandler(OrderNotFoundExceptionHateoas.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String orderNotFoundHandler(OrderNotFoundExceptionHateoas ex){
        final String message = ex.getMessage();
        return message;
    }
}
