package com.library.demo.services.userhasbooks;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.library.demo.models.UserHasBooks;

@Service
public interface UserHasBooksService {
    public Iterable<UserHasBooks> findAll();

	public Page<UserHasBooks> findAll(Pageable pageable);
	
	public Optional<UserHasBooks> findById(Long id);
	
	public UserHasBooks save(UserHasBooks author);
	
	public UserHasBooks update(Long id, UserHasBooks author);
	
	public void deleteById(Long id);
}
