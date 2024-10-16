package jp.teamd.zikanwari.repository.koma;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import jakarta.persistence.Query;
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
    public String get_season(Integer s_code) {
    // JPQLを使ってクエリを実行
        
        String jpql = "SELECT s.s_classification FROM SubjectBean s WHERE s.s_code = :s_code";
        TypedQuery<Integer> query = entityManager.createQuery(jpql, Integer.class);
        query.setParameter("s_code", s_code);
        
        Integer sflg = query.getSingleResult();
        String ret = "";
        if(sflg == 1){
            ret = "e";
        }else if(sflg == 2){
            ret = "l";
        }else{
            ret = "all";
        }
        
        return ret; 
    }

    @Override
    public Integer get_btime(Integer s_code) {
    // JPQLを使ってクエリを実行
        
        String jpql = "SELECT s.c_code FROM SubjectBean s WHERE s.s_code = :s_code"; // クラス名を使用
        TypedQuery<String> query = entityManager.createQuery(jpql, String.class);
        query.setParameter("s_code", s_code);
        
        String c_code = query.getSingleResult(); // 結果のリストを取得

        jpql = "SELECT s.btime FROM ClassBean s WHERE s.c_code = :c_code";
        TypedQuery<Integer>query2 = entityManager.createQuery(jpql, Integer.class);
        query2.setParameter("c_code", c_code);

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
    public boolean check_room(String season, Integer d_code, String dayofweak, Integer r_number) {
        // JPQLを使ってクエリを実行
        boolean ret = true;
        String jpql = "SELECT s.s_code FROM KomaBean s WHERE s.season = :season AND s.d_code = :d_code AND s.dayofweak = :dayofweak AND s.r_number = :r_number";
        
        TypedQuery<Integer> query = entityManager.createQuery(jpql, Integer.class);
        query.setParameter("season", season);
        query.setParameter("d_code", d_code);
        query.setParameter("dayofweak", dayofweak);
        query.setParameter("r_number", r_number);
        
        List<Integer> data = query.getResultList(); // getSingleResultの代わりにgetResultListを使用
        
        if (!data.isEmpty()) {
            ret = false; // 結果が存在すればfalse
        }
        
        return ret; 
    }


    @Override
    public boolean check_teacher(String season, Integer s_code, Integer d_code, String dayofweak, Integer t_number) {
        // JPQLを使ってクエリを実行
        boolean ret = true;
        Integer sflg = null;

        // seasonを比較
        if ("e".equals(season)) {
            sflg = 1;
        } else if ("l".equals(season)) {
            sflg = 2;
        }

        // 最初のクエリ
        String jpql = "SELECT s.s_code FROM SubjectBean s WHERE s.t_number = :t_number AND s.s_code <> :s_code AND s.s_classification IN (0, :sflg)";
        TypedQuery<Integer> query = entityManager.createQuery(jpql, Integer.class);
        query.setParameter("t_number", t_number);
        query.setParameter("s_code", s_code);
        query.setParameter("sflg", sflg);

        List<Integer> data = query.getResultList();
        if (!data.isEmpty()) {
            ret = false; // 競合があればfalseを返す
        } else {
            // 競合がなければ次の処理
            for (Integer num : data) {
                jpql = "SELECT s.s_code FROM KomaBean s WHERE s.season = :season AND s.d_code = :d_code AND s.s_code = :s_code AND dayofweak = :dayofweak";
                query = entityManager.createQuery(jpql, Integer.class);
                query.setParameter("season", season); // 修正
                query.setParameter("d_code", d_code);
                query.setParameter("s_code", num);
                query.setParameter("dayofweak", dayofweak);

                List<Integer> results = query.getResultList(); // getResultListを使用

                if (!results.isEmpty()) {
                    ret = false; // 結果があればfalse
                    break; // ループを終了
                }
            }
        }
        return ret;
    }


    @Override
    public Integer get_setflg(String season, Integer s_code) {
        String table;
        String jpql;

        // All_SubjectBeanからalls_codeを取得
        jpql = "SELECT s.alls_code FROM All_SubjectBean s WHERE s.sub_code = :s_code";
        TypedQuery<String> query = entityManager.createQuery(jpql, String.class);
        query.setParameter("s_code", s_code);
        
        List<String> all = query.getResultList();

        // テーブル名の決定
        if (season.equals("e")) {
            table = "E_SubjectBean";
        } else if (season.equals("l")) {
            table = "L_SubjectBean";
        } else {
            return 0; // 不正なseasonの場合はnullを返す
        }

        // setflgの取得
        if (all.isEmpty()) {
            jpql = "SELECT s.setflg FROM " + table + " s WHERE s.sub_code = :s_code";
        } else {
            jpql = "SELECT s.setflg FROM All_SubjectBean s WHERE s.sub_code = :s_code AND s.alls_code LIKE :alls_code";
        }

        TypedQuery<Integer> queryFinal = entityManager.createQuery(jpql, Integer.class);
        queryFinal.setParameter("s_code", s_code);
        
        if (!all.isEmpty()) {
            queryFinal.setParameter("alls_code", "%" + season);
        }

        try {
            return queryFinal.getSingleResult();
        } catch (NoResultException e) {
            return 0; // 結果が存在しない場合はnullを返す
        }
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

    @Transactional
    @Override
    public void update_setflg(String season, Integer s_code) {
        // JPQLを使ってクエリを実行
        String table = "";
        if (season.equals("e")) {
            table = "E_SubjectBean"; // equalsメソッドで文字列を比較
        } else if (season.equals("l")) {
            table = "L_SubjectBean";
        }
        
        // JPQLのUPDATEクエリを作成
        String jpql = "UPDATE " + table + " e SET e.setflg = e.setflg - 1 WHERE e.sub_code = :sub_code";
        
        // TypedQueryではなくQueryを使用
        Query query = entityManager.createQuery(jpql);
        query.setParameter("sub_code", s_code);
        
        // 更新クエリを実行
        int updatedCount = query.executeUpdate(); // 更新された行数を取得

        // 必要に応じて、更新された行数をログ出力したり、例外を投げたりすることも可能
        System.out.println("Updated rows: " + updatedCount);
    }

}
