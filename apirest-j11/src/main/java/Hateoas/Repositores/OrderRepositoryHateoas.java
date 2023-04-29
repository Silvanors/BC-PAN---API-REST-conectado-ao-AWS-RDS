package Hateoas.Repositores;

import Hateoas.Entitys.OrderHateoas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepositoryHateoas extends JpaRepository<OrderHateoas, Long> {
}
