package jp.teamd.zikanwari.repository.subject;

import java.util.List;



import jp.teamd.zikanwari.bean.SubjectBean;



public interface SubjectRepositoryCustom {
    public List<SubjectBean> search(String c_code);
    public Integer getcode(String c_code);
}
