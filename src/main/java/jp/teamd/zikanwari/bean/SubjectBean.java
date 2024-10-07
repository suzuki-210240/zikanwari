package jp.teamd.zikanwari.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

@Entity
@Table( name = "subject")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectBean {
    @Id
    private Integer s_code;
    private String s_name;
    private String c_code;
    private Integer t_number;
    private Integer s_classification;
    private Integer use_room_number;

    public String getC_code() {
        return c_code;
    }

    public void setC_code(String c_code) {
        this.c_code = c_code;
    }
    
    public void set_all(Integer s_code,String s_name,String c_code,Integer t_number,Integer s_classification,Integer use_room_number){
        this.s_code = s_code;
        this.s_name = s_name;
        this.c_code = c_code;
        this.t_number = t_number;
        this.s_classification = s_classification;
        this.use_room_number = use_room_number;
    }

}
