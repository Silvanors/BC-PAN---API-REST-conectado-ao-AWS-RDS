package ApiRest;

import org.springframework.data.jpa.repository.JpaRepository;
//Após montar esta classe ir para "EmployeeController"
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
