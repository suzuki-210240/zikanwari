package jp.teamd.zikanwari.repository.l_subject;

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


    public L_SubjectRepositoryCustomImpl() {
        // 特に処理は必要ない場合
    }

    @Override
    public List<L_SubjectBean> set(String es_code, Integer sub_code, Integer weak_frame) {
        // JPQLを使ってクエリを実行
        
        String jpql = "SELECT s FROM L_SubjectBean s WHERE s.es_code = :es_code"; // クラス名を使用
        TypedQuery<L_SubjectBean> query = entityManager.createQuery(jpql, L_SubjectBean.class);
        query.setParameter("es_code", es_code); // パラメータを設定
        return query.getResultList(); // 結果を返す
    }

    @Override
    public String create_code() {
        // JPQLを使ってクエリを実行
        String ret = "l";
        String jpql = "SELECT MAX(s.s_number) FROM L_SubjectBean s"; // クラス名を使用
        TypedQuery<Integer> query = entityManager.createQuery(jpql, Integer.class);
        List<Integer> resultList = query.getResultList(); // パラメータを設定
        ret = ret + String.valueOf(resultList.get(0) + 1);
        return ret; // 結果を返す
    }

    @Override
    public Integer get_number(){
        String jpql = "SELECT MAX(s.s_number) FROM L_SubjectBean s"; // クラス名を使用
        TypedQuery<Integer> query = entityManager.createQuery(jpql, Integer.class);
        Integer resultList = query.getSingleResult(); // パラメータを設定
        Integer ret = resultList + 1;
        return ret; // 結果を返す
    }

    @Override
    public Integer get_setflg(Integer s_code){
        String jpql = "SELECT s.setflg FROM L_SubjectBean s WHERE s.s_code = :s_code";
        TypedQuery<Integer> query = entityManager.createQuery(jpql, Integer.class);
        query.setParameter("s_code", s_code);
        Integer ret = query.getSingleResult();
        return ret;
    }

    @Override
    public List<Integer> get_subcode() {
        // JPQLを使ってクエリを実行
        String jpql = "SELECT s.sub_code FROM L_SubjectBean s WHERE s.setflg > 0"; // クラス名を使用
        TypedQuery<Integer> query = entityManager.createQuery(jpql, Integer.class);
        
        return query.getResultList(); // 結果を返す
    }
}
