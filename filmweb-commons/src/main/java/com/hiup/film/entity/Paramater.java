package com.hiup.film.entity;

import com.baomidou.mybatisplus.toolkit.IdWorker;

import java.io.Serializable;

public class Paramater implements Serializable{
    private String serviceName;

    private ServiceType serviceType = ServiceType.BYNAME;

    private String method;

    private Object[] args;

    private Object result;

    private final String id = String.valueOf(IdWorker.getId());

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
