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
}
