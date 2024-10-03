package jp.teamd.zikanwari.From;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class KomaForm {
    private String season; //e:前期,l:後期
    private Integer d_code;//何コマ目かの枠番号
    private Integer s_code;//科目コード
    private String dayofweak;//月火水木金
    private Integer r_number;//教室番号
    private Integer onlineflg;//0：登校日,1：オンライン
}

