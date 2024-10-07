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
    private String es_code;
    private Integer sub_code;
    private Integer weak_frame;
}
