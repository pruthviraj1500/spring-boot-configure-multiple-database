package com.programming.configure_multiple_databases.book.repository;

import com.programming.configure_multiple_databases.model.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

}
