package ApiRest;

import org.springframework.web.bind.annotation.*;

import java.util.List;

//Junto com esta classe configurar uma nova classe "EmployeeNotFoundException"
public class EmployeeController {

    private final EmployeeRepository repository;

    public EmployeeController(EmployeeRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/employees")
    //definindo um lavel para o GetMapping
    //criar método do tipo lista de employees
    public List<Employee> listOfEmployeeAll() {
        return repository.findAll();
    }

    @GetMapping("/employee/{id}")
    public Employee consultById(@PathVariable Long id){
        //o método irá tentar achar o empregado pelo id, caso não consiga encontrar vai jogar uma exceção
        return repository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    @PostMapping("/employees")
    public Employee newEmployee(@RequestBody Employee newEmployee){
        return repository.save(newEmployee);
    }

    //modificação parcial
    @PutMapping("/employees/{id}")
    public Employee replaceEmployee(@RequestBody Employee newEmployee, long id) {
        return repository.findById(id).map(employee -> {
            employee.setName(newEmployee.getName());
            employee.setAddress(newEmployee.getAddress());
            employee.setRole(newEmployee.getRole());
            return repository.save(newEmployee);
        }).orElseGet(()-> {
            newEmployee.setId(id);
            return repository.save(newEmployee);
        });
    }

    @DeleteMapping("/employee/{id}")
    void deleteEmployee(@PathVariable long id) {
        repository.deleteById(id);
    }
}
