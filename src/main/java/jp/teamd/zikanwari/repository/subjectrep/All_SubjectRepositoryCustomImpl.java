package jp.teamd.zikanwari.repository.subjectrep;

import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Component;
import jp.teamd.zikanwari.bean.All_SubjectBean;

@Component
public class All_SubjectRepositoryCustomImpl implements All_SubjectRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<All_SubjectBean> set(String es_code, Integer sub_code, Integer weak_frame) {
        // JPQLを使ってクエリを実行
        String jpql = "SELECT s FROM All_SubjectBean s WHERE s.es_code = :es_code"; // クラス名を使用
        TypedQuery<All_SubjectBean> query = entityManager.createQuery(jpql, All_SubjectBean.class);
        query.setParameter("es_code", es_code); // パラメータを設定
        return query.getResultList(); // 結果を返す
    }
}
