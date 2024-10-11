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
public class ClassBean {
    @Id
    private String c_code;
    private Integer c_year;
    private Integer c_class;
    @Column(nullable = false)
    private String d_code;
    private String onlineday;
    private Integer btime;
}