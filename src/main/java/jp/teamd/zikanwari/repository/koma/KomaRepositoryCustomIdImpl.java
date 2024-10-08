package jp.teamd.zikanwari.repository.koma;

import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jp.teamd.zikanwari.bean.KomaBean;
import org.springframework.stereotype.Component;

@Component
public class KomaRepositoryCustomIdImpl implements KomaRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<KomaBean> search(String c_code) {
        // JPQLを使ってクエリを実行
        String jpql = "SELECT s FROM KomaBean s WHERE s.c_code = :c_code"; // クラス名を使用
        TypedQuery<KomaBean> query = entityManager.createQuery(jpql, KomaBean.class);
        query.setParameter("c_code", c_code);
        return query.getResultList(); // 結果を返す
    }

    @Override
    public Integer get_btime(Integer s_code) {
    // JPQLを使ってクエリを実行
        
        String jpql = "SELECT s.c_code FROM SubjectBean s WHERE s.s_code = :s_code"; // クラス名を使用
        TypedQuery<Integer> query = entityManager.createQuery(jpql, Integer.class);
        query.setParameter("s_code", s_code);
        
        Integer c_code = query.getSingleResult(); // 結果のリストを取得

        jpql = "SELECT s.bitme FROM ClassBean s WHERE s.c_code = :c_code";
        query = entityManager.createQuery(jpql, Integer.class);
        query.setParameter("c_code", c_code);

        Integer ret = query.getSingleResult();
    
        return ret; 
    }

    @Override
    public Integer get_room(Integer s_code) {
    // JPQLを使ってクエリを実行
        
        String jpql = "SELECT s.use_room_number FROM SubjectBean s WHERE s.s_code = :s_code"; // クラス名を使用
        TypedQuery<Integer> query = entityManager.createQuery(jpql, Integer.class);
        query.setParameter("s_code", s_code);
        
        Integer room_num = query.getSingleResult();
        return room_num; 
    }
}
