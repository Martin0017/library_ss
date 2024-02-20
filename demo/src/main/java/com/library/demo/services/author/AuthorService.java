package com.library.demo.services.author;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.library.demo.models.Author;

@Service
public interface AuthorService {
    public Iterable<Author> findAll();

	public Page<Author> findAll(Pageable pageable);
	
	public Optional<Author> findById(Long id);
	
	public Author save(Author author);
	
	public Author update(Long id, Author author);
	
	public void deleteById(Long id);
}
