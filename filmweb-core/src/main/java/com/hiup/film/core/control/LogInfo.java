package com.hiup.film.core.control;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target( { ElementType.METHOD, ElementType.FIELD })
public @interface LogInfo {
    public String logEnd() default "local";

    public String[] keys();

    public int aysn() default 0;

}
