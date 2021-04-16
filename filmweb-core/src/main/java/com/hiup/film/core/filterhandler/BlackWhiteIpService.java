package com.hiup.film.core.filterhandler;

/**
 * Created by zw on 2017/12/26.
 */
public interface BlackWhiteIpService {

    boolean isWhiteIp(String ip);

    boolean isBlackIp(String ip);


}
