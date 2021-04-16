package com.hiup.film.entity;

import com.hiup.film.tools.Resources;
import net.sf.json.JSONObject;

/**
 * Created by zw on 2017/12/25.
 */
public enum HttpCode {
    /** 200请求成功 */
    OK(200),
    /** 207频繁操作 */
    MULTI_STATUS(207),
    /** 303登录失败 */
    LOGIN_FAIL(303),
    /** 400请求参数出错 */
    BAD_REQUEST(400),
    /** 401没有登录 */
    UNAUTHORIZED(401),
    /** 403没有权限 */
    FORBIDDEN(403),
    /** 404找不到页面 */
    NOT_FOUND(404),
    /** 408请求超时 */
    REQUEST_TIMEOUT(408),
    /** 409发生冲突 */
    CONFLICT(409),
    /** 410已被删除 */
    GONE(410),
    /** 423已被锁定 */
    LOCKED(423),
    /** 500服务器出错 */
    INTERNAL_SERVER_ERROR(500);

    private final Integer value;

    private HttpCode(Integer value) {
        this.value = value;
    }

    /**
     * Return the integer value of this status code.
     */
    public Integer value() {
        return this.value;
    }

    public String msg() {
        return Resources.getMessage("HTTPCODE_" + this.value);
    }

    public String toString() {
        return this.value.toString();
    }

    public static enum  ServiceType {
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
}