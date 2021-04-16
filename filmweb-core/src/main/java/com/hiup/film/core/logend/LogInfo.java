package com.hiup.film.core.logend;

import org.springframework.stereotype.Service;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Service
@Retention(RetentionPolicy.RUNTIME)
@Target( { ElementType.METHOD, ElementType.TYPE })
public @interface LogInfo {
    public LogEndType logEnd() default LogEndType.LOGTYPE_FILE;

    public String[] keys();

    public int aysn() default 0;

}
