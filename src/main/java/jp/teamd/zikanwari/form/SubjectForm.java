package jp.teamd.zikanwari.form;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SubjectForm {
    private Integer s_code;
    private String s_name;
    private String c_code;
    private Integer t_number;
    private Integer s_classification;
    private Integer use_room_number;
}

