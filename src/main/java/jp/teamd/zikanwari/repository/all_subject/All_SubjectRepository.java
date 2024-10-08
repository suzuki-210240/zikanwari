package jp.teamd.zikanwari.repository.all_subject;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import jp.teamd.zikanwari.bean.All_SubjectBean;



public interface All_SubjectRepository extends JpaRepository<All_SubjectBean,String>{

    @Query("SELECT X FROM All_SubjectBean X")
    List<All_SubjectBean> findAllSubjectBeans();

}
