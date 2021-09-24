package com.spring.demo.controller.html;

import com.spring.demo.dto.TeacherDto;
import com.spring.demo.dto.TeachersDto;
import com.spring.demo.service.TeacherService;
import com.spring.demo.validator.TeacherValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class TeacherHtmlController extends AbstractHtmlController {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private TeacherValidator teacherValidator;

    @InitBinder
    protected void initBinder(WebDataBinder webDataBinder){
        webDataBinder.setValidator(teacherValidator);
    }

    @RequestMapping("/teachers")
    public String teachers(Model model){
        TeachersDto teachersDto = teacherService.findAll();
        model.addAttribute("teachers", teachersDto.getTeachers());
        return "teachers";
    }

    @RequestMapping("/teachers/{id}")
    public String teacher(Model model, @PathVariable int id){
        TeacherDto teacherDto = teacherService.findById(id);
        model.addAttribute("teacher", teacherDto);
        model.addAttribute("action","update");

        return "teacher-create-update";
    }



    @RequestMapping("/teachers/create")
    public String initCreateTeacher(Model model){
        if(!model.containsAttribute("teacher")){
            model.addAttribute("teacher", new TeacherDto());
        }
        model.addAttribute("action","save");

        return "teacher-create-update.html";
    }

    @PostMapping("/teachers/create")
    public String createTeacher(@Valid TeacherDto teacher, BindingResult result, RedirectAttributes redirectAttributes){
        if(result.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.teacher", result);
            redirectAttributes.addFlashAttribute("teacher", teacher);
            return "redirect:/teachers/create";
        }
        teacherService.save(teacher);
        return "redirect:/teachers";
    }

    @PostMapping("/teachers/{id}")
    public String updateTeacher(@PathVariable int id, @Valid TeacherDto teacher,BindingResult result, RedirectAttributes redirectAttributes){
        if(result.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.teacher", result);
            redirectAttributes.addFlashAttribute("teacher", teacher);
            return "redirect:/teachers/" + id;
        }
        teacherService.save(teacher);
        return "redirect:/teachers";
    }
}
