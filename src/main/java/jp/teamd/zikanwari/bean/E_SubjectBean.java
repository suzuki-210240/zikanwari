package jp.teamd.zikanwari.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

@Entity
@Table( name = "e_subject")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class E_SubjectBean {
    @Id
    private String es_code;
    private Integer sub_code;
    private Integer weak_frame;
    private Integer s_number;
    private Integer setflg;

    
    public void set_es_code(String es_code){
        this.es_code = es_code;
    }

    public void set_all(Integer sub_code,Integer weak_frame){
        this.sub_code = sub_code;
        this.weak_frame = weak_frame;
    }
}
