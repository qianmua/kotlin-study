package pres.hjc.kotlinspringboot.target;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * To change this template use File | Settings | File Templates.
 * @date 2020/4/14
 * @time 21:03
 */
@Target( ElementType.METHOD)
@Retention( RetentionPolicy.RUNTIME)
public @interface AuthMenuLeave {
    String value() default "0";
}
