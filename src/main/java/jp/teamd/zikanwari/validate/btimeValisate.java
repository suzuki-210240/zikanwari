package jp.teamd.zikanwari.validate;
import jakarta.validation.*;
import jp.teamd.zikanwari.validate.btimeValid.time;

public class btimeValisate implements ConstraintValidator<time,Integer>{
    //Integer ok;
    @Override
    public void initialize(time tm){
        //ok = tm.ok();
    }
    @Override
    public boolean isValid(Integer in,ConstraintValidatorContext cxt){
        if(in == null){
            return false;
        }
        //System.out.println(in.equals(ok));
        return in == 31 || in == 32;
    }
}
