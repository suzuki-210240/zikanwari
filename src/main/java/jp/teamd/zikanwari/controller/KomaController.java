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

import jp.teamd.zikanwari.bean.KomaBean;
import jp.teamd.zikanwari.form.KomaForm;
import jp.teamd.zikanwari.service.KomaService;
import jp.teamd.zikanwari.service.SubjectService;
import org.springframework.web.bind.annotation.RequestBody;



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
        return "/koma/set";
    }

    @PostMapping(path = "edit",params = "form")
    String editForm(@RequestParam String season,@RequestParam Integer d_code,@RequestParam Integer s_code,@RequestParam String dayofweak,Model model){
        KomaForm form = new KomaForm();
        form.setSeason(season);
        form.setD_code(d_code);
        form.setS_code(s_code);
        form.setDayofweak(dayofweak);
        KomaForm komaForm = komaService.findOne(season,d_code,s_code,dayofweak);
        if (komaForm != null) {
            BeanUtils.copyProperties(komaForm, form);
        }
    
        model.addAttribute("koma", form);
        return "koma/edit";
    }
    
    @PostMapping(path = "check")
    public String check(@RequestParam String season,@RequestParam Integer d_code,@RequestParam Integer s_code,@RequestParam String dayofweak,Model model) {
        
        if(komaService.check_room(season, d_code, dayofweak, s_code)){
            if(komaService.check_teacher(season, d_code, dayofweak, d_code, s_code)){
                model.addAttribute("message", "重複はありませんでした。");
            }else{
                model.addAttribute("message", "入力エラー：教員が重複しています");
            }
        }else{
            model.addAttribute("message", "入力エラー：教室が重複しています");
        }
        return "redirect:/koma"; // 更新後はリダイレクト
    }

    @PostMapping("update")
    public String update(@RequestParam String season,@RequestParam Integer d_code,@RequestParam Integer s_code,@RequestParam String dayofweak) {
        KomaForm form = new KomaForm();
        KomaBean komaBean = new KomaBean();

        form.setSeason(season);
        form.setD_code(d_code);
        form.setS_code(s_code);
        form.setDayofweak(dayofweak);
        komaService.update(form);
        return "redirect:/koma";
    }
    

    @PostMapping(path = "delete")
    String delete(@RequestParam String season,@RequestParam Integer d_code,@RequestParam Integer s_code,@RequestParam String dayofweak){
        KomaForm form = new KomaForm();
        form.setSeason(season);
        form.setD_code(d_code);
        form.setS_code(s_code);
        form.setDayofweak(dayofweak);
        komaService.delete(season,d_code,s_code,dayofweak);
        return "redirect:/koma";
    }

    @PostMapping(path = "goTolist")
    String goTolist(Model model){
        model.addAttribute("koma",komaService.findAll());
        return "koma/list";
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

