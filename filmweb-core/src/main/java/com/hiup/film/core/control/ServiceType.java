package com.hiup.film.core.control;

import net.sf.json.JSONObject;

public enum  ServiceType {
    BYNAME,
    BYTYPE;



    public static void main(String[] args) {
        JSONObject jsonObject = new JSONObject();
        Paramater paramater = new Paramater();
        jsonObject = JSONObject.fromObject(paramater);
        String string = jsonObject.toString();
        JSONObject jsonObject1 = JSONObject.fromObject(string);

        Paramater paramater1 = (Paramater) JSONObject.toBean(jsonObject1,Paramater.class);
        System.out.println(paramater1.getServiceType().equals(ServiceType.BYTYPE));
    }
}
