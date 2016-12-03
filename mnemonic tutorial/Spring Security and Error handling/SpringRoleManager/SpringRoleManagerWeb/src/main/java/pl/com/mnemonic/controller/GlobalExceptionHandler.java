package pl.com.mnemonic.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ModelAndView handle(HttpServletRequest req, Exception ex) {
        ModelAndView model = new ModelAndView("welcome");
        model.addObject("exception", ex);
        model.addObject("url", req.getRequestURL());
        model.addObject("msg", "Exception occured: " + "exception \r" + ex + "\n" +ex.getMessage());
        return model;
    }
}
