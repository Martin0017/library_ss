package com.library.demo.services.userhasbooks;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.library.demo.models.UserHasBooks;
import com.library.demo.repository.UserHasBooksRepository;

@Service
public class UserHasBooksServiceImplements implements UserHasBooksService {

    private UserHasBooksRepository repository;

    public UserHasBooksServiceImplements(UserHasBooksRepository repository) {
        this.repository = repository;
    }

    @Override
    public Iterable<UserHasBooks> findAll() {
        return repository.findAll();
    }

    @Override
    public Page<UserHasBooks> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Optional<UserHasBooks> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public UserHasBooks save(UserHasBooks userHasBooks) {
        return repository.save(userHasBooks);
    }

    @Override
    public UserHasBooks update(Long id, UserHasBooks userHasBooks) {
        if (repository.existsById(id)) {
            userHasBooks.setIdUserHasBook(id);
            return repository.save(userHasBooks);
        } else {
            return null;
        }
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
    
}
