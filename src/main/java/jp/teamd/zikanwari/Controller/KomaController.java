package jp.teamd.zikanwari.Controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jp.teamd.zikanwari.From.KomaForm;
import jp.teamd.zikanwari.Service.KomaService;


@Controller
@RequestMapping("pages")
public class KomaController {
    @Autowired
    KomaService bookService;

    @ModelAttribute
    KomaForm setUpForm(){
        return new KomaForm();
    }

    @GetMapping
    String list(Model model){
        model.addAttribute("pages",bookService.findAll());
        return "pages/list";
    }

    @PostMapping(path="create")
    String create(KomaForm form,Model mode){
        bookService.create(form);
        return "redirect:/pages";
    }

    @PostMapping(path = "edit",params = "form")
    String editForm(@RequestParam String season,Integer d_code,Integer s_code,String dayofweak,KomaForm form){
        KomaForm bookForm = bookService.findOne(season,d_code,s_code,dayofweak);
        BeanUtils.copyProperties(bookForm, form);
        return "pages/edit";
    }

    @PostMapping(path = "edit")
    String edit(@RequestParam Integer id,KomaForm form){
        bookService.update(form);
        return "redirect:/pages";
    }

    @PostMapping(path = "delete")
    String delete(@RequestParam String season,Integer d_code,Integer s_code,String dayofweak){
        bookService.delete(season,d_code,s_code,dayofweak);
        return "redirect:/pages";
    }

    @PostMapping(path = "edit",params = "goToTop")
    String goToTop(){
        return "redirect:/pages";
    }
}

