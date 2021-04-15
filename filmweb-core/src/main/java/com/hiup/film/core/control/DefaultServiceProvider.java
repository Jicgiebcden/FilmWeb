package com.hiup.film.core.control;

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

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Paramater excute(Paramater paramater) {
        BaseService baseService;
        if (paramater.getServiceType().equals(ServiceType.BYNAME)){
            baseService = (BaseService) applicationContext.getBean(paramater.getServiceName());
        } else if (paramater.getServiceType().equals(ServiceType.BYTYPE)) {
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
            if (logInfo != null){

            }
            Object result = null;
            try {
                result = serviceFastMethod.invoke(baseService, parameters);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            }
            paramater.setResult(result);
        }

        return null;
    }
}
