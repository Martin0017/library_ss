package com.library.demo.repository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import com.library.demo.models.*;

public interface UserRepository extends PagingAndSortingRepository<User,Long> , CrudRepository<User, Long>{

    Optional<User> findByEmailUser(String email);

}


