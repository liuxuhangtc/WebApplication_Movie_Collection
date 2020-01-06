package me.xuhang.movie.validator.constraints;

/**
 * Created by xuhang on 2019/11/20.
 */

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.lang.annotation.*;

/**
 * Validation constraints for loginName
 *
 * @author xuhang
 */
@Pattern(regexp = "^(?!(([1][3|5|8][0-9]{9})|([\\\\w-]+(\\\\.[\\\\w-]+)*@[\\\\w-]+(\\\\.[\\\\w-]+)+)))([0-9a-zA-Z_\u4E00-\u9FBF]+)", message = "User name can not use phone number or email")
@NotNull(message = "User name can not be empty")
@Size(min = 2, max = 30, message = "Length between 2-30")
@Constraint(validatedBy = {})
@Target({ElementType.METHOD,
        ElementType.FIELD,
        ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface UserName {

    String message() default "{common.loginName}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
