package jp.teamd.zikanwari.repository.koma;

import java.util.List;
import jp.teamd.zikanwari.bean.KomaBean;
import jp.teamd.zikanwari.bean.OnlineBean;

public interface KomaRepositoryCustom {
    public List<KomaBean> search(String c_code);
    public Integer get_btime(Integer s_code);
    public Integer get_room(Integer s_code);
    public boolean check_room(String season,Integer d_code,String dayofweak,Integer r_number);
    public boolean check_teacher(String season,Integer d_code,String dayofweak,Integer r_number);
    public OnlineBean set_online();
    public Integer get_setflg(String season,Integer s_code);
}
