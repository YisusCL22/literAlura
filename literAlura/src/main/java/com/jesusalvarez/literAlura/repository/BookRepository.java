package com.jesusalvarez.literAlura.repository;

import com.jesusalvarez.literAlura.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT b FROM Book b WHERE :language member of b.languages")
    List<Book> findByLanguagesContaining(@Param("language") String language);
}