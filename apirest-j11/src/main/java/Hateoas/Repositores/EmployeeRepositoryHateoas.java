package Hateoas.Repositores;

import ApiRest.Employee;
import Hateoas.Entitys.EmployeeHateoas;
import org.springframework.data.jpa.repository.JpaRepository;

//Após montar esta classe ir para "EmployeeController"
public interface EmployeeRepositoryHateoas extends JpaRepository<EmployeeHateoas, Long> {
}
