package jp.teamd.zikanwari.repository.clss;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Component;

@Component
public class ClassRepositoryCustomImpl implements ClassRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Integer getcode() {
    // JPQLを使ってクエリを実行
        String jpql = "SELECT MAX(c.c_class) FROM ClassBean c"; // クラス名を使用
        TypedQuery<Integer> query = entityManager.createQuery(jpql, Integer.class);
        
        Integer ret = query.getSingleResult() + 1; // 結果のリストを取得
        
    
        return ret; 
    }

    @Override
    public String get_onlineday(Integer s_code){
        String jpql = "SELECT s.c_code FROM SubjectBean s WHERE s.s_code = :s_code"; // クラス名を使用
        TypedQuery<String> query = entityManager.createQuery(jpql, String.class);
        query.setParameter("s_code", s_code);
        
        String c_code = query.getSingleResult(); // 結果のリストを取得

        jpql = "SELECT c.onlineday FROM ClassBean c WHERE c.c_code = :c_code"; // クラス名を使用
        query = entityManager.createQuery(jpql, String.class);
        query.setParameter("c_code", c_code);
        
        String ret = query.getSingleResult() + 1; // 結果のリストを取得
        
    
        return ret; 
    }
}
