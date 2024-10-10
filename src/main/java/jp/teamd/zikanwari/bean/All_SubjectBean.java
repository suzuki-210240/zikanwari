package jp.teamd.zikanwari.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

@Entity
@Table( name = "all_subject")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class All_SubjectBean {
    @Id
    private String alls_code;
    private Integer sub_code;
    private Integer weak_frame;
    private Integer s_number;
    private Integer setflg;
    
    public void set_es_code(String alls_code){
        this.alls_code = alls_code;
    }
}
