package jp.teamd.zikanwari;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
//import org.springframework.data.annotation.Id;

@Entity
@Table( name = "koma")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class KomaBean {
    @Id
    private String season; //e:前期,l:後期
    @Id
    private Integer d_code;//何コマ目かの枠番号
    @Id
    private Integer s_code;//科目コード
    @Id
    private String dayofweak;//月火水木金
    private Integer r_number;//教室番号
    private Integer onlineflg;//0：登校日,1：オンライン


}
