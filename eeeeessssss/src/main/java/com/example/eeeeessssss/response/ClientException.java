package com.example.eeeeessssss.response;
import org.apache.commons.*;

/**
 * @author zc
 */
public class ClientException extends Exception {
    private String code;
    private String errorMsg;

    public ClientException(EnumRespCode resp) {
        this.code = resp.getCode();
        this.errorMsg = resp.getMsg();
    }

    public ClientException(EnumRespCode resp, String placeHolder) {
        this.code = resp.getCode();
        this.errorMsg = ErrorMsgHelper.findByCode(code);
        if (null != errorMsg){
            this.errorMsg = resp.getMsg();
        }
        this.errorMsg = String.format(this.errorMsg,placeHolder);
    }

    public ClientException(String code, String desc) {
        this.code = code;
        this.errorMsg = desc;

    }
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
