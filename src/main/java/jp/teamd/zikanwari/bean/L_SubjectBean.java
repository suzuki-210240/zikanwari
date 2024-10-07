package jp.teamd.zikanwari.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

@Entity
@Table( name = "l_subject")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class L_SubjectBean {
    @Id
    private String ls_code;
    private Integer sub_code;
    private Integer weak_frame;

    
    public void set_es_code(String ls_code){
        this.ls_code = ls_code;
    }
    
    public void set_all(Integer sub_code,Integer weak_frame){
        this.sub_code = sub_code;
        this.weak_frame = weak_frame;
    }
}
