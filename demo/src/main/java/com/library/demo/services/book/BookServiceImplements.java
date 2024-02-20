package com.library.demo.services.book;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.library.demo.models.Book;
import com.library.demo.repository.BookRepository;

@Service
public class BookServiceImplements implements BookService {

    private BookRepository repository;

    public BookServiceImplements(BookRepository repository) {
        this.repository = repository;
    }

    @Override
    public Iterable<Book> findAll() {
        return repository.findAll();
    }

    @Override
    public Page<Book> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Optional<Book> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Book save(Book book) {
        return repository.save(book);
    }

    @Override
    public Book update(Long id, Book book) {
        if (repository.existsById(id)) {
            book.setIdBook(id);
            return repository.save(book);
        } else {
            return null;
        }
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
    
}
