package jp.teamd.zikanwari.repository;

import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import jp.teamd.zikanwari.bean.TeacherBean;

public interface TeacherRepository extends JpaRepository<TeacherBean,Integer>{

    @Query("SELECT X FROM TeacherBean X")
    List<TeacherBean> findAllTeacherBeans();
    
}
