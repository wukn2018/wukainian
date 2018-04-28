package com.example.eeeeessssss.response;


import java.util.HashMap;
import java.util.Map;

/**
 * @author zc
 */
public class ErrorMsgHelper {
    private static Map<String, Object> errorMap;

//    public static void loadAll(){
//        errorMap = DisconfDataGetter.getByFile("errormsg.properties");
//    }

    public static String findByCode(String code) {
        if (null == errorMap){
//            loadAll();
            return null;
        }
        return (String) errorMap.get(code);
    }


    public static void updateErrorMap(Map<String, Object> sourceErrorMap){
        if (sourceErrorMap == null) {
            return;
        }
        if (null != errorMap){
            errorMap.clear();
        }
        if (errorMap == null){
            errorMap = new HashMap<>(sourceErrorMap.size());
        }
        errorMap.putAll(sourceErrorMap);
    }
}