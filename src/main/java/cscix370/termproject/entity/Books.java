package cscix370.termproject.entity;


import javax.persistence.Entity;
import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity // This tells Hibernate to make a table out of this class
public class Books {

    @Id
    private Integer goodreads_book_id;
    
    private Integer book_id;
    
    private Integer best_book_id;
    
    private Integer work_id;
    
    private Integer books_count;
    
    private Integer isbn;

    private String authors;
     
    private Integer original_publication_year;
    
    private String original_title; 
    
    private String title; 
    
    private String language_code;
    
    @Column(name = "average_rating")
    private Double averageRating;
    
    private Integer ratings_count;
    
    private Integer work_ratings_count;
    
    private Integer work_text_reviews_count;
    
    private Integer ratings_1;
    
    private Integer ratings_2;
    
    private Integer ratings_3;
    
    private Integer ratings_4;
    
    private Integer ratings_5;

    @Column(name = "image_url")
    private String url;

    

    public String getTitle(){
        return title;
    }

    public Double getAverageRating(){
        return averageRating;
    }

    public String getAuthors(){
        return authors;
    }

    public String getUrl(){
        return url;
    }

}