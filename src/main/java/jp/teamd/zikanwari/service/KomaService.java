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

    public Boolean create(KomaForm komaForm){
        boolean retflg = true; 
        KomaBean komaBean = new KomaBean();
        OnlineBean onlineBean = new OnlineBean();

        String season = komaForm.getSeason();
        Integer s_code = komaForm.getS_code();
        Integer r_number = komaRepositoryCustom.get_room(s_code);
        Integer t_number = komaRepositoryCustom.get_tnumber(s_code);
        Integer setflg = komaRepositoryCustom.get_setflg(season, s_code);
        Integer onlineflg = 0;
        
        
        if(setflg > 0){
            Integer btime = komaRepositoryCustom.get_btime(s_code);
            String onlineday = classRepositoryCustom.get_onlineday(s_code);

            String[] days = new String[]{"月", "火", "水", "木", "金"};
            Integer[] f_time = new Integer[]{1,2,31,32,4,5};

            //登録されていない週コマ数分繰り返し
            for(int i = 0; i < setflg; i++){
                boolean seton = false; //登録成功時にtrueになる

                //外ループ：曜日
                for(int day = 0;day < days.length; day++){
                    String dayofweak = days[day];
                    //内ループ：コマの枠
                    for(int time = 0; time < f_time.length; time++){
                        Integer d_code = f_time[time];
                        //休憩時間だったらとばす
                        if(d_code == btime){
                            continue;
                        }

                        if(komaRepositoryCustom.check_room(season, d_code, dayofweak, r_number)){
                            if(komaRepositoryCustom.check_teacher(season,s_code ,d_code, dayofweak, t_number)){
                                if(onlineday == dayofweak){
                                    if(komaRepositoryCustom.cover_online(season, d_code, s_code, btime) > 0){
                                        onlineBean.setSeason(season);
                                        onlineBean.setD_code(d_code);
                                        onlineBean.setS_code(s_code);
                                        onlineRepository.save(onlineBean);
                                    }
                                }else{
                                }
                            }
                        }
                    }
                }
            }

            BeanUtils.copyProperties(komaForm, komaBean);
            komaRepository.save(komaBean);
        }else{
            retflg = false;
        }

        return retflg;
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
