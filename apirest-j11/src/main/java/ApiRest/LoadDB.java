package ApiRest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //para o spring boot conseguir executar e usar as instancias
public class LoadDB {

    private static final Logger log = LoggerFactory.getLogger(LoadDB.class);

    //persistindo dados no banco om jpa
    @Bean
    CommandLineRunner commandLineRunner(EmployeeRepository employeeRepository){
        return args -> {
            log.info("Log of event save user 1: "+ employeeRepository.save(new Employee("Maria Silva",
                    "Chef", "avenida silveira dutra 1002")));
            log.info("Log of event save user 2: "+ employeeRepository.save(new Employee("John Dutra",
                    "Mecanico", "rua joão freire 231")));
        };



    }
}
