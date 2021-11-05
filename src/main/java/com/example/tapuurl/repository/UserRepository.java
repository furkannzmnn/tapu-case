package com.example.tapuurl.repository;

import com.example.tapuurl.model.Url;
import com.example.tapuurl.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User , Integer> {

}
