package jp.teamd.zikanwari.repository.l_subject;

import java.util.List;
import jp.teamd.zikanwari.bean.L_SubjectBean;
public interface L_SubjectRepositoryCustom {
    public List<L_SubjectBean> set(String ls_code,Integer sub_code,Integer weak_frame);
    public String create_code();
    public Integer get_number();
    public Integer get_setflg(Integer s_code);
    public List<Integer> get_subcode();
}
