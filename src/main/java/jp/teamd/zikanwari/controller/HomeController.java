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
import org.springframework.web.bind.annotation.RequestBody;
import jp.teamd.zikanwari.form.KomaForm;
import jp.teamd.zikanwari.form.SubjectForm;
import jp.teamd.zikanwari.form.TeacherForm;
import jp.teamd.zikanwari.form.ClassForm;
import jp.teamd.zikanwari.form.DepartmentForm;



@Controller
public class HomeController {

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
        return "class/list";
    }

    @GetMapping("/department/list")
    public String department(@ModelAttribute DepartmentForm departmentForm,Model model) {
        model.addAttribute("departmentForm",departmentForm);
        return "department/list";
    }

    @GetMapping("/subject/list")
    public String subject(@ModelAttribute SubjectForm subjectForm,Model model) {
        model.addAttribute("subjectForm",subjectForm);
        return "subject/list";
    }

    @GetMapping("/teacher/list")
    public String teacher(@ModelAttribute TeacherForm teacherForm,Model model) {
        model.addAttribute("teacherForm",teacherForm);
        return "teacher/list";
    }

}

