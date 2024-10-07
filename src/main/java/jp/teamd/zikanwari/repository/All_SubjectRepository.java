package jp.teamd.zikanwari.repository;

import java.util.List;



import jp.teamd.zikanwari.bean.All_SubjectBean;



public interface All_SubjectRepository {
    public List<All_SubjectBean> set(String c_code);
}
