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
}
