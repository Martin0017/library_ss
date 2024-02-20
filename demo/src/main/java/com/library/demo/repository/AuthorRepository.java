package com.library.demo.repository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.CrudRepository;
import com.library.demo.models.*;

public interface AuthorRepository extends PagingAndSortingRepository<Author,Long> , CrudRepository<Author, Long>{

}


