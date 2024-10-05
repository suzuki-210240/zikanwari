package jp.teamd.zikanwari.repository;

import java.util.*;
/*import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.springframework.stereotype.Repository;
import org.springframework.beans.BeanUtils;*/
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import jp.teamd.zikanwari.bean.SubjectBean;

public interface SubjectRepository extends JpaRepository<SubjectBean,Integer>{

    @Query("SELECT X FROM SubjectBean X")
    List<SubjectBean> findAllSubjectBeans();
    
}
