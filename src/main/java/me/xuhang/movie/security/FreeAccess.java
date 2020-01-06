package me.xuhang.movie.security;

import java.lang.annotation.*;

/**
 * assigned login status
 * Created by xuhang on 2019/11/20.
 */
@Documented
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = {ElementType.METHOD})
public @interface FreeAccess {
}
