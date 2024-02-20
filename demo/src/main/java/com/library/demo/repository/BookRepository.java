package com.library.demo.repository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.CrudRepository;
import com.library.demo.models.*;

public interface BookRepository extends PagingAndSortingRepository<Book,Long> , CrudRepository<Book, Long>{

}


