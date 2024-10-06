package jp.teamd.zikanwari.repository;

import java.util.List;



import jp.teamd.zikanwari.bean.SubjectBean;



public interface SubjectRepositoryCustom {
    public List<SubjectBean> search(String c_code);
}
