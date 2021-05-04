package cscix370.termproject.entity;

import java.io.Serializable;
import javax.persistence.*;

@Embeddable
public class Book_tags_id implements Serializable{

    @Column(name = "goodreads_book_id", nullable = false)
    private Integer book_id;

    @Column(name = "tag_id", nullable = false)
    private Integer tag_id;
    
}
