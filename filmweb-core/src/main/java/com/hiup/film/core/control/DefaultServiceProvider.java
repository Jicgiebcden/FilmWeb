package com.hiup.film.core.control;

import com.hiup.film.core.logend.LogEndService;
import com.hiup.film.core.logend.LogInfo;
import com.hiup.film.core.logend.ParamaterInvokeInfo;
import com.hiup.film.core.service.BaseService;
import com.hiup.film.entity.HttpCode;
import com.hiup.film.entity.Paramater;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.reflect.FastClass;
import org.springframework.cglib.reflect.FastMethod;
import org.springframework.context.ApplicationContext;
import org.springframework.util.ClassUtils;

import java.lang.reflect.InvocationTargetException;

public class DefaultServiceProvider implements ServiceProvider {
    private static Logger logger = LoggerFactory.getLogger(DefaultServiceProvider.class.getName());

    private ApplicationContext applicationContext;

    private LogEndService logEndService;

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Paramater excute(Paramater paramater) {
        BaseService baseService;
        if (paramater.getServiceType().equals(HttpCode.ServiceType.BYNAME)){
            baseService = (BaseService) applicationContext.getBean(paramater.getServiceName());
        } else if (paramater.getServiceType().equals(HttpCode.ServiceType.BYTYPE)) {
            Class clasz = null;
            try {
                clasz = ClassUtils.forName(paramater.getServiceName(),getClass().getClassLoader());
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            baseService = (BaseService) applicationContext.getBean(clasz);
            Object[] parameters = paramater.getArgs();
            Class<?>[] parameterTypes = new Class[parameters.length];
            int index = 0;
            for (Object object:parameters){
                parameterTypes[index++]=object.getClass();
            }
            FastClass serviceFastClass = FastClass.create(baseService.getClass());
            FastMethod serviceFastMethod = serviceFastClass.getMethod(paramater.getMethod(), parameterTypes);

            LogInfo logInfo = serviceFastMethod.getJavaMethod().getAnnotation(LogInfo.class);
            if (logInfo == null){
                logInfo = (LogInfo) serviceFastClass.getJavaClass().getAnnotation(LogInfo.class);
            }

            Object result = null;
            ParamaterInvokeInfo paramaterInvokeInfo = new ParamaterInvokeInfo();
            long start = System.currentTimeMillis();
            try {
                result = serviceFastMethod.invoke(baseService, parameters);
                paramater.setResult(result);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            }finally {
                if (logInfo != null){
                    try {
                        long end = System.currentTimeMillis();
                        paramaterInvokeInfo.setParamater(paramater);
                        paramaterInvokeInfo.setStart(start);
                        paramaterInvokeInfo.setEnd(end);

                        logEndService.log(paramaterInvokeInfo);
                    }catch (Exception e){
                        //do nothing
                    }
                    logEndService.log(paramaterInvokeInfo);
                }
            }
        }
        return paramater;
    }
}
