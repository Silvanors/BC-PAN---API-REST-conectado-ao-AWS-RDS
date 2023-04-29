package com.example.dioclass.apirest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
/**Na classe controller serão definidos o métodos de requisições*/
public class PersonController {

    private final PersonRepository repositoryPerson;
    // deve-se criar o constructor do "private final PersonRepository repositoryPerson;"

    public PersonController(PersonRepository repositoryPerson) {
        this.repositoryPerson = repositoryPerson;
    }

    //@GetMapping ("/") para mapear a raiz
    @GetMapping("/")
    public String helloworld() {
        return ("This is my first API in Spring boot!");
    }

    //estão dispostos os métodos de requisição

    //Anotação @GetMapping para utilizar/configurar um caminho pelo browser
    @GetMapping("/persons")
    public List<Person> consultAllPersons(){
        return repositoryPerson.findAll();
    }
    @GetMapping("/persons/{id}")
    // A classe "Optional"  Optional nos ajuda a evitar os erros NullPointerException,
    // tirar a necessidade da verificação (if x != null)
    public Optional<Person> consultById(@PathVariable Long id) {
        return repositoryPerson.findById(id);
    }
}
