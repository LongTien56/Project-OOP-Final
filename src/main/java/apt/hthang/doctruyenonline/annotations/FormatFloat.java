package apt.hthang.doctruyenonline.annotations;

import apt.hthang.doctruyenonline.validator.FormatFloatValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FormatFloatValidator.class)
@Documented
public @interface FormatFloat {
    
    String message() default "{hthang.truyenmvc.chapter.serial.numberFormat.message}";
    
    Class< ? >[] groups() default {};
    
    Class< ? extends Payload >[] payload() default {};
    
    String baseField();
    
    String idField();
    
    String matchField();
}
