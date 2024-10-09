package jp.teamd.zikanwari.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import jp.teamd.zikanwari.OnlineId;

@Entity
@Table( name = "online")
@IdClass(OnlineId.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OnlineBean {
    @Id
    private String season; //e:前期,l:後期
    @Id
    private Integer d_code;//何コマ目かの枠番号
    @Id
    private Integer s_code;//科目コード
}
