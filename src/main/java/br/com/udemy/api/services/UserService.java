package br.com.udemy.api.services;

import br.com.udemy.api.domain.Users;
import br.com.udemy.api.domain.dto.UserDTO;

import java.util.List;

public interface UserService {
    Users findById(Integer id);

    List<Users> findAll();

    Users create(UserDTO user);
}
