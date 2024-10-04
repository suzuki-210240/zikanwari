package jp.teamd.zikanwari.validate;
import java.lang.annotation.*;
import jakarta.validation.*;

public class btimeValid {
    @Documented
    @Constraint(validatedBy = btimeValisate.class)
    @Target({ElementType.METHOD,ElementType.FIELD})
    @Retention(RetentionPolicy.RUNTIME)
        
    public @interface time{
        
        String ok();
        String message() default "{ok} を入力してください.";
        //Classオブジェクトを得る（戻り値とする）　メソッドgroups()
        //デフォルト値は空のクラス
        Class<?>[] groups() default{};
        //Payloadクラスを継承したClassオブジェクトを得る
        //（戻り値とする）メソッドpayload(),デフォルト値は空のクラス
        Class<? extends Payload>[] payload() default{};
    }
}
