package jp.teamd.zikanwari.repository;

import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Component;

import jp.teamd.zikanwari.bean.E_SubjectBean;

public interface E_SubjectRepositoryCustom implements E_SubjectRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<E_SubjectBean> search(String c_code) {
        // JPQLを使ってクエリを実行
        String jpql = "SELECT s FROM SubjectBean s WHERE s.c_code = :c_code"; // クラス名を使用
        TypedQuery<SubjectBean> query = entityManager.createQuery(jpql, SubjectBean.class);
        query.setParameter("c_code", c_code);
        return query.getResultList(); // 結果を返す
