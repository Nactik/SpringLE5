package com.spring.demo.controller.html;

import com.spring.demo.exception.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice("com.spring.demo.controller.html")
public class ExceptionHtmlTranslator {

    private static final Logger log = LoggerFactory.getLogger(ExceptionHtmlTranslator.class);

    @ExceptionHandler(EntityNotFoundException.class)
    public String handleNotFoundException(Exception e, RedirectAttributes redirectAttributes){
        log.error("Not Found Exception",e);
        redirectAttributes.addFlashAttribute("errorMessage",e.getMessage());

        return "redirect:/teachers";
    }
}
