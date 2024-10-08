package jp.teamd.zikanwari.form;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OnlineForm {
    private String season; //e:前期,l:後期
    private Integer d_code;//何コマ目かの枠番号
    private Integer s_code;//科目コード
    private String dayofweak;//月火水木金
}

