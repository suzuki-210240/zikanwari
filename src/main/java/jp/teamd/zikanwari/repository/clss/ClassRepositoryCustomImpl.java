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
}
