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
    

}

