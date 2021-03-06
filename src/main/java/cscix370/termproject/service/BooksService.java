package cscix370.termproject.service;

import cscix370.termproject.entity.Books;
import cscix370.termproject.repository.BooksRepository;
import cscix370.termproject.interfaces.BooksTopAuthors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;


@Service("booksService")
public class BooksService {
    
    private BooksRepository booksRepository;

    @Autowired
    public BooksService(BooksRepository booksRepository) { 
      this.booksRepository = booksRepository;
    }


    public List<Books> findTop100(){
        return booksRepository.findTop100();
    }

    public List<BooksTopAuthors> findTop100Authors(){
        return booksRepository.findTop100Authors();
    }

    public List<Books> findTop100ToRead(){
        return booksRepository.findTop100ToRead();
    }

    public List<Books> findBooksByGoodreadsIds(List<Integer> ids){
        return booksRepository.findBooksByGoodreadsIds(ids);
    }

    public List<Books> findBooksParameters(List<Integer> ids, String query, double min, double max){
        return booksRepository.findBooksParameters(ids, query, min, max);
    }

    public List<Books> findBooksParameters(String query, double min, double max){
        return booksRepository.findBooksParameters(query, min, max);
    }
}
