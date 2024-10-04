package jp.teamd.zikanwari.service;

import java.util.*;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.teamd.zikanwari.bean.DepartmentBean;
import jp.teamd.zikanwari.form.DepartmentForm;
import jp.teamd.zikanwari.repository.DepartmentRepository;

@Service
public class DepartmentService {
    @Autowired
    DepartmentRepository departmentRepository;

    public DepartmentForm create(DepartmentForm departmentForm){
        DepartmentBean departmentBean = new DepartmentBean();
        BeanUtils.copyProperties(departmentForm, departmentBean);
        departmentRepository.save(departmentBean);
        return departmentForm;
    }

    public DepartmentForm update(DepartmentForm departmentForm){
        DepartmentBean departmentBean = new DepartmentBean();
        BeanUtils.copyProperties(departmentForm, departmentBean);
        departmentRepository.save(departmentBean);
        return departmentForm;
    }

    public void delete(Integer d_code){
        departmentRepository.deleteById(d_code);
    }
    public List<DepartmentForm> findAll(){
        List<DepartmentBean> beanList = departmentRepository.findAll();
        List<DepartmentForm> formList = new ArrayList<DepartmentForm>();
        for(DepartmentBean departmentBean: beanList){
            DepartmentForm departmentForm = new DepartmentForm();
            BeanUtils.copyProperties(departmentBean, departmentForm);
            formList.add(departmentForm);
        }
        return formList;
    }
    public DepartmentForm findOne(Integer d_code){
        Optional<DepartmentBean> opt = departmentRepository.findById(d_code);
        DepartmentForm departmentForm = new DepartmentForm();
        opt.ifPresent(department ->{
            BeanUtils.copyProperties(opt.get(), departmentForm);
        });
        return departmentForm;
    }
}
