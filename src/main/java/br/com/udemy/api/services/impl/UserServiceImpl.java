package br.com.udemy.api.services.impl;

import br.com.udemy.api.domain.Users;
import br.com.udemy.api.repositories.UserRepository;
import br.com.udemy.api.services.UserService;
import br.com.udemy.api.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public Users findById(Integer id) {
        Optional<Users> usuario = repository.findById(id);
        return usuario.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado!"));
    }

}
