package com.bmicrocorp.laboratory.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.bmicrocorp.laboratory.model.entities.User;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByEmail(String email);

    User findById(long id);
}
