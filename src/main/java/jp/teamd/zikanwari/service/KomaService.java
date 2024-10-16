package jp.teamd.zikanwari.service;

import java.util.*;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import jp.teamd.zikanwari.KomaId;
import jp.teamd.zikanwari.bean.KomaBean;
import jp.teamd.zikanwari.form.KomaForm;
import jp.teamd.zikanwari.bean.OnlineBean;
import jp.teamd.zikanwari.repository.koma.KomaRepository;
import jp.teamd.zikanwari.repository.koma.KomaRepositoryCustom;
import jp.teamd.zikanwari.repository.OnlineRepository;
import jp.teamd.zikanwari.repository.clss.ClassRepositoryCustom;

import jp.teamd.zikanwari.KomaId;

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

        System.out.println("1");
        
        Integer s_code = komaForm.getS_code();
        String season = komaRepositoryCustom.get_season(s_code);
        Integer r_number = komaRepositoryCustom.get_room(s_code);
        Integer t_number = komaRepositoryCustom.get_tnumber(s_code);
        Integer setflg = komaRepositoryCustom.get_setflg(season, s_code);
        Integer onlineflg = 0;
        
        System.out.println("2");
        if(setflg > 0){
            Integer btime = komaRepositoryCustom.get_btime(s_code);
            System.out.println("3");
            String onlineday = classRepositoryCustom.get_onlineday(s_code);

            String[] days = new String[]{"月", "火", "水", "木", "金"};
            Integer[] f_time = new Integer[]{1,2,31,32,4,5};
            System.out.println("4");

            //登録されていない週コマ数分繰り返し
            for(int i = 0; i < setflg; i++){
                boolean set_on = false; //登録成功時にtrueになる

                //外ループ：曜日
                for(int day = 0;day < days.length; day++){
                    String dayofweak = days[day];
                    if(set_on){
                        break;
                    }
                    //内ループ：コマの枠
                    for(int time = 0; time < f_time.length; time++){
                        Integer d_code = f_time[time];
                        //休憩時間だったらとばす
                        if(d_code == btime){
                            continue;
                        }

                        //使用教室の重複確認
                        if(komaRepositoryCustom.check_room(season, d_code, dayofweak, r_number)){
                            //担当教員の重複確認
                            if(komaRepositoryCustom.check_teacher(season,s_code ,d_code, dayofweak, t_number)){
                                //オンラインに指定されている日か
                                if(onlineday == dayofweak){
                                    //オンライン日内で重複がないか確認
                                    if(komaRepositoryCustom.cover_online(season, d_code, s_code, btime) > 0){
                                        onlineBean.setSeason(season);
                                        onlineBean.setD_code(d_code);
                                        onlineBean.setS_code(s_code);
                                        onlineRepository.save(onlineBean);
                                        onlineflg = 1;
                                        r_number = 9999;
                                        komaForm.setSeason(season);
                                        komaForm.setR_number(r_number);
                                        komaForm.setOnlineflg(onlineflg);
                                        komaForm.setDayofweak(dayofweak);
                                        komaForm.setD_code(d_code);
                                        BeanUtils.copyProperties(komaForm, komaBean);
                                        komaRepository.save(komaBean);
                                        set_on = true;
                                        komaRepositoryCustom.update_setflg(season, s_code);
                                        break;
                                    }
                                }else{
                                        komaForm.setSeason(season);
                                        komaForm.setR_number(r_number);
                                        komaForm.setOnlineflg(onlineflg);
                                        komaForm.setDayofweak(dayofweak);
                                        komaForm.setD_code(d_code);
                                        BeanUtils.copyProperties(komaForm, komaBean);
                                        komaRepository.save(komaBean);
                                        set_on = true;
                                        komaRepositoryCustom.update_setflg(season, s_code);
                                        break;
                                }
                            }
                        }
                    }
                }
                
            }
        }else{
            retflg = false;
        }

        return retflg;
    }

    // public KomaForm update(KomaForm komaForm,String season, Integer d_code,Integer s_code,String dayofweak,Integer r_number) {
    // // IDを使って既存のKomaBeanを取得
    // KomaId komaId = KomaId.KomaId();
    // KomaBean existingKomaBean = komaRepository.findById(null)
    //         .orElseThrow(() -> new EntityNotFoundException("Koma not found with ID: " + komaForm.getId()));

    // // プロパティをコピー
    // BeanUtils.copyProperties(komaForm, existingKomaBean, "id"); // idを除外して上書きしない

    // // 更新処理
    // return komaRepository.save(existingKomaBean);
//}


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

    public boolean check_room(String season, Integer d_code,String dayofweak,Integer r_number){
        if(komaRepositoryCustom.check_room(season, d_code, dayofweak, r_number)){
            return true; 
        }else{
            return false; 
        }
    }
    public boolean check_teacher(String season, Integer d_code,String dayofweak,Integer r_number,Integer s_code){
        Integer t_number = komaRepositoryCustom.get_tnumber(s_code);
        if(komaRepositoryCustom.check_teacher(season, s_code, d_code, dayofweak, t_number)){
            return true; 
        }else{
            return false; 
        }
    }
    public boolean check_setflg(String season,Integer s_code){
        System.out.println();
        Integer setflg = komaRepositoryCustom.get_setflg(season, s_code);
        if(setflg > 0){
            return true;
        }else{
            return false;
        }
    }
}
