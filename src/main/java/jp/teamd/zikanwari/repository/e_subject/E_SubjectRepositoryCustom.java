package jp.teamd.zikanwari.repository.e_subject;

import java.util.List;
import jp.teamd.zikanwari.bean.E_SubjectBean;

public interface E_SubjectRepositoryCustom {
    public List<E_SubjectBean> set(String es_code,Integer sub_code,Integer weak_frame);
    public String create_code();
    public Integer get_number();
    public List<Integer> get_subcode();
}
