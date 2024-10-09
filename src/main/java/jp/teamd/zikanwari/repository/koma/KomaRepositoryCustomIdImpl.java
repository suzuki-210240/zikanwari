package jp.teamd.zikanwari.repository.koma;

import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jp.teamd.zikanwari.bean.KomaBean;
import org.springframework.stereotype.Component;
import jp.teamd.zikanwari.bean.OnlineBean;
import jp.teamd.zikanwari.repository.OnlineRepository;

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
        TypedQuery<String> query = entityManager.createQuery(jpql, String.class);
        query.setParameter("s_code", s_code);
        
        String c_code = query.getSingleResult(); // 結果のリストを取得

        jpql = "SELECT s.bitme FROM ClassBean s WHERE s.c_code = :c_code";
        TypedQuery<Integer>query2 = entityManager.createQuery(jpql, Integer.class);
        query.setParameter("c_code", c_code);

        Integer ret = query2.getSingleResult();
    
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

    @Override
    public boolean check_room(String season,Integer d_code,String dayofweak,Integer r_number) {
    // JPQLを使ってクエリを実行
        boolean ret = true;
        String jpql = "SELECT s.s_code FROM KomaBean s WHERE s.season= :season AND s.d_code = :d_code AND s.dayofweak = :dayofweak AND s.r_number = :r_number"; // クラス名を使用
        TypedQuery<Integer> query = entityManager.createQuery(jpql, Integer.class);
        query.setParameter("sesason", season);
        query.setParameter("d_code", d_code);
        query.setParameter("dayofweak", dayofweak);
        query.setParameter("r_number", r_number);
        
        Integer data = query.getSingleResult();
        if(data != null){
            ret = false;
        }
        return ret; 
    }

    @Override
    public boolean check_teacher(String season,Integer d_code,String dayofweak,Integer r_number) {
    // JPQLを使ってクエリを実行
        boolean ret = true;
        String jpql = "SELECT s.t_number FROM KomaBean s WHERE s.season= :season AND s.d_code = :d_code AND s.dayofweak = :dayofweak AND s.r_number = :r_number"; // クラス名を使用
        TypedQuery<Integer> query = entityManager.createQuery(jpql, Integer.class);
        query.setParameter("sesason", season);
        query.setParameter("d_code", d_code);
        query.setParameter("dayofweak", dayofweak);
        query.setParameter("r_number", r_number);
        
        Integer data = query.getSingleResult();
        if(data != null){
            ret = false;
        }
        return ret; 
    }

    @Override
    public OnlineBean set_online() {
    // JPQLを使ってクエリを実行
        OnlineBean onlineBeans = new OnlineBean();
        boolean ret = true;
        String jpql = "SELECT s.s_code FROM KomaBean s WHERE s.season= :season AND s.d_code = :d_code AND s.dayofweak = :dayofweak AND s.r_number = :r_number"; // クラス名を使用
        TypedQuery<Integer> query = entityManager.createQuery(jpql, Integer.class);
        
        return onlineBeans; 
    }
}
