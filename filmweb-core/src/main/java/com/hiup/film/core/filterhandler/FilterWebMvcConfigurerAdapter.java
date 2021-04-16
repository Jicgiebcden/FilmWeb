package com.hiup.film.core.filterhandler;
/**
 * Created by zw on 2017/12/26.
 */

import com.hiup.film.core.control.IpFilterHandlerInterceptor;
import com.hiup.film.core.filterhandler.DefaultBlackWhiteIpService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * code is far away from bug with the animal protecting
 * ┏┓　　　┏┓
 * ┏┛┻━━━┛┻┓
 * ┃　　　　　　　┃
 * ┃　　　━　　　┃
 * ┃　┳┛　┗┳　┃
 * ┃　　　　　　　┃
 * ┃　　　┻　　　┃
 * ┃　　　　　　　┃
 * ┗━┓　　　┏━┛
 * 　　┃　　　┃神兽保佑
 * 　　┃　　　┃代码无BUG！
 * 　　┃　　　┗━━━┓
 * 　　┃　　　　　　　┣┓
 * 　　┃　　　　　　　┏┛
 * 　　┗┓┓┏━┳┓┏┛
 * 　　　┃┫┫　┃┫┫
 * 　　　┗┻┛　┗┻┛
 *
 * <p>
 * ---------------------------------
 */
@Configuration
@ConditionalOnBean(name = "filterHandlerService")
public class FilterWebMvcConfigurerAdapter extends WebMvcConfigurerAdapter {


    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(new IpFilterHandlerInterceptor(new DefaultBlackWhiteIpService())).addPathPatterns("/**");
    }

    public void addInterceptors(InterceptorRegistry registry,IpFilterHandlerInterceptor ipFilterHandlerInterceptor){
        registry.addInterceptor(ipFilterHandlerInterceptor).addPathPatterns("/**");
        super.addInterceptors(registry);
    }
}