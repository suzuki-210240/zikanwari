package jp.teamd.zikanwari.repository.subjectrep;

import java.util.List;
/*import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.springframework.stereotype.Repository;
import org.springframework.beans.BeanUtils;*/
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import jp.teamd.zikanwari.bean.L_SubjectBean;



public interface L_SubjectRepository extends JpaRepository<L_SubjectBean,String>{

    @Query("SELECT X FROM L_SubjectBean X")
    List<L_SubjectBean> findAllSubjectBeans();

}
