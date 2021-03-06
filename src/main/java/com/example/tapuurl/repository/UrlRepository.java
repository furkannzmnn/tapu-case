package com.example.tapuurl.repository;

import com.example.tapuurl.model.Url;
import com.example.tapuurl.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UrlRepository extends JpaRepository<Url , Integer> {
    Optional<Url> findByShortUrl(String url);


}
