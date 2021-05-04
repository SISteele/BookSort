package cscix370.termproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
    


    @RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView displayHomepage(ModelAndView modelAndView) {
        System.out.println("TEST");
        
        modelAndView.setViewName("home");
		return modelAndView;
    }
    

    

}
