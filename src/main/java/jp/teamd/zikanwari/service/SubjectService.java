package jp.teamd.zikanwari.service;

import java.util.*;

//import org.hibernate.mapping.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.teamd.zikanwari.bean.SubjectBean;
import jp.teamd.zikanwari.bean.E_SubjectBean;
import jp.teamd.zikanwari.bean.L_SubjectBean;
import jp.teamd.zikanwari.form.SubjectForm;
import jp.teamd.zikanwari.repository.all_subject.All_SubjectRepositoryCustom;
import jp.teamd.zikanwari.repository.e_subject.E_SubjectRepository;
import jp.teamd.zikanwari.repository.e_subject.E_SubjectRepositoryCustom;
import jp.teamd.zikanwari.repository.l_subject.L_SubjectRepository;
import jp.teamd.zikanwari.repository.l_subject.L_SubjectRepositoryCustom;
import jp.teamd.zikanwari.repository.subject.SubjectRepository;
import jp.teamd.zikanwari.repository.subject.SubjectRepositoryCustom;

@Service
public class SubjectService {
    @Autowired
    SubjectRepository SubjectRepository;
    @Autowired
    SubjectRepositoryCustom SubjectRepositoryCustom;
    @Autowired
    E_SubjectRepositoryCustom e_SubjectRepositoryCustom;
    @Autowired
    E_SubjectRepository e_SubjectRepository;
    @Autowired
    L_SubjectRepositoryCustom l_SubjectRepositoryCustom;
    @Autowired
    L_SubjectRepository l_SubjectRepository;
    @Autowired
    All_SubjectRepositoryCustom all_SubjectRepositoryCustom;
    

    public void create(Integer s_code,String s_name,String c_code,Integer t_number,Integer s_classification,Integer use_room_number){
        SubjectBean subjectBean = new SubjectBean();
        subjectBean.setS_code(s_code);
        subjectBean.setS_name(s_name);
        subjectBean.setC_code(c_code);
        subjectBean.setT_number(t_number);
        subjectBean.setS_classification(s_classification);
        subjectBean.setUse_room_number(use_room_number);
        SubjectRepository.save(subjectBean);
    }

    public SubjectForm update(SubjectForm subjectForm){
        SubjectBean subjectBean = new SubjectBean();
        BeanUtils.copyProperties(subjectForm, subjectBean);
        SubjectRepository.save(subjectBean);
        return subjectForm;
    }

    public void delete(Integer s_code){
        SubjectRepository.deleteById(s_code);
    }
    public List<SubjectForm> findAll(){
        List<SubjectBean> beanList = SubjectRepository.findAll();
        List<SubjectForm> formList = new ArrayList<SubjectForm>();
        for(SubjectBean subjectBean: beanList){
            SubjectForm subjectForm = new SubjectForm();
            BeanUtils.copyProperties(subjectBean, subjectForm);
            formList.add(subjectForm);
        }
        return formList;
    }
    public SubjectForm findOne(Integer s_code){
        Optional<SubjectBean> opt = SubjectRepository.findById(s_code);
        SubjectForm subjectForm = new SubjectForm();
        opt.ifPresent(clas ->{
            BeanUtils.copyProperties(opt.get(), subjectForm);
        });
        return subjectForm;
    }

    public List<SubjectForm> findByC_code(String c_code){
        List<SubjectBean> beanList;
        List<SubjectForm> formList = new ArrayList<SubjectForm>();

        if (c_code != null) {
            beanList =  SubjectRepositoryCustom.search(c_code);
        }else{
            beanList = SubjectRepository.findAll();
        }
        
        
        for(SubjectBean subjectBean: beanList){
            SubjectForm subjectForm = new SubjectForm();
            BeanUtils.copyProperties(subjectBean, subjectForm);
            formList.add(subjectForm);
        }
        return formList;
    }

    public Integer getCode(String c_code){
        return SubjectRepositoryCustom.getcode(c_code);
    }

    public void setSubject(Integer s_code,String s_name,String c_code,Integer t_number,Integer s_classification,Integer weak_frame,Integer use_room_number){

        if(s_classification == 0){
            //通年
        }else if(s_classification == 1){
            //前期
            String es_code = e_SubjectRepositoryCustom.create_code();
            Integer s_number = e_SubjectRepositoryCustom.get_number();
            E_SubjectBean e_subjectBean = new E_SubjectBean();
            
            e_subjectBean.setEs_code(es_code);
            e_subjectBean.setSub_code(s_code);
            e_subjectBean.setWeak_frame(weak_frame);
            e_subjectBean.setS_number(s_number);
            e_subjectBean.setSetflg(weak_frame);
            e_SubjectRepository.save(e_subjectBean);

        }else if(s_classification == 2){
            //後期
            String ls_code = l_SubjectRepositoryCustom.create_code();
            Integer s_number = l_SubjectRepositoryCustom.get_number();
            L_SubjectBean l_subjectBean = new L_SubjectBean();

            l_subjectBean.setLs_code(ls_code);
            l_subjectBean.setSub_code(s_code);
            l_subjectBean.setWeak_frame(weak_frame);
            l_subjectBean.setS_number(s_number);
            l_subjectBean.setSetflg(weak_frame);
            l_SubjectRepository.save(l_subjectBean);
        }
    }
}
