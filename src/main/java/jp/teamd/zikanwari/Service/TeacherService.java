package jp.teamd.zikanwari.service;

import java.util.*;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.teamd.zikanwari.bean.TeacherBean;
import jp.teamd.zikanwari.form.TeacherForm;
import jp.teamd.zikanwari.repository.TeacherRepository;

@Service
public class TeacherService {
    @Autowired
    TeacherRepository teacherRepository;

    public TeacherForm create(TeacherForm teacherForm){
        TeacherBean teacherBean = new TeacherBean();
        BeanUtils.copyProperties(teacherForm, teacherBean);
        teacherRepository.save(teacherBean);
        return teacherForm;
    }

    public TeacherForm update(TeacherForm teacherForm){
        TeacherBean teacherBean = new TeacherBean();
        BeanUtils.copyProperties(teacherForm, teacherBean);
        teacherRepository.save(teacherBean);
        return teacherForm;
    }

    public void delete(Integer t_number){
        teacherRepository.deleteById(t_number);
    }
    public List<TeacherForm> findAll(){
        List<TeacherBean> beanList = teacherRepository.findAll();
        List<TeacherForm> formList = new ArrayList<TeacherForm>();
        for(TeacherBean teacherBean: beanList){
            TeacherForm teacherForm = new TeacherForm();
            BeanUtils.copyProperties(teacherBean, teacherForm);
            formList.add(teacherForm);
        }
        return formList;
    }
    public TeacherForm findOne(Integer t_number){
        Optional<TeacherBean> opt = teacherRepository.findById(t_number);
        TeacherForm teacherForm = new TeacherForm();
        opt.ifPresent(teacher ->{
            BeanUtils.copyProperties(opt.get(), teacherForm);
        });
        return teacherForm;
    }
}
