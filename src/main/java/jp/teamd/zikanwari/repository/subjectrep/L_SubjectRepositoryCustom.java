package jp.teamd.zikanwari.repository.subjectrep;

import java.util.List;



import jp.teamd.zikanwari.bean.L_SubjectBean;



public interface L_SubjectRepositoryCustom {
    public List<L_SubjectBean> set(String ls_code,Integer sub_code,Integer weak_frame);
}
