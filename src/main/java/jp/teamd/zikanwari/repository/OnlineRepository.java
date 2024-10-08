package jp.teamd.zikanwari.repository;

import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import jp.teamd.zikanwari.OnlineId;
import jp.teamd.zikanwari.bean.OnlineBean;

public interface OnlineRepository extends JpaRepository<OnlineBean,OnlineId>{

    @Query("SELECT X FROM OnlineBean X")
    List<OnlineBean> findAllOnlineBeans();
    
}
