package cscix370.termproject.repository;

import cscix370.termproject.entity.Books;
import cscix370.termproject.interfaces.BooksTopAuthors;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BooksRepository extends CrudRepository<Books, Integer>{

    @Query(
        value = "SELECT DISTINCT * FROM books ORDER BY average_rating DESC LIMIT 100",
        nativeQuery = true
    )
    public List<Books> findTop100();
    

    @Query(
        value = "SELECT authors as authors, ROUND(AVG(average_rating), 2) as rating, COUNT(DISTINCT book_id) as count FROM books "
        + "GROUP BY authors "
        + "HAVING COUNT(DISTINCT book_id) > 5 "
        + "ORDER BY AVG(average_rating) DESC "
        + "LIMIT 100",
        nativeQuery = true
    )
    public List<BooksTopAuthors> findTop100Authors();


    @Query(
        value = "SELECT DISTINCT * "
                + "FROM " 
                    + "(SELECT book_id, COUNT(book_id) AS toread " 
                    + "FROM to_read "
                    + "GROUP BY book_id "
                    + "ORDER BY COUNT(book_id) DESC "
                    + "LIMIT 100) as b "
                + "INNER JOIN books "
                + "ON books.book_id = b.book_id "
                + "ORDER BY toread DESC",
        nativeQuery = true
    )
    public List<Books> findTop100ToRead();



    @Query(
        value = "SELECT * FROM books "
                + "WHERE goodreads_book_id "
                + "IN (:ids)",
        nativeQuery = true
    )
    public List<Books> findBooksByGoodreadsIds(@Param("ids") List<Integer> ids);




    @Query(
            value = "SELECT * FROM books "
                    + "WHERE (goodreads_book_id IN (:ids)) "
                    + "AND ((:query is null OR lower(title) LIKE lower(concat('%', :query, '%'))) OR (:query is null OR lower(authors) LIKE lower(concat('%', :query, '%')))) "
                    + "AND (average_rating BETWEEN :min AND :max)",
            nativeQuery = true
    )
    public List<Books> findBooksParameters(@Param("ids") List<Integer> ids, @Param("query") String query, @Param("min") double min, @Param("max") double max);


    @Query(
            value = "SELECT * FROM books "
                    + "WHERE (average_rating BETWEEN :min AND :max) "
                    + "AND ((:query is null OR lower(title) LIKE lower(concat('%', :query, '%'))) OR ((:query is null OR lower(authors) LIKE lower(concat('%', :query, '%')))))",
            nativeQuery = true
    )
    public List<Books> findBooksParameters(@Param("query") String query, @Param("min") double min, @Param("max") double max);

}
