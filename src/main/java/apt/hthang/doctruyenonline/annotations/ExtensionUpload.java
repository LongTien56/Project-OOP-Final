package apt.hthang.doctruyenonline.annotations;

import apt.hthang.doctruyenonline.validator.ExtensionUploadValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;



@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ExtensionUploadValidator.class)
@Documented
public @interface ExtensionUpload {
    
    String message() default "{hthang.truyenmvc.story.category.empty.message}";
    
    Class< ? >[] groups() default {};
    
    Class< ? extends Payload >[] payload() default {};
    
}
