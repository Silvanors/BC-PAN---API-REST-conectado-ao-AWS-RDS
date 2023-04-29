package Hateoas;

import ApiRest.Employee;
import ApiRest.EmployeeRepository;
import Hateoas.Entitys.EmployeeHateoas;
import Hateoas.Entitys.OrderHateoas;
import Hateoas.Entitys.Status;
import Hateoas.Repositores.EmployeeRepositoryHateoas;
import Hateoas.Repositores.OrderRepositoryHateoas;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //para o spring boot conseguir executar e usar as instancias
public class LoadDBHateoas {

    private static final Logger log = LoggerFactory.getLogger(LoadDBHateoas.class);

    //persistindo dados no banco om jpa
    @Bean
    CommandLineRunner loadData(EmployeeRepositoryHateoas employeeRepositoryHateoas, OrderRepositoryHateoas orderRepositoryHateoas){
        return args -> {
            log.info("Log of event save user 1: "+ employeeRepositoryHateoas.save(new EmployeeHateoas("Maria Silva",
                    "Chef", "avenida silveira dutra 1002")));
            log.info("Log of event save user 2: "+ employeeRepositoryHateoas.save(new EmployeeHateoas("John Dutra",
                    "Mecanico", "rua joão freire 231")));
            orderRepositoryHateoas.save(new OrderHateoas(Status.COMPLETED, "review"));
            orderRepositoryHateoas.save(new OrderHateoas(Status.IN_PROGRESS, "travel"));
            orderRepositoryHateoas.save(new OrderHateoas(Status.COMPLETED, "sale"));
            orderRepositoryHateoas.findAll().forEach(order -> {
                log.info("Preloaded " + order);
            });
        };
    }
}
