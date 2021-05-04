package cscix370.termproject.entity;


import javax.persistence.Entity;
import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity // This tells Hibernate to make a table out of this class
public class Book_tags {

    @EmbeddedId
    private Book_tags_id id;

    private Integer count;


    public Book_tags(Book_tags_id id, Integer count){
        this.id = id;
        this.count = count;
    }

}
