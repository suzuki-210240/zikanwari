package jp.teamd.zikanwari;

import java.util.Optional;

import java.util.*;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;

    public BookForm create(BookForm bookForm){
        //bookForm.setId(bookRepository.getBookId());
        BookBean bookBean = new BookBean();
        BeanUtils.copyProperties(bookForm, bookBean);
        bookRepository.save(bookBean);
        return bookForm;
    }

    public BookForm update(BookForm bookForm){
        BookBean bookBean = new BookBean();
        BeanUtils.copyProperties(bookForm, bookBean);
        bookRepository.save(bookBean);
        return bookForm;
    }

    public void delete(Integer id){
        bookRepository.deleteById(id);
    }
    public List<BookForm> findAll(){
        List<BookBean> beanList = bookRepository.findAll();
        List<BookForm> formList = new ArrayList<BookForm>();
        for(BookBean bookBean: beanList){
            BookForm bookForm = new BookForm();
            BeanUtils.copyProperties(bookBean, bookForm);
            formList.add(bookForm);
        }
        return formList;
    }
    public BookForm findOne(Integer id){
        Optional<BookBean> opt = bookRepository.findById(id);
        BookForm bookForm = new BookForm();
        opt.ifPresent(book ->{
            BeanUtils.copyProperties(opt.get(), bookForm);
        });
        return bookForm;
    }
}
