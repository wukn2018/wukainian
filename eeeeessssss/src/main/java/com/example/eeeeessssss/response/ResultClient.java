package com.example.eeeeessssss.response;

import com.example.eeeeessssss.contans.ResultCode;

import java.io.Serializable;

/**
 * com.xinguangnet.tuchao.merchant.manage
 *
 * @Author : Wukn
 * @Date : 2018/2/5
 */
public class ResultClient<T> implements Serializable{

    private String resultCode;
    private String resultDesc;
    private T resultData;

    public ResultClient() {
    }

    public ResultClient(String resultCode, String resultDesc) {
        this.resultCode = resultCode;
        this.resultDesc = resultDesc;
    }


    public ResultClient(String resultCode, String resultDesc, T resultData) {
        this.resultCode = resultCode;
        this.resultDesc = resultDesc;
        this.resultData = resultData;
    }

    public ResultClient(T resultData) {
        this.resultCode = ResultCode.SUCCESS.getCode();
        this.resultDesc = ResultCode.SUCCESS.getMsg();
        this.resultData = resultData;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultDesc() {
        return resultDesc;
    }

    public void setResultDesc(String resultDesc) {
        this.resultDesc = resultDesc;
    }

    public T getResultData() {
        return resultData;
    }

    public void setResultData(T resultData) {
        this.resultData = resultData;
    }

    @Override
    public String toString() {
        return "ResultClient{" +
                "resultCode='" + resultCode + '\'' +
                ", resultDesc='" + resultDesc + '\'' +
                ", resultData=" + resultData +
                '}';
    }
}
