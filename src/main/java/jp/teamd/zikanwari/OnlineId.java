package jp.teamd.zikanwari;

import java.io.Serializable;


public class OnlineId implements Serializable{
    private String season; //e:前期,l:後期
    private Integer d_code;//何コマ目かの枠番号
    private Integer s_code;//科目コード
    private String dayofweak;//月火水木金

    public OnlineId(){}

    public OnlineId(String season,Integer d_code,Integer s_code,String dayofweak){
        this.season = season;
        this.d_code = d_code;
        this.s_code = s_code;
        this.dayofweak = dayofweak;
    }

    
}
