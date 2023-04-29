package com.example.dioclass.apirest;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
/** Após a criação da entidade deve-se:
    1º)Criar as interfaces de repository;
    2º)Criar as classes de controller */

@Entity // para persistir a classe no banco de dados
public class Person {
    //A class Person representa a entidade pessoa
    //Após a configuração da entidade deve-se criar um "Repository" para persistir dados no banco
    // (interface "PersonRpository")
    //Atributos da classe
    @Id //Anotação para id automático
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;

    //Construir o construtor dos atributos
    //Construtor vazio
    public Person() {

    }
    //Construir o construtor dos atributos sem passar o id pq será gerado automaticamente
    public Person(String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    //Gerar o getter e setter
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    //Gerar String com "GeneratetoString"
    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
