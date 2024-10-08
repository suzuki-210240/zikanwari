package jp.teamd.zikanwari.repository.e_subject;

import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Component;
import jp.teamd.zikanwari.bean.E_SubjectBean;
@Component
public class E_SubjectRepositoryCustomImpl implements E_SubjectRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<E_SubjectBean> set(String es_code, Integer sub_code, Integer weak_frame) {
        // JPQLを使ってクエリを実行
        String jpql = "SELECT s FROM E_SubjectBean s WHERE s.es_code = :es_code"; // クラス名を使用
        TypedQuery<E_SubjectBean> query = entityManager.createQuery(jpql, E_SubjectBean.class);
        query.setParameter("es_code", es_code); // パラメータを設定
        return query.getResultList(); // 結果を返す
    }

    @Override
    public String create_code() {
        // JPQLを使ってクエリを実行
        String ret = "e";
        String jpql = "SELECT MAX(s.s_number) FROM E_SubjectBean s"; // クラス名を使用
        TypedQuery<Integer> query = entityManager.createQuery(jpql, Integer.class);
        List<Integer> resultList = query.getResultList(); // パラメータを設定
        ret = ret + String.valueOf(resultList.get(0) + 1);
        return ret; // 結果を返す
    }

    @Override
    public Integer get_number(){
        String jpql = "SELECT MAX(s.s_number) FROM E_SubjectBean s"; // クラス名を使用
        TypedQuery<Integer> query = entityManager.createQuery(jpql, Integer.class);
        Integer resultList = query.getSingleResult(); // パラメータを設定
        Integer ret = resultList + 1;
        return ret; // 結果を返す
    }

    @Override
    public List<Integer> get_subcode() {
        // JPQLを使ってクエリを実行
        String jpql = "SELECT s.sub_code FROM E_SubjectBean s WHERE s.setflg > 0"; // クラス名を使用
        TypedQuery<Integer> query = entityManager.createQuery(jpql, Integer.class);
        
        return query.getResultList(); // 結果を返す
    }
}
