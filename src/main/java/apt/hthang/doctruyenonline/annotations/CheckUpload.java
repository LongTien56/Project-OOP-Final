package apt.hthang.doctruyenonline.annotations;

import apt.hthang.doctruyenonline.validator.CheckUploadValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;



@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CheckUploadValidator.class)
@Documented
public @interface CheckUpload {
    
    String message() default "{hthang.truyenonline.story.category.empty.message}";
    
    Class< ? >[] groups() default {};
    
    Class< ? extends Payload >[] payload() default {};
    
}
