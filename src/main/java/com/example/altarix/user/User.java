package com.example.altarix.user;

import lombok.Data;
import org.springframework.hateoas.Identifiable;

import javax.persistence.*;

@Data
@Entity
@Table(name = "users")
public class User implements Identifiable<Integer> {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Override
    public Integer getId() {
        return id;
    }
}
