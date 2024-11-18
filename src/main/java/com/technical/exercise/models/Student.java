package com.technical.exercise.models;

import com.fasterxml.jackson.annotation.JsonProperty; // in order to parse to json properly
import com.technical.exercise.enums.Sexuality;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Table(name = "student")
@Data
public class Student implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "id")
    private Long id;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "age")
    private Integer age;

    @Enumerated(EnumType.STRING)
    @Column(name = "sexuality")
    private Sexuality sexuality;

    // Constructors
    public Student() {
    }

    public Student(String firstName, String lastName, Integer age, String sexuality) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.sexuality = Sexuality.valueOf(sexuality);
    }
}
