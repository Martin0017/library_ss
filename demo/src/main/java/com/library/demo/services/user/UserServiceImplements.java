package com.library.demo.services.user;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.library.demo.models.User;
import com.library.demo.repository.UserRepository;

@Service
public class UserServiceImplements implements UserService {

    private UserRepository repository;

    public UserServiceImplements(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Iterable<User> findAll() {
        return repository.findAll();
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Optional<User> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public User save(User user) {
        return repository.save(user);
    }

    @Override
    public User update(Long id, User user) {
        if (repository.existsById(id)) {
            user.setIdUser(id);
            return repository.save(user);
        } else {
            return null;
        }
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return repository.findByEmailUser(email) ;
    }

    @Override
public UserDetailsService userDetailsService() {
    return username -> {
        User user = repository.findByEmailUser(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        user.setUserName(username);
        return user;
    };
}
    
}
