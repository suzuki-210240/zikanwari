package jp.teamd.zikanwari.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

@Entity
@Table( name = "department")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentBean {
    @Id
    private String d_code;
    @Column(nullable = false)
    private String d_name;
}
