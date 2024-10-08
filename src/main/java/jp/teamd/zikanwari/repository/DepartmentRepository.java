package jp.teamd.zikanwari.repository;

import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import jp.teamd.zikanwari.bean.DepartmentBean;

public interface DepartmentRepository extends JpaRepository<DepartmentBean,String>{

    @Query("SELECT X FROM DepartmentBean X")
    List<DepartmentBean> findAllDepartmentBeans();
    
}
