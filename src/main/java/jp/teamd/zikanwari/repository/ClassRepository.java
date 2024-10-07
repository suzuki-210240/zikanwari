package jp.teamd.zikanwari.repository;

import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import jp.teamd.zikanwari.bean.ClassBean;

public interface ClassRepository extends JpaRepository<ClassBean,String>{

    @Query("SELECT X FROM ClassBean X")
    List<ClassBean> findAllClassBeans();
    
}
