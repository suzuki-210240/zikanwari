package jp.teamd.zikanwari.repository.subjectrep;

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

    @Override
    public Integer getcode(String c_code) {
    // JPQLを使ってクエリを実行
    String jpql = "SELECT MAX(s.s_code) FROM SubjectBean s WHERE s.c_code = :c_code"; // クラス名を使用
    TypedQuery<Integer> query = entityManager.createQuery(jpql, Integer.class);
    query.setParameter("c_code", c_code);
    
    List<Integer> resultList = query.getResultList(); // 結果のリストを取得
    Integer ret = 0;
    if(resultList.size() == 0){
        jpql = "SELECT c.class FROM class c WHERE c.c_code = :c_code"; // クラス名を使用
        query = entityManager.createQuery(jpql, Integer.class);
        query.setParameter("c_code", c_code);
        List<Integer> 
    }else{
       ret = resultList.get(0); 
    }
    return ret; 
}
}
