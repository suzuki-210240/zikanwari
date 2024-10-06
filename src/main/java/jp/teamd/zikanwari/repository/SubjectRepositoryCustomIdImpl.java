package jp.teamd.zikanwari.repository;

import java.util.List;
import org.springframework.stereotype.Component;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jp.teamd.zikanwari.bean.SubjectBean;

@Component
public class SubjectRepositoryCustomIdImpl implements SubjectRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<SubjectBean> search(String c_code) {
        // JPQLを使ってクエリを実行
        String query = "SELECT s FROM SubjectBean s WHERE s.code = :c_code";
        return entityManager.createQuery(query, SubjectBean.class)
                            .setParameter("c_code", c_code)
                            .getResultList();
    }
}
