package jp.teamd.zikanwari.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jp.teamd.zikanwari.form.KomaForm;
import jp.teamd.zikanwari.form.SubjectForm;
import jp.teamd.zikanwari.service.KomaService;
import jp.teamd.zikanwari.service.SubjectService;


@Controller
@RequestMapping("koma")
public class KomaController {
    @Autowired
    KomaService komaService;
    @Autowired
    SubjectService subjectService;

    @ModelAttribute
    KomaForm setUpForm(){
        return new KomaForm();
    }

    @GetMapping
    String list(Model model){
        model.addAttribute("koma",komaService.findAll());
        return "koma/list";
    }

    @PostMapping(path="create")
    String create(KomaForm form,Model model){
        if(komaService.create(form)){
        }else{
            model.addAttribute("message", "この科目は週の上限分登録済みです。");
        }
        return "redirect:/koma";
    }

    @PostMapping(path = "edit",params = "form")
    String editForm(@RequestParam String season,Integer d_code,Integer s_code,String dayofweak,KomaForm form){
        KomaForm bookForm = komaService.findOne(season,d_code,s_code,dayofweak);
        BeanUtils.copyProperties(bookForm, form);
        return "koma/edit";
    }

    @PostMapping(path = "edit")
    String edit(@RequestParam Integer id,KomaForm form){
        komaService.update(form);
        return "redirect:/koma";
    }

    @PostMapping(path = "delete")
    String delete(@RequestParam String season,Integer d_code,Integer s_code,String dayofweak){
        komaService.delete(season,d_code,s_code,dayofweak);
        return "redirect:/koma";
    }

    @PostMapping(path = "edit",params = "goToTop")
    String goToTop(){
        return "redirect:/koma";
    }

    @PostMapping(path = "gohome")
    String gohome(){
        return "/home";
    }

    @PostMapping(path = "set")
    String setkoma(){
        return "koma/set";
    }
}

