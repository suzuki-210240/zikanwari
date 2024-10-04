package jp.teamd.zikanwari.service;

import java.util.*;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.teamd.zikanwari.bean.ClassBean;
import jp.teamd.zikanwari.form.ClassForm;
import jp.teamd.zikanwari.repository.ClassRepository;

@Service
public class ClassService {
    @Autowired
    ClassRepository ClassRepository;

    public ClassForm create(ClassForm classForm){
        ClassBean classBean = new ClassBean();
        BeanUtils.copyProperties(classForm, classBean);
        ClassRepository.save(classBean);
        return classForm;
    }

    public ClassForm update(ClassForm classForm){
        ClassBean classBean = new ClassBean();
        BeanUtils.copyProperties(classForm, classBean);
        ClassRepository.save(classBean);
        return classForm;
    }

    public void delete(String c_code){
        ClassRepository.deleteById(c_code);
    }
    public List<ClassForm> findAll(){
        List<ClassBean> beanList = ClassRepository.findAll();
        List<ClassForm> formList = new ArrayList<ClassForm>();
        for(ClassBean classBean: beanList){
            ClassForm classForm = new ClassForm();
            BeanUtils.copyProperties(classBean, classForm);
            formList.add(classForm);
        }
        return formList;
    }
    public ClassForm findOne(String c_code){
        Optional<ClassBean> opt = ClassRepository.findById(c_code);
        ClassForm classForm = new ClassForm();
        opt.ifPresent(clas ->{
            BeanUtils.copyProperties(opt.get(), classForm);
        });
        return classForm;
    }
}
