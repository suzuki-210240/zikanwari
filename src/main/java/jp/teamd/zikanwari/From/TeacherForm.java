package jp.teamd.zikanwari.From;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TeacherForm {
    private Integer t_number;
    private String t_name;
    private Integer p_code;
    private String d_code;
}
