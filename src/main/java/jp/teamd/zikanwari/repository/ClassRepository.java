package jp.teamd.zikanwari.repository;

import java.util.*;
/*import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.springframework.stereotype.Repository;
import org.springframework.beans.BeanUtils;*/
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import jp.teamd.zikanwari.bean.ClassBean;

public interface ClassRepository extends JpaRepository<ClassBean,Integer>{

    @Query("SELECT X FROM ClassBean X")
    List<ClassBean> findAllClassBeans();
    
}
