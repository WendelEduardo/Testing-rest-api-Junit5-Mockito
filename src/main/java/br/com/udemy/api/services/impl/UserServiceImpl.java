package br.com.udemy.api.services.impl;

import br.com.udemy.api.domain.Users;
import br.com.udemy.api.domain.dto.UserDTO;
import br.com.udemy.api.repositories.UserRepository;
import br.com.udemy.api.services.UserService;
import br.com.udemy.api.services.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public Users findById(Integer id) {
        Optional<Users> usuario = repository.findById(id);
        return usuario.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado!"));
    }

    @Override
    public List<Users> findAll() {
        List<Users> usuarios = repository.findAll();
        return usuarios;
    }

    @Override
    public Users create(UserDTO userDTO) {
        findByEmail(userDTO);
        return repository.save(mapper.map(userDTO, Users.class));
    }

    private void findByEmail(UserDTO userDTO){
        Optional<Users> usuario = repository.findByEmail(userDTO.getEmail());

        if(usuario.isPresent() && !usuario.get().getId().equals(userDTO.getId()) ){
            throw new DataIntegrityViolationException("Email já cadastrado no sistema.");
        }
    }

    @Override
    public Users update(UserDTO userDTO) {
        findByEmail(userDTO);
        return repository.save(mapper.map(userDTO, Users.class));
    }

    @Override
    public UserDTO delete(Integer id) {
        Optional<Users> user = Optional.ofNullable(findById(id));
        repository.deleteById(id);
        return mapper.map(user, UserDTO.class);
    }

}
