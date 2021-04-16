package com.hiup.film.core.filterhandler;

import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

/**
 * Created by zw on 2017/12/26.
 */

public interface FilterHandlerService {

    public Map<String,HandlerInterceptor> handlerInterceptorMap();

}
