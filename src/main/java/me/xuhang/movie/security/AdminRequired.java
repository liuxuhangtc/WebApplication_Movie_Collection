package me.xuhang.movie.security;

/**
 * Created by xuhang on 2019/11/20.
 */

import java.lang.annotation.*;

/**
 * Need admin to view/trigger action or resources no matter what privilege
 *
 * Created by xuhang on 2019/11/20.
 */
@Documented
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = {ElementType.TYPE, ElementType.METHOD})
public @interface AdminRequired {
}
