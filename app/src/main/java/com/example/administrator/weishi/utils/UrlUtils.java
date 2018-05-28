package com.example.administrator.weishi.utils;

import android.net.Uri;

/**
 * 请求地址拼接
 */

public class UrlUtils {
    public static String BASE_URL = "http://renwu.007jieshaohuo.com/Api/";

    /**
     * 登录
     */
    public static String login() {
        String url = null;
        Uri.Builder uribBuilder = Uri.parse(BASE_URL + "do_login").buildUpon();
        url = uribBuilder.build().toString();
        return url;
    }




}
