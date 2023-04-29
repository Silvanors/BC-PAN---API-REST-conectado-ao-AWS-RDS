package com.example.dioclass.apirest;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long>{
    //Após criar a interface deve-se extender para o Jpa passando a classe e o tipo
}
