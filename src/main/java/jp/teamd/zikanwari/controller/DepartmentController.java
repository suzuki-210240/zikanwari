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

import jp.teamd.zikanwari.form.DepartmentForm;
import jp.teamd.zikanwari.service.DepartmentService;


@Controller
@RequestMapping("department")
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;

    @ModelAttribute
    DepartmentForm setUpForm(){
        return new DepartmentForm();
    }

    @GetMapping
    String list(Model model){
        model.addAttribute("department",departmentService.findAll());
        return "department/list";
    }

    @PostMapping(path="create")
    String create(DepartmentForm form,Model mode){
        departmentService.create(form);
        return "redirect:/department";
    }


    @PostMapping(path = "delete")
    String delete(@RequestParam String d_code){
        departmentService.delete(d_code);
        return "redirect:/department";
    }

}

