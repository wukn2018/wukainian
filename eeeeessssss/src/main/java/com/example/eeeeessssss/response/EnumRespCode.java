package com.example.eeeeessssss.response;

public enum EnumRespCode {

	SUCCESS("0", "操作成功"),
	FAIL("1", "操作失败"),
	PARAMS_ERROR("2", "参数错误"),
	INFO_ERROR("3", "信息错误"),
	BUSY("4", "系统繁忙"),
	LOGIN_TOKEN_EXPIRE("-5", "登陆授权码过期"),
	LOGIN_TOKEN_OVERTIME("-1000","登录授权码失效"),
	FILE_ERROR("6", "文件错误"),
	USER_PWD_INVALID("10100", "用户名或密码错误"),
	USER_TOKEN_INVALID("10101", "用户token无效"),
	USER_INVALID("10101", "非法用户"),

	LOGIN_TOKEN_ERROR("30001", "登陆授权码错误"),
	LOGIN_TOKEN_NEED("30003", "请登录"),
	LOGIN_TOKEN_IMEI_LIMIT("30004", "账号在另外一台设备登录"),

	SHOP_NOT_EXIT("40001","该商户下不存在门店"),
	PLATFORMID_NOT_EXIT("40002","平台id为空"),
	PLATFORMID_NOT_CONSISTENT("40003","商家平台id和聚童id不一致"),

	EXPORT_PARAM_ERROR("10500","导出参数错误"),

	ERROR_DELETE_CATEGORY("GC000201","该一级分类下有子分类，不允许删除"),
	ERROR_DELETE_GOODSCATEGORY("GC000202","该商品分类下有商品，不允许删除，不允许删除"),
	ERROR_EDIT_CATEGORYNAME("GC000203","分类名大于12位，不允许添加"),
	ERROR_EDIT_CATEGORYNAME_EXIST("GC000204","分类名已存在，不允许添加"),
	ERROR_EDIT_CATEGORY_NULL("GC000205","分类名为空，不允许添加"),
	ERROR_EDIT_CATEGORY_OVER("GC000206","子分类超过5个，不允许添加"),
	NO_OFFSALE_GOODSCATEGORY("GC000207","该商品正在参团中，不允许操作"),
	TEMPLATE_IS_USED("10501","模板已绑定商品，不能删除"),
	TEMPLATE_DELETE_ERROR("10502","运费删除失败"),
	ADDRESS_FREIGHT_RULE_EXIST("41001","运费规则不存在"),
	DICTIONARY_TYPE_ERROR("10600","枚举类型错误"),

	FILE_IS_NULL("10700", "文件上传参数为空"),
	PICMAXSIZE_IS_OVER("10701", "文件大小超过限制"),
	PICCONTENTTYPE_NOT_RIGHT("10702", "文件类型不正确"),
	ADDRESS_INFO_ERROR("10703", "地址信息有误"),
	SUBJECT_DELETE_ERROR("10704", "专题删除失败"),
	START_END_TIME_ERROR("10705", "开始时间大于结束时间"),
	NO_GROUP_ERROR("10706", "团活动不存在"),
	GROUP_END_ERROR("10707", "团活动已经结束"),

	SERVICE_ORDER_IS_NOT_AVAILABLE("SS0001","服务不可用"),
	SERVICE_GOODS_IS_NOT_AVAILABLE("SS0010","服务不可用"),
	SERVICE_AXE_IS_NOT_AVAILABLE("SS0020","服务不可用"),
	SERVICE_ROUTE_IS_NOT_AVAILABLE("SS0002","服务不可用"),
    SERVICE_GROUP_IS_NOT_AVAILABLE("SS0003","拼团服务不可用"),

	;





    private String code;
    private String msg;

	EnumRespCode(String code, String msg) {
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
