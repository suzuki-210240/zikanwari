package jp.teamd.zikanwari.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jp.teamd.zikanwari.form.TeacherForm;
import jp.teamd.zikanwari.service.TeacherService;


@Controller
@RequestMapping("teacher")
public class TeacherController {
    @Autowired
    TeacherService teacherService;

    @ModelAttribute
    TeacherForm setUpForm(){
        return new TeacherForm();
    }

    @GetMapping
    String list(Model model){
        model.addAttribute("teacher",teacherService.findAll());
        return "teacher/list";
    }

    @PostMapping(path="create")
    String create(TeacherForm form,Model mode){
        teacherService.create(form);
        return "redirect:/teacher";
    }


    @PostMapping(path = "delete")
    String delete(@RequestParam Integer t_number){
        teacherService.delete(t_number);
        return "redirect:/teacher";
    }

}

