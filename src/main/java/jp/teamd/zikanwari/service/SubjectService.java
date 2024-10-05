package jp.teamd.zikanwari.service;

import java.util.*;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.teamd.zikanwari.bean.SubjectBean;
import jp.teamd.zikanwari.form.SubjectForm;
import jp.teamd.zikanwari.repository.SubjectRepository;

@Service
public class SubjectService {
    @Autowired
    SubjectRepository SubjectRepository;

    public SubjectForm create(SubjectForm subjectForm){
        SubjectBean subjectBean = new SubjectBean();
        BeanUtils.copyProperties(subjectForm, subjectBean);
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
}
