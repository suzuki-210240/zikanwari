package jp.teamd.zikanwari.repository;

import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jp.teamd.zikanwari.bean.SubjectBean;
import org.springframework.stereotype.Component;

@Component
public class SubjectRepositoryCustomIdImpl implements SubjectRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<SubjectBean> search(String c_code) {
        // JPQLを使ってクエリを実行
        String jpql = "SELECT s FROM SubjectBean s WHERE s.c_code = :c_code"; // クラス名を使用
        TypedQuery<SubjectBean> query = entityManager.createQuery(jpql, SubjectBean.class);
        query.setParameter("c_code", c_code);
        return query.getResultList(); // 結果を返す
    }
}
