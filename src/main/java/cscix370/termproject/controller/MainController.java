package cscix370.termproject.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;

import cscix370.termproject.entity.Books;
import cscix370.termproject.service.BooksService;
import cscix370.termproject.interfaces.BooksTopAuthors;

import cscix370.termproject.entity.Tags;
import cscix370.termproject.service.TagsService;
import cscix370.termproject.interfaces.TagsTopTags;

import cscix370.termproject.entity.Book_tags;
import cscix370.termproject.service.Book_tagsService;


@Controller
public class MainController {
    
    BooksService booksService;
    TagsService tagsService;
    Book_tagsService book_tagsService;

    @Autowired
    public MainController(BooksService booksService, TagsService tagsService, Book_tagsService book_tagsService){
        this.booksService = booksService;
        this.tagsService = tagsService;
        this.book_tagsService = book_tagsService;
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

        List<TagsTopTags> tags = tagsService.findTop100();
        modelAndView.addObject("tags", tags);

        List<Books> books = booksService.findTop100();
        modelAndView.addObject("books", books);

        modelAndView.setViewName("search");
		return modelAndView;
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
	public ModelAndView search(ModelAndView modelAndView, @RequestParam(value = "query", required = false) String query,  @RequestParam(value = "tags", required = false) List<String> tags, @RequestParam(value = "ratingmin", required = true) double min, @RequestParam(value = "ratingmax", required = true) double max) {

        /* no query results in empty string; make it null for sql query to function properly with optional parameters*/
        if(query.equals("")){
            query = null;
        }

        List<Books> books = null;
        List<Integer> goodreads_ids = null;

        /* gets all goodreads_book_ids associated with the tags */
        if(tags != null){
            List<Integer> ids = tagsService.findTagIds(tags);
            goodreads_ids = book_tagsService.findGoodreadsIds(ids);
        } // tags not null

        /*
        *  native queries do not seem to support optional lists for 'IN' clause,
        *  so separate queries are needed depending on if it is null.
        * */
        if(tags != null){ // tags were specified when searching;
            books = booksService.findBooksParameters(goodreads_ids, query, min/20, max/20);
        }else{ // no tags specified when searching;
            books = booksService.findBooksParameters(query, min/20, max/20);
        }

        //books = booksService.findBooksByGoodreadsIds(goodreads_ids);
        System.out.println("\n---Books---");
        for(Books book: books){
            System.out.println(book.getTitle());
        }

        if(books != null){
            modelAndView.addObject("books", books);
            modelAndView.addObject("results", books.size());
        }else{
            modelAndView.addObject("results", 0);
        }


    System.out.println("lower range: " + min/20);
        System.out.println("lower range: " + max/20);


        List<TagsTopTags> populartags = tagsService.findTop100();
        modelAndView.addObject("tags", populartags);

        modelAndView.setViewName("search");
		return modelAndView;
    }
    

}
