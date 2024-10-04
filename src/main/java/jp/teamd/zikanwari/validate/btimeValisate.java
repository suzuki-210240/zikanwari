package jp.teamd.zikanwari.validate;
import jakarta.validation.*;
import jp.teamd.zikanwari.validate.btimeValid.time;

public class btimeValisate implements ConstraintValidator<time,String>{
    String ok;
    @Override
    public void initialize(time wr){
        ok = wr.ok();
    }
    @Override
    public boolean isValid(String in,ConstraintValidatorContext cxt){
        if(in == null){
            return false;
        }
        System.out.println(in.equals(ok));
        return in.equals(ok);
    }
}
