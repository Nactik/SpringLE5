package com.spring.demo.controller.html;

import com.spring.demo.dto.TeachersDto;
import com.spring.demo.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TeacherHtmlController extends AbstractHtmlController {

    @Autowired
    private TeacherService teacherService;

    @RequestMapping("/teachers")
    public String teachers(Model model){
        TeachersDto teachersDto = teacherService.findAll();
        model.addAttribute("teachers", teachersDto.getTeachers());
        return "teachers";
    }

}