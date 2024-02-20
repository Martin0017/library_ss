package com.library.demo.services.user;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.library.demo.models.User;

@Service
public interface UserService {
    public Iterable<User> findAll();

	public Page<User> findAll(Pageable pageable);
	
	public Optional<User> findById(Long id);
	
	public User save(User author);
	
	public User update(Long id, User author);
	
	public void deleteById(Long id);
}
