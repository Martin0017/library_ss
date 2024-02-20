package com.library.demo.services.book;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.library.demo.models.Book;


@Service
public interface BookService {
    public Iterable<Book> findAll();

	public Page<Book> findAll(Pageable pageable);
	
	public Optional<Book> findById(Long id);
	
	public Book save(Book author);
	
	public Book update(Long id, Book author);
	
	public void deleteById(Long id);
}
