package com.example.eeeeessssss.menus;

/**
 * com.xinguangnet.tuchao.merchant.manage
 *
 * @Author : Wukn
 * @Date : 2018/2/5
 */
public enum EnumResp {

    SUCCESS("0", "操作成功"),
    FAIL("1", "操作失败"),
    SERVICE_ROUTE_IS_NOT_AVAILABLE("SS0002","服务不可用")
    ;


    private String code;
    private String msg;

    EnumResp(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
