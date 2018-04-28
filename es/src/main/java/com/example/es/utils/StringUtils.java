package com.example.es.utils;

/**
 * @author Created by dev-tang <dev-tang@foxmail.com> on 17-11-1.
 */
public class StringUtils {
    private StringUtils(){}

    public static boolean isEmpty(String str) {
        return (str == null || str.trim().length() == 0);
    }
}
