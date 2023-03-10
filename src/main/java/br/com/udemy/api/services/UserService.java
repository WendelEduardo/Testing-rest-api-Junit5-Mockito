package br.com.udemy.api.services;

import br.com.udemy.api.domain.Users;

import java.util.List;

public interface UserService {
    Users findById(Integer id);

    List<Users> findAll();
}
