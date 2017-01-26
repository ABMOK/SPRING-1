package pl.com.mnemonic.ExceptionHandlers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by mnemonic on 2016-03-05.
 */
@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(Exception.class)
    public ModelAndView handle(HttpServletRequest req, Exception ex) {
        ModelAndView model = new ModelAndView("error");
        model.addObject("exception", ex);
        model.addObject("url", req.getRequestURL());
        model.setViewName("error");
        model.addObject("msg", "Exception occured: " + "exception \r" + ex + "\n" +ex.getMessage());
        return model;
    }
/*
    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleResourceNotFoundException() {
        return "redirect:/";
    }*/
}
