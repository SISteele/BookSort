package cscix370.termproject.entity;


import javax.persistence.Entity;
import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity // This tells Hibernate to make a table out of this class
public class Tags {
    
    @Id
    private Integer tag_id;

    private String tag_name;
}
