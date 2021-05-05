package cscix370.termproject.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;

import cscix370.termproject.entity.Books;
import cscix370.termproject.service.BooksService;
import cscix370.termproject.interfaces.BooksTopAuthors;

@Controller
public class MainController {
    
    BooksService booksService;

    @Autowired
    public MainController(BooksService booksService){
        this.booksService = booksService;
    }


    @RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView redirectHome(ModelAndView modelAndView) {
        modelAndView.setViewName("redirect:home");
		return modelAndView;
    }
    

    @RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView displayHomepage(ModelAndView modelAndView) {
        modelAndView.setViewName("main");
		return modelAndView;
    }


    @RequestMapping(value = "/topbooks", method = RequestMethod.GET)
	public ModelAndView getPopularBooks(ModelAndView modelAndView) {

        List<Books> books = booksService.findTop100();

        // for(Books b: books){
        //     System.out.println(b.getTitle() + " ---- " + b.getAverageRating());
        // }
        modelAndView.addObject("books", books);

        modelAndView.setViewName("books");
		return modelAndView;
    }


    @RequestMapping(value = "/topauthors", method = RequestMethod.GET)
	public ModelAndView getPopularAuthors(ModelAndView modelAndView) {

        List<BooksTopAuthors> books = booksService.findTop100Authors();

        // for(BooksTopAuthors b: books){
        //     System.out.println(b.getAuthors() + " ---- " + b.getRating() + " ---- " + b.getCount());
        // }

        modelAndView.addObject("books", books);

        modelAndView.setViewName("authors");
		return modelAndView;
    }


    @RequestMapping(value = "/bucketlist", method = RequestMethod.GET)
	public ModelAndView getBucketList(ModelAndView modelAndView) {

        List<Books> books = booksService.findTop100ToRead();

        // for(Books b: books){
        //     System.out.println(b);
        // }
        modelAndView.addObject("books", books);

        modelAndView.setViewName("bucketlist");
		return modelAndView;
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
	public ModelAndView search(ModelAndView modelAndView) {

        modelAndView.setViewName("search");
		return modelAndView;
    }

    

}
