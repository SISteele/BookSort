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
        

        modelAndView.setViewName("search");
		return modelAndView;
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
	public ModelAndView search(ModelAndView modelAndView, @RequestParam(value = "query", required = false) String query,  @RequestParam(value = "tags", required = false) List<String> tags) {

        System.out.println(query);


        if(tags != null){
            System.out.println("\n---Tags---");
            for(String tag: tags){
                System.out.println(tag);
            }

            List<Integer> ids = tagsService.findTagIds(tags);
            System.out.println("\n---Tags IDS---");
            for(Integer id: ids){
                System.out.println(id);
            }

            List<Integer> goodreads_ids = book_tagsService.findGoodreadsIds(ids);
            System.out.println("\n---Goodreads IDS---");
            for(Integer id: goodreads_ids){
                System.out.println(id);
            }

            List<Books> books = booksService.findBooksByGoodreadsIds(goodreads_ids);
            System.out.println("\n---Books---");
            for(Books book: books){
                System.out.println(book.getTitle());
            }

        } // tags not null
        
        
        modelAndView.setViewName("redirect:search");
		return modelAndView;
    }
    

}
