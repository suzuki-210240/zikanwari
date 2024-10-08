package jp.teamd.zikanwari.repository;

import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import jp.teamd.zikanwari.KomaId;
import jp.teamd.zikanwari.bean.KomaBean;

public interface KomaRepository extends JpaRepository<KomaBean,KomaId>{

    @Query("SELECT X FROM KomaBean X ")
    List<KomaBean> findAllKomaBeans();
    
}