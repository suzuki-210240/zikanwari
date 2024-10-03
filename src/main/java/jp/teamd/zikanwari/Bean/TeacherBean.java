package jp.teamd.zikanwari.Bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

@Entity
@Table( name = "teacher")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherBean {
    @Id
    private Integer t_number;
    private String t_name;
    @Column(nullable = false)
    private Integer p_code;
    private String d_code;
}
