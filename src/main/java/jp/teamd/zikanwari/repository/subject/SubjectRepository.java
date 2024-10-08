package jp.teamd.zikanwari.repository.subject;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import jp.teamd.zikanwari.bean.SubjectBean;



public interface SubjectRepository extends JpaRepository<SubjectBean,Integer>{

    @Query("SELECT X FROM SubjectBean X")
    List<SubjectBean> findAllSubjectBeans();

}
