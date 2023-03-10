package br.com.udemy.api.services;

import br.com.udemy.api.domain.Users;

public interface UserService {
    Users findById(Integer id);
}
