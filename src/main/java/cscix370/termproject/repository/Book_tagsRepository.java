package cscix370.termproject.repository;

import cscix370.termproject.entity.Book_tags;
import cscix370.termproject.entity.Book_tags_id;
import cscix370.termproject.interfaces.BooksTopAuthors;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface Book_tagsRepository extends CrudRepository<Book_tags, Book_tags_id> {



    @Query(
        value = "SELECT goodreads_book_id FROM book_tags "
                + "WHERE tag_id "
                + "IN (:ids)",
        nativeQuery = true
    )
    public List<Integer> findGoodreadsIds(@Param("ids") List<Integer> ids);




}