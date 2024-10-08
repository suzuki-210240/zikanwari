package jp.teamd.zikanwari.repository.subject;

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
        
        Integer ret = query.getSingleResult(); // 結果のリストを取得
        
        if(ret == null){
            jpql = "SELECT c.c_class FROM ClassBean c WHERE c.c_code = :c_code"; // クラス名を使用
            query = entityManager.createQuery(jpql, Integer.class);
            query.setParameter("c_code", c_code);
            List<Integer> codeList = query.getResultList();
            jpql = "SELECT c.c_year FROM ClassBean c WHERE c.c_code = :c_code"; // クラス名を使用
            query = entityManager.createQuery(jpql, Integer.class);
            query.setParameter("c_code", c_code);
            List<Integer> yearList = query.getResultList();
            if (!codeList.isEmpty() && codeList.get(0) != null && !yearList.isEmpty() && yearList.get(0) != null) {
                ret = (codeList.get(0) * 10000) + (yearList.get(0) * 100);
            } else {
                ret = 1; // クラス情報がない場合
            }

        }else{
            ret = ret + 1; 
        }
    
        return ret; 
    }
}
