package jp.teamd.zikanwari.form;

import lombok.Data;
import lombok.NoArgsConstructor;
import jp.teamd.zikanwari.validate.btimeValid.*;

@Data
@NoArgsConstructor
public class ClassForm {
    private String c_code;
    private Integer c_year;
    private String c_class = "9";
    private String d_code;
    private String onlineday;
    @time
    private Integer btime;
}
