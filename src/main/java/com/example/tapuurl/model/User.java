package com.example.tapuurl.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private  String userName;

    private String password;

    @JsonIgnore
    @OneToMany(mappedBy = "users" ,cascade = CascadeType.ALL)
    private Set<Url> urlSet = new HashSet<>();

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public User(int id) {
        this.id = id;
    }
}
