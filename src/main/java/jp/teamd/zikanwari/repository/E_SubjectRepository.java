package jp.teamd.zikanwari.repository;

import java.util.List;



import jp.teamd.zikanwari.bean.E_SubjectBean;



public interface E_SubjectRepository {
    public List<E_SubjectBean> search(String c_code);
}
