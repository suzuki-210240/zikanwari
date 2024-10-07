package jp.teamd.zikanwari.service;

import java.util.*;

//import org.hibernate.mapping.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import jp.teamd.zikanwari.bean.SubjectBean;
import jp.teamd.zikanwari.form.SubjectForm;
import jp.teamd.zikanwari.repository.subjectrep.All_SubjectRepositoryCustom;
import jp.teamd.zikanwari.repository.subjectrep.E_SubjectRepositoryCustom;
import jp.teamd.zikanwari.repository.subjectrep.L_SubjectRepositoryCustom;
import jp.teamd.zikanwari.repository.subjectrep.SubjectRepository;
import jp.teamd.zikanwari.repository.subjectrep.SubjectRepositoryCustomIdImpl;

@Service
public class SubjectService {
    @Autowired
    SubjectRepository SubjectRepository;
    @Autowired
    SubjectRepositoryCustomIdImpl SubjectRepositoryCustom;
    @Autowired
    E_SubjectRepositoryCustom e_SubjectRepositoryCustom;
    @Autowired
    L_SubjectRepositoryCustom l_SubjectRepositoryCustom;
    @Autowired
    All_SubjectRepositoryCustom All_SubjectRepositoryCustom;
    

    public SubjectForm create(SubjectForm subjectForm ,Integer s_code){
        SubjectBean subjectBean = new SubjectBean();
        BeanUtils.copyProperties(subjectForm, subjectBean);
        subjectBean.setS_code(s_code);
        SubjectRepository.save(subjectBean);
        return subjectForm;
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
    public void setSubject(Integer s_code,String s_name,String c_code,Integer t_number,Integer s_classification,Integer use_room_number){

        if(s_classification == 0){
            //通年
        }else if(s_classification == 1){
            //前期
        }else if(s_classification == 2){
            //後期
            String ls_code = l_SubjectRepositoryCustom.create_code();
        }
    }
}
