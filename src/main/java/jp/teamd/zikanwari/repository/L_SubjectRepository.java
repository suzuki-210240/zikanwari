package jp.teamd.zikanwari.repository;

import java.util.List;



import jp.teamd.zikanwari.bean.L_SubjectBean;



public interface L_SubjectRepository {
    public List<L_SubjectBean> search(String c_code);
}
