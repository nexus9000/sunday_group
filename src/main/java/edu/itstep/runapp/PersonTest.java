package edu.itstep.runapp;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name="person")
public class PersonTest {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name="age")
    private int age;

    public PersonTest(String name, int age){
        this.name = name;
        this.age = age;
    }
}
