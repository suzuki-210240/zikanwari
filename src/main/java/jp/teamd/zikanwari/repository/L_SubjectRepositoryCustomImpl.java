package jp.teamd.zikanwari.repository;

import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Component;
import jp.teamd.zikanwari.bean.L_SubjectBean;

@Component
public class L_SubjectRepositoryCustomImpl implements L_SubjectRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<L_SubjectBean> set(String es_code, Integer sub_code, Integer weak_frame) {
        // JPQLを使ってクエリを実行
        String jpql = "SELECT s FROM L_SubjectBean s WHERE s.es_code = :es_code"; // クラス名を使用
        TypedQuery<L_SubjectBean> query = entityManager.createQuery(jpql, L_SubjectBean.class);
        query.setParameter("es_code", es_code); // パラメータを設定
        return query.getResultList(); // 結果を返す
    }
}
