package jp.teamd.zikanwari.repository.koma;

import java.util.ArrayList;
import java.util.Arrays;
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
    public boolean check_teacher(String season,Integer s_code,Integer d_code,String dayofweak,Integer t_number) {
    // JPQLを使ってクエリを実行
        boolean ret = true;
        Integer sflg = 0;
        if(season == "e"){
            sflg = 1;
        }else if(season == "l"){
            sflg = 2;
        }
        String jpql =  "SELECT s.s_code FROM SubjectBean s WHERE s.t_number = :t_number AND s.s_code <> :s_code AND s.s_classification IN (0, :sflg)";
        TypedQuery<Integer> query = entityManager.createQuery(jpql, Integer.class);
        query.setParameter("t_number", t_number);
        query.setParameter("s_code", s_code);
        query.setParameter("sflg", sflg);
        
        List<Integer> data = query.getResultList();
        if(!data.isEmpty()){
            ret = false;
        }else{
            int count = 0;
            for(int i = 0;i<data.size();i++){
                Integer num = data.get(i);
                jpql = "SELECT s.s_code FROM KomaBean s WHERE s.season = :season AND s.d_code = :d_code AND s.s_code = :s_code AND dayofweak = :dayofweak";
                query = entityManager.createQuery(jpql, Integer.class);
                query.setParameter("sesason", season);
                query.setParameter("d_code", d_code);
                query.setParameter("s_code", num);
                query.setParameter("dayofweak", dayofweak);

                Integer result = query.getSingleResult();
                if(result != null){
                    count++;
                }   
            }
            if(count == 0){
                ret = true;
            }else{
                ret = false;
            }
        }
        return ret; 
    }

    @Override
    public Integer get_setflg(String season,Integer s_code){
        String table = "";
        if(season == "e"){
            table = "E_SubjectBean";
        }else if(season == "l"){
            table = "L_SubjectBean";
        }
        String jpql = "SELECT s.setflg FROM :table s WHERE s.s_code = :s_code";
        TypedQuery<Integer> query = entityManager.createQuery(jpql, Integer.class);
        query.setParameter("table", table);
        query.setParameter("s_code", s_code);
        Integer ret = query.getSingleResult();
        return ret;
    }

    @Override
    public Integer get_tnumber(Integer s_code) {
    // JPQLを使ってクエリを実行
        
        String jpql = "SELECT s.t_number FROM SubjectBean s WHERE s.s_code = :s_code";
        TypedQuery<Integer> query = entityManager.createQuery(jpql, Integer.class);
        query.setParameter("s_code", s_code);
        
        Integer room_num = query.getSingleResult();
        return room_num; 
    }

    @Override
    public Integer cover_online(String season,Integer d_code,Integer s_code,Integer btime) {
    // JPQLを使ってクエリを実行
        Integer ret = 0;
        Integer small = (s_code/100)*100;
        Integer[] f_time = new Integer[]{1,2,31,32,4,5};
        List<Integer> list = new ArrayList<Integer>(Arrays.asList(f_time));
        list.remove(btime);
        for(int i=0;i<list.size();i++){
            d_code = list.get(i);
            String jpql = "SELECT o FROM OnlineBean o WHERE o.season = :season AND o.d_code = :d_code AND o.s_code BETWEEN :small AND :s_code"; // クラス名を使用
            TypedQuery<Integer> query = entityManager.createQuery(jpql, Integer.class);
            query.setParameter("sesason", season);
            query.setParameter("d_code", d_code);
            query.setParameter("s_code", s_code);
            query.setParameter("small", small);

            Integer sq_result = query.getSingleResult();
            if(sq_result == null){
                ret = d_code;
                break;
            }
            
        }
        
        return ret; 
    }
}
