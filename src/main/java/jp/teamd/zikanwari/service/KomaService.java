package jp.teamd.zikanwari.service;

import java.util.*;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.teamd.zikanwari.KomaId;
import jp.teamd.zikanwari.bean.KomaBean;
import jp.teamd.zikanwari.form.KomaForm;
import jp.teamd.zikanwari.form.OnlineForm;
import jp.teamd.zikanwari.bean.OnlineBean;
import jp.teamd.zikanwari.repository.koma.KomaRepository;
import jp.teamd.zikanwari.repository.koma.KomaRepositoryCustom;
import jp.teamd.zikanwari.repository.OnlineRepository;
import jp.teamd.zikanwari.repository.clss.ClassRepositoryCustom;

@Service
public class KomaService {
    @Autowired
    KomaRepository komaRepository;
    @Autowired
    KomaRepositoryCustom komaRepositoryCustom;

    @Autowired
    OnlineRepository onlineRepository;

    @Autowired
    ClassRepositoryCustom classRepositoryCustom;

    public KomaForm create(KomaForm komaForm){
        KomaBean komaBean = new KomaBean();
        OnlineBean onlineBean = new OnlineBean();
        OnlineForm onlineForm = new OnlineForm();

        String season = komaForm.getSeason();
        Integer d_code = komaForm.getD_code();
        String dayofweak = komaForm.getDayofweak();
        Integer s_code = komaForm.getS_code();
        
        Integer btime = komaRepositoryCustom.get_btime(s_code);
        String onlineday = classRepositoryCustom.get_onlineday(s_code);

        String[] days = {"月", "火", "水", "木", "金"};
        Integer[] f_time = {1,2,31,32,4,5};

        
        BeanUtils.copyProperties(komaForm, komaBean);
        komaRepository.save(komaBean);
        return komaForm;
    }

    public KomaForm update(KomaForm komaForm){
        KomaBean komaBean = new KomaBean();
        BeanUtils.copyProperties(komaForm, komaBean);
        komaRepository.save(komaBean);
        return komaForm;
    }

    public void delete(String season,Integer d_code,Integer s_code,String dayofweak){
        KomaId komaId = new KomaId(season,d_code,s_code,dayofweak);
        komaRepository.deleteById(komaId);
    }

    public List<KomaForm> findAll(){
        List<KomaBean> beanList = komaRepository.findAll();
        List<KomaForm> formList = new ArrayList<KomaForm>();
        for(KomaBean komaBean: beanList){
            KomaForm komaForm = new KomaForm();
            BeanUtils.copyProperties(komaBean, komaForm);
            formList.add(komaForm);
        }
        return formList;
    }
    public KomaForm findOne(String season,Integer d_code,Integer s_code,String dayofweak){
        KomaId komaId = new KomaId(season,d_code,s_code,dayofweak);
        Optional<KomaBean> opt = komaRepository.findById(komaId);
        KomaForm komaForm = new KomaForm();
        opt.ifPresent(koma ->{
            BeanUtils.copyProperties(opt.get(), komaForm);
        });
        return komaForm;
    }
}
