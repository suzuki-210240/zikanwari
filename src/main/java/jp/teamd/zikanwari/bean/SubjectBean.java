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
}
