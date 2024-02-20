package com.library.demo.services.author;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.library.demo.models.Author;
import com.library.demo.repository.AuthorRepository;

@Service
public class AuthorServiceImplements implements AuthorService {

    private AuthorRepository repository;

    public AuthorServiceImplements(AuthorRepository repository) {
        this.repository = repository;
    }

    @Override
    public Iterable<Author> findAll() {
        return repository.findAll();
    }

    @Override
    public Page<Author> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Optional<Author> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Author save(Author author) {
        return repository.save(author);
    }

    @Override
    public Author update(Long id, Author author) {
        if (repository.existsById(id)) {
            author.setIdAuthor(id);
            return repository.save(author);
        } else {
            return null;
        }
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
    
}
