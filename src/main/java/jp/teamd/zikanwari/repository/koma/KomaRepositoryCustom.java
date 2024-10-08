package jp.teamd.zikanwari.repository.koma;

import java.util.List;
import jp.teamd.zikanwari.bean.KomaBean;

public interface KomaRepositoryCustom {
    public List<KomaBean> search(String c_code);
    public Integer get_btime(Integer s_code);
    public Integer get_room(Integer s_code);
}
