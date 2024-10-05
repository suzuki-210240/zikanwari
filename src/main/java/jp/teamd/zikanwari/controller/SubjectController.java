package jp.teamd.zikanwari.controller;

//import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jp.teamd.zikanwari.form.SubjectForm;
import jp.teamd.zikanwari.service.ClassService;
import jp.teamd.zikanwari.service.SubjectService;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
@RequestMapping("subject")
public class SubjectController {
    @Autowired
    SubjectService subjectService;
    @Autowired
    ClassService classService;

    @ModelAttribute
    SubjectForm setUpForm(){
        return new SubjectForm();
    }

    
    @GetMapping
    String list(Model model){
        model.addAttribute("subject",subjectService.findAll());
        model.addAttribute("clss", classService.findAll());
        return "subject/list";
    }

    @PostMapping(path="create")
    String create(SubjectForm form,Model mode){
        subjectService.create(form);
        return "redirect:/subject";
    }


    @PostMapping(path = "delete")
    String delete(@RequestParam Integer s_code){
        subjectService.delete(s_code);
        return "redirect:/subject";
    }

    @PostMapping(path = "filter")
    public String postMethodName(@RequestBody Model model) {
        model.addAttribute("subject",subjectService.findAll());
        
        return "redirect:/subject";
    }
    



}

