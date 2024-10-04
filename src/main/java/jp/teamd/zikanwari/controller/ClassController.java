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

import jp.teamd.zikanwari.form.ClassForm;
import jp.teamd.zikanwari.service.ClassService;


@Controller
@RequestMapping("class")
public class ClassController {
    @Autowired
    ClassService classService;

    @ModelAttribute
    ClassForm setUpForm(){
        return new ClassForm();
    }

    @GetMapping
    String list(Model model){
        model.addAttribute("class",classService.findAll());
        return "class/list";
    }

    @PostMapping(path="create")
    String create(ClassForm form,Model mode){
        classService.create(form);
        return "redirect:/class";
    }


    @PostMapping(path = "delete")
    String delete(@RequestParam String c_code){
        classService.delete(c_code);
        return "redirect:/class";
    }

}

