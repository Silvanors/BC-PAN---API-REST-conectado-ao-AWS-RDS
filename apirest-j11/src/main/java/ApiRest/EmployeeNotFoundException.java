package ApiRest;
//Após montar esta classe configurar uma nova classe "EmployeeNotFoundAdvice"
public class EmployeeNotFoundException extends RuntimeException{

    public EmployeeNotFoundException(Long id){
        super("Could not find the id "+ id);
    }
}
