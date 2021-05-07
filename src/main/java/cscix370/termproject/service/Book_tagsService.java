package cscix370.termproject.service;

import cscix370.termproject.entity.Book_tags;
import cscix370.termproject.repository.Book_tagsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;


@Service("book_tagsService")
public class Book_tagsService {

    private Book_tagsRepository book_tagsRepository;

    @Autowired
    public Book_tagsService(Book_tagsRepository book_tagsRepository) { 
      this.book_tagsRepository = book_tagsRepository;
    }


    // finds every goodreads_book_id associated with a list of tag_id's
    public List<Integer> findGoodreadsIds(List<Integer> ids){
        return book_tagsRepository.findGoodreadsIds(ids);
    } 


}