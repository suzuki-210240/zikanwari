package jp.teamd.zikanwari;

import java.util.*;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KomaService {
    @Autowired
    KomaRepository bookRepository;

    public KomaForm create(KomaForm komaForm){
        //bookForm.setId(bookRepository.getBookId());
        KomaBean komaBean = new KomaBean();
        BeanUtils.copyProperties(komaForm, komaBean);
        bookRepository.save(komaBean);
        return komaForm;
    }

    public KomaForm update(KomaForm komaForm){
        KomaBean komaBean = new KomaBean();
        BeanUtils.copyProperties(komaForm, komaBean);
        bookRepository.save(komaBean);
        return komaForm;
    }

    public void delete(String season,Integer d_code,Integer s_code,String dayofweak){
        KomaId komaId = new KomaId(season,d_code,s_code,dayofweak);
        bookRepository.deleteById(komaId);
    }
    public List<KomaForm> findAll(){
        List<KomaBean> beanList = bookRepository.findAll();
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
        Optional<KomaBean> opt = bookRepository.findById(komaId);
        KomaForm bookForm = new KomaForm();
        opt.ifPresent(book ->{
            BeanUtils.copyProperties(opt.get(), bookForm);
        });
        return bookForm;
    }
}
