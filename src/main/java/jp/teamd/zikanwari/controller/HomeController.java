package jp.teamd.zikanwari.controller;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import jp.teamd.zikanwari.form.KomaForm;
import jp.teamd.zikanwari.form.SubjectForm;
import jp.teamd.zikanwari.form.TeacherForm;
import jp.teamd.zikanwari.form.ClassForm;
import jp.teamd.zikanwari.form.DepartmentForm;
import jp.teamd.zikanwari.service.ClassService;
import jp.teamd.zikanwari.service.SubjectService;
import jp.teamd.zikanwari.service.TeacherService;
import jp.teamd.zikanwari.service.DepartmentService;



@Controller
public class HomeController {

    @Autowired
    SubjectService subjectService;
    @Autowired
    ClassService classService;
    @Autowired
    TeacherService teacherService;
    @Autowired
    DepartmentService departmentService;


    @GetMapping("/")
    String list(Model model){
        return "home";
    }

    @GetMapping("/koma/list")
    public String koma(@ModelAttribute KomaForm komaForm,Model model) {
        model.addAttribute("komaForm",komaForm);
        return "koma/list";
    }
    
    @GetMapping("/class/list")
    public String clss(@ModelAttribute ClassForm classForm,Model model) {
        model.addAttribute("classForm",classForm);
        model.addAttribute("class",classService.findAll());
        return "class/list";
    }

    @GetMapping("/department/list")
    public String department(@ModelAttribute DepartmentForm departmentForm,Model model) {
        
        model.addAttribute("departmentForm",departmentForm);
        model.addAttribute("department",departmentService.findAll());
        return "department/list";
    }

    @GetMapping("/subject/list")
    public String subject(@ModelAttribute SubjectForm subjectForm,Model model) {
        model.addAttribute("subject",subjectService.findAll());
        model.addAttribute("clss", classService.findAll());
        return "subject/list";
    }

    @GetMapping("/teacher/list")
    public String teacher(@ModelAttribute TeacherForm teacherForm,Model model) {
        model.addAttribute("teacherForm",teacherForm);
        model.addAttribute("teacher",teacherService.findAll());
        return "teacher/list";
    }

}

