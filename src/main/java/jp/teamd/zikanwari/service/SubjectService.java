package jp.teamd.zikanwari.service;

import java.util.*;

//import org.hibernate.mapping.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import jp.teamd.zikanwari.bean.SubjectBean;
import jp.teamd.zikanwari.bean.E_SubjectBean;
import jp.teamd.zikanwari.bean.L_SubjectBean;
import jp.teamd.zikanwari.form.SubjectForm;
import jp.teamd.zikanwari.repository.subjectrep.All_SubjectRepositoryCustom;
import jp.teamd.zikanwari.repository.subjectrep.E_SubjectRepositoryCustom;
import jp.teamd.zikanwari.repository.subjectrep.E_SubjectRepository;
import jp.teamd.zikanwari.repository.subjectrep.L_SubjectRepositoryCustom;
import jp.teamd.zikanwari.repository.subjectrep.L_SubjectRepository;
import jp.teamd.zikanwari.repository.subjectrep.SubjectRepository;
import jp.teamd.zikanwari.repository.subjectrep.SubjectRepositoryCustom;

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
        subjectBean.set_all(s_code, s_name, c_code, t_number, s_classification, use_room_number);
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

    @Transactional
    public void setSubject(Integer s_code,String s_name,String c_code,Integer t_number,Integer s_classification,Integer weak_frame,Integer use_room_number){

        if(s_classification == 0){
            //通年
        }else if(s_classification == 1){
            //前期
            String es_code = e_SubjectRepositoryCustom.create_code();
            E_SubjectBean e_subjectBean = new E_SubjectBean();
            
            e_subjectBean.set_es_code(es_code);
            e_subjectBean.set_all(s_code, weak_frame);
            e_SubjectRepository.save(e_subjectBean);

        }else if(s_classification == 2){
            //後期
            String ls_code = l_SubjectRepositoryCustom.create_code();
            L_SubjectBean l_SubjectBean = new L_SubjectBean();

            l_SubjectBean.setLs_code(ls_code);
            l_SubjectBean.set_all(s_code, weak_frame);
            l_SubjectRepository.save(l_SubjectBean);
        }
    }
}
