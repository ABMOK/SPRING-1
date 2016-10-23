package pl.com.mnemonic;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SpringApplicationController {
	
	   @RequestMapping("/welcome")
       public ModelAndView helloWorld() {  
                  String message = "WORKS";
                  return new ModelAndView("welcome", "message", message);
       }          

}
