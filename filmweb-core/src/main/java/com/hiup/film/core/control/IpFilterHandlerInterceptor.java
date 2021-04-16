package com.hiup.film.core.control;
/**
 * Created by zw on 2017/12/26.
 */

import com.hiup.film.core.filterhandler.BlackWhiteIpService;
import com.hiup.film.tools.NetworkUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.Set;

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
public class IpFilterHandlerInterceptor implements HandlerInterceptor,InitializingBean {

    private static Logger logger = LoggerFactory.getLogger(IpFilterHandlerInterceptor.class.getName());

    private Set<String> trustPaths = new HashSet<>();

    public IpFilterHandlerInterceptor(BlackWhiteIpService blackWhiteIpService) {
        this.blackWhiteIpService = blackWhiteIpService;
    }



    private BlackWhiteIpService blackWhiteIpService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        if (trustPaths.contains(httpServletRequest.getRequestURI())){
            logger.info("httpServletRequest.getRequestURI() is " + httpServletRequest.getRequestURI());
            return true;
        }
        String ip = NetworkUtil.getIpAddress(httpServletRequest);
        logger.info("ip is " + ip) ;
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        //modelAndView.getModelMap().

       // logger
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

    public BlackWhiteIpService getBlackWhiteIpService() {
        return blackWhiteIpService;
    }

    public void setBlackWhiteIpService(BlackWhiteIpService blackWhiteIpService) {
        this.blackWhiteIpService = blackWhiteIpService;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        trustPaths.add("login");
        logger.info("init login");
    }
}