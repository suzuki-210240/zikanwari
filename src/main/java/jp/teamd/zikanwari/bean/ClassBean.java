package jp.teamd.zikanwari.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

@Entity
@Table( name = "class")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherBean {
    @Id
    private Integer c_code;
    private String c_year;
    private String c_class;
    @Column(nullable = false)
    private Integer d_code;
    private String onlineday;
    private Integer btime;
}
