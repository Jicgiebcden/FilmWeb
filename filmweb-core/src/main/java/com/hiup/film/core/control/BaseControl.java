package com.hiup.film.core.control;
/**
 * Created by zw on 2017/12/25.
 */

import com.hiup.film.core.exception.BaseException;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
public abstract class BaseControl {
    private static Logger logger = LoggerFactory.getLogger(BaseControl.class.getName());

    //public abstract String getService();

    @ExceptionHandler(Exception.class)
    public void exceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception ex) throws IOException {
        logger.error(ex.getMessage());
        //ModelMap modelMap = new ModelMap();
        JSONObject jsonObject = new JSONObject();
        if (ex instanceof BaseException){
            ((BaseException)ex).hander(jsonObject);
        }
        response.setCharacterEncoding("UTF-8");
        response.getOutputStream().write(jsonObject.toString().getBytes("UTF-8"));
    }
}