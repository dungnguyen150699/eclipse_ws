package dungnt_ptit.com.securitystandard.configuration.filter.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

@Retention(value = RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
@Inherited
public @interface RBAC {
    @AliasFor("name")
    String []value() default "";
    @AliasFor("value")
    String []name() default "";
}
