package jp.teamd.zikanwari.repository.koma;

import java.util.List;
import jp.teamd.zikanwari.bean.KomaBean;
public interface KomaRepositoryCustom {
    public List<KomaBean> search(String c_code);
    public Integer get_btime(Integer s_code);
    public Integer get_room(Integer s_code);
    public boolean check_room(String season,Integer d_code,String dayofweak,Integer r_number);
    public boolean check_teacher(String season,Integer s_code,Integer d_code,String dayofweak,Integer t_number);
    public Integer get_setflg(String season,Integer s_code);
    public Integer get_tnumber(Integer s_code);
    public Integer cover_online(String season,Integer d_code,Integer s_code,Integer btime);
}
