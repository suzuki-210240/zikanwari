package jp.teamd.zikanwari.repository.e_subject;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import jp.teamd.zikanwari.bean.E_SubjectBean;
public interface E_SubjectRepository extends JpaRepository<E_SubjectBean,String>{

    @Query("SELECT X FROM E_SubjectBean X")
    List<E_SubjectBean> findAllSubjectBeans();

}
