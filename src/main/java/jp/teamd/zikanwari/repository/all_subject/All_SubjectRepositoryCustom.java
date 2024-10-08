package jp.teamd.zikanwari.repository.all_subject;

import java.util.List;



import jp.teamd.zikanwari.bean.All_SubjectBean;



public interface All_SubjectRepositoryCustom {
    public List<All_SubjectBean> set(String alls_code,Integer sub_code,Integer weak_frame);
}
