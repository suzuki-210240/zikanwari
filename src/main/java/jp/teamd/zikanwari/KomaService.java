package jp.teamd.zikanwari;

import java.util.Optional;

import java.util.*;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KomaService {
    @Autowired
    KomaRepository bookRepository;

    public KomaForm create(KomaForm bookForm){
        //bookForm.setId(bookRepository.getBookId());
        KomaBean komaBean = new KomaBean();
        BeanUtils.copyProperties(bookForm, komaBean);
        bookRepository.save(komaBean);
        return bookForm;
    }

    public KomaForm update(KomaForm bookForm){
        KomaBean komaBean = new KomaBean();
        BeanUtils.copyProperties(bookForm, komaBean);
        bookRepository.save(komaBean);
        return bookForm;
    }

    public void delete(Integer id){
        bookRepository.deleteById(id);
    }
    public List<KomaForm> findAll(){
        List<KomaBean> beanList = bookRepository.findAll();
        List<KomaForm> formList = new ArrayList<KomaForm>();
        for(KomaBean komaBean: beanList){
            KomaForm bookForm = new KomaForm();
            BeanUtils.copyProperties(komaBean, bookForm);
            formList.add(bookForm);
        }
        return formList;
    }
    public KomaForm findOne(Integer id){
        Optional<KomaBean> opt = bookRepository.findById(id);
        KomaForm bookForm = new KomaForm();
        opt.ifPresent(book ->{
            BeanUtils.copyProperties(opt.get(), bookForm);
        });
        return bookForm;
    }
}
