package com.polarion.starter.utility;

/**
 * Created by scottrichards on 9/5/15.
 */
public class URLService extends Object {
    private static String baseURL = "http://54.183.27.217";

    // return url formed by adding the specified ending url to the base url
    static public String buildUrl(String url) {
        String fullUrl;
        if (url.charAt(0) != '/') {
            fullUrl = baseURL + '/' + url;
        } else {
            fullUrl = baseURL + url;
        }
        return fullUrl;
    }
}
