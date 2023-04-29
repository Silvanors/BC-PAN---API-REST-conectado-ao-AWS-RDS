package Hateoas.Controllers;

import Hateoas.Entitys.OrderHateoas;
import Hateoas.Entitys.Status;
import Hateoas.Exceptions.OrderNotFoundExceptionHateoas;
import Hateoas.Repositores.OrderRepositoryHateoas;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class OrderControllerHateoas {
    //definindo o repository
    private final OrderRepositoryHateoas repositoryOrder;

    public OrderControllerHateoas(OrderRepositoryHateoas repositoryHateoas) {
        this.repositoryOrder = repositoryHateoas;
    }

    @GetMapping("/orders")
    ResponseEntity<List<OrderHateoas>> consultOrderAll() {
        Long idOrder;
        Link linkUri;
        List<OrderHateoas> orderList = repositoryOrder.findAll();
        if (orderList.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        for (OrderHateoas orderHateoas:orderList){
            idOrder = orderHateoas.getId();
            linkUri = linkTo(methodOn(OrderControllerHateoas.class).consultOneOrder(idOrder)).withSelfRel();
            orderHateoas.add(linkUri);
            linkUri = linkTo(methodOn(OrderControllerHateoas.class).consultOrderAll()).withRel("Customer order list");
            orderHateoas.add(linkUri);
        }
        return new ResponseEntity<List<OrderHateoas>>(orderList, HttpStatus.OK);
    }

    @GetMapping("/orders/{id}")
    ResponseEntity<OrderHateoas> consultOneOrder(@PathVariable Long id) {
        Optional<OrderHateoas> orderPointer = repositoryOrder.findById(id);
        if(orderPointer.isPresent()) {
            OrderHateoas order = orderPointer.get();
            order.add(linkTo(methodOn(OrderControllerHateoas.class).consultOrderAll()).withRel("All orders"));
            return new ResponseEntity<>(order, HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //adicionando um employee
    @PostMapping("/orders")
    OrderHateoas newOrder(@RequestBody OrderHateoas newOrder) {

        return repositoryOrder.save(newOrder);
    }

    @DeleteMapping("/orders/{id}")
    void deleteOrder(@PathVariable long id) {
        repositoryOrder.deleteById(id);
    }

    //Metodo para cancelar que é diferente de deletar
    @PutMapping("/orders/{id}/cancel")
    ResponseEntity<?> cancelOrderById(@PathVariable long id) {
        //se fizer a consulta e não encontrar será enviado a mensagem de erro
        OrderHateoas cancelledOrder = repositoryOrder.findById(id).orElseThrow(
                () -> new OrderNotFoundExceptionHateoas(id)
        );
        if(cancelledOrder.getStatus() == Status.IN_PROGRESS) {
            cancelledOrder.setStatus(Status.CANCELLED);
            //retornar para o usuário os links
            cancelledOrder.add(linkTo(methodOn(OrderControllerHateoas.class).consultOneOrder(id)).withSelfRel());
            cancelledOrder.add(linkTo(methodOn(OrderControllerHateoas.class).consultOrderAll())
                    .withRel("Complete order list"));
            repositoryOrder.save(cancelledOrder);
            return new ResponseEntity<>(cancelledOrder, HttpStatus.OK);
        }

        //esta é uma maneira de retornar o porque que deu erro para cancelar
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
                .header(HttpHeaders.CONTENT_TYPE,
                        MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE)
                .body("You can't complete the task, the order has a " + cancelledOrder.getStatus() + "status");
    }

    //Metodo para completar tipo um update
    @PutMapping("/orders/{id}/complete")
    ResponseEntity<?> completeOrderById(@PathVariable long id) {
        //se fizer a consulta e não encontrar será enviado a mensagem de erro
        OrderHateoas completedOrder = repositoryOrder.findById(id).orElseThrow(
                () -> new OrderNotFoundExceptionHateoas(id)
        );
        if(completedOrder.getStatus() == Status.IN_PROGRESS) {
            completedOrder.setStatus(Status.COMPLETED);
            //retornar para o usuário os links
            completedOrder.add(linkTo(methodOn(OrderControllerHateoas.class).consultOneOrder(id)).withSelfRel());
            completedOrder.add(linkTo(methodOn(OrderControllerHateoas.class).consultOrderAll())
                    .withRel("Complete order list"));
            repositoryOrder.save(completedOrder);
            return new ResponseEntity<>(completedOrder, HttpStatus.OK);
        }

        //esta é uma maneira de retornar o porque que deu erro para cancelar
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
                .header(HttpHeaders.CONTENT_TYPE,
                        MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE)
                .body("You can't complete the task, the order has a " + completedOrder.getStatus() + "status");
    }

}
