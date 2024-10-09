package jp.teamd.zikanwari.controller;

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
import java.util.List;


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
    String create(Model model,String s_name,String c_code,Integer t_number,Integer s_classification,Integer weak_frame,Integer use_room_number){
        Integer code = subjectService.getCode(c_code);
        subjectService.create(code, s_name, c_code, t_number, s_classification, use_room_number);
        subjectService.setSubject(code, s_name, c_code, t_number, s_classification,weak_frame,use_room_number);
        return "redirect:/subject";
    }


    @PostMapping(path = "delete")
    String delete(@RequestParam Integer s_code){
        subjectService.delete(s_code);
        return "redirect:/subject";
    }

    @PostMapping(path = "filter")
    public String filter(@RequestParam("c_code") String c_code, Model model) {
        
        
        // 全ての科目を取得してモデルに追加
        model.addAttribute("subject", subjectService.findAll());
        
        // クラス情報も再度取得してモデルに追加
        model.addAttribute("clss", classService.findAll());
        
        // フィルター結果を取得
        List<SubjectForm> cbitem = subjectService.findByC_code(c_code);
        model.addAttribute("filter", cbitem);
        
        // フィルター後のリストを表示するためのテンプレートを返す
        return "subject/list"; // または、必要なテンプレート名
    }
    
    @PostMapping(path = "set")
    String setsubject(){
        return "subject/set";
    }

    @PostMapping(path = "redirect")
    String redirect(){
        return "redirect:/subject";
    }

    @PostMapping(path = "goToTop")
    String goToTop(){
        return "redirect:/subject";
    }

}

