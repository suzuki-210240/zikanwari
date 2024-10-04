package jp.teamd.zikanwari.service;

import java.util.*;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.teamd.zikanwari.bean.classBean;
import jp.teamd.zikanwari.form.classForm;
import jp.teamd.zikanwari.repository.classRepository;

@Service
public class classService {
    @Autowired
    classRepository classRepository;

    public classForm create(classForm classForm){
        classBean classBean = new classBean();
        BeanUtils.copyProperties(classForm, classBean);
        classRepository.save(classBean);
        return classForm;
    }

    public classForm update(classForm classForm){
        classBean classBean = new classBean();
        BeanUtils.copyProperties(classForm, classBean);
        classRepository.save(classBean);
        return classForm;
    }

    public void delete(Integer c_code){
        classRepository.deleteById(c_code);
    }
    public List<classForm> findAll(){
        List<classBean> beanList = classRepository.findAll();
        List<classForm> formList = new ArrayList<classForm>();
        for(classBean classBean: beanList){
            classForm classForm = new classForm();
            BeanUtils.copyProperties(classBean, classForm);
            formList.add(classForm);
        }
        return formList;
    }
    public classForm findOne(Integer c_code){
        Optional<classBean> opt = classRepository.findById(c_code);
        classForm classForm = new classForm();
        opt.ifPresent(class ->{
            BeanUtils.copyProperties(opt.get(), classForm);
        });
        return classForm;
    }
}
