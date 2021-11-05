package com.example.tapuurl.model;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "urls")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Url implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String originUrl;

    private String shortUrl;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private User users;


    public Url(String originUrl, String shortUrl) {
        this.originUrl = originUrl;
        this.shortUrl = shortUrl;
    }

    public Url(String originUrl, String shortUrl, User users) {
        this.originUrl = originUrl;
        this.shortUrl = shortUrl;
        this.users = users;
    }
}
