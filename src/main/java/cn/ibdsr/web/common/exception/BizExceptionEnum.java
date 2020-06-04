package cn.ibdsr.web.common.exception;

/**
 * @author fengshuonan
 * @Description 所有业务异常的枚举
 * @date 2016年11月12日 下午5:04:51
 */
public enum BizExceptionEnum {

	/**
	 * 字典
	 */
	DICT_EXISTED(400,"字典已经存在"),
	ERROR_CREATE_DICT(500,"创建字典失败"),
	ERROR_WRAPPER_FIELD(500,"包装字典属性失败"),


	/**
	 * 文件上传
	 */
	FILE_READING_ERROR(400,"FILE_READING_ERROR!"),
	FILE_NOT_FOUND(400,"FILE_NOT_FOUND!"),
	UPLOAD_ERROR(500,"上传图片出错"),

	/**
	 * 软件
	 */
	SOFTWARE_ID_IS_NULL(400, "软件ID不能为空"),
	SOFTWARE_IS_NOT_EXIST(400, "软件不存在"),
	CURRENT_VERSION_NO_IS_NULL(450, "当前版本号不能为空"),
	NO_PACKAGE(400,"该软件暂无安装包"),


	/**
	 * 安装包
	 */
	FILE_IS_NULL(400, "文件不能为空"),
	FILE_TYPE_ERROR(400, "文件类型与软件类型不匹配"),
	FILE_TOO_BIG(400, "文件超出100MB"),
	ANALYSE_ERROR(400, "安装包解析失败"),
	PLEASE_UPLOAD_PACKAGE(400, "请上传安装包"),
	UPLOAD_VERSION_RECORD_NOT_EXIST(400, "上传版本记录不存在"),
	NEW_VERSION_SMALL_OLD_VERSION(400, "上传的新的安装包版本号必须比前一个高"),
	PACKAGE_FOR_DIFFERENT_APPLICATION(400, "不同应用的安装包不能上传"),


	/**
	 * 权限和数据问题
	 */
	DB_RESOURCE_NULL(400,"数据库中没有该资源"),
	NO_PERMITION(405, "权限异常"),
	REQUEST_INVALIDATE(400,"请求数据格式不正确"),
	INVALID_KAPTCHA(400,"验证码不正确"),
	CANT_DELETE_ADMIN(600,"不能删除超级管理员"),
	CANT_FREEZE_ADMIN(600,"不能冻结超级管理员"),
	CANT_CHANGE_ADMIN(600,"不能修改超级管理员角色"),

	/**
	 * 账户问题
	 */
	USER_ALREADY_REG(401,"该用户已经注册"),
	NO_THIS_USER(400,"没有此用户"),
	USER_NOT_EXISTED(400, "没有此用户"),
	ACCOUNT_FREEZED(401, "账号被冻结"),
	OLD_PWD_NOT_RIGHT(402, "原密码不正确"),
	TWO_PWD_NOT_MATCH(405, "两次输入密码不一致"),

	/**
	 * 错误的请求
	 */
	MENU_PCODE_COINCIDENCE(400,"菜单编号和副编号不能一致"),
	EXISTED_THE_MENU(400,"菜单编号重复，不能添加"),
	DICT_MUST_BE_NUMBER(400,"字典的值必须为数字"),
	REQUEST_NULL(400, "请求有错误"),
	SESSION_TIMEOUT(400, "会话超时"),
	SERVER_ERROR(500, "服务器异常"),

	/**
	 * 通用
	 */
	OBJECT_NOT_EXIST(1000, "对象不存在"),
	PREVIOUS_NOT_EXIST(1001, "前一个元素不存在"),
	NEXT_NOT_EXIST(1002, "后一个元素不存在"),
	ID_IS_NULL(1003,"id不能为空"),
	/**
	 *项目列表
	 */
	PROJECT_IS_NULL(1005,"项目不能为空"),
	;

	BizExceptionEnum(int code, String message) {
		this.friendlyCode = code;
		this.friendlyMsg = message;
	}
	
	BizExceptionEnum(int code, String message,String urlPath) {
		this.friendlyCode = code;
		this.friendlyMsg = message;
		this.urlPath = urlPath;
	}

	private int friendlyCode;

	private String friendlyMsg;
	
	private String urlPath;

	public int getCode() {
		return friendlyCode;
	}

	public void setCode(int code) {
		this.friendlyCode = code;
	}

	public String getMessage() {
		return friendlyMsg;
	}

	public void setMessage(String message) {
		this.friendlyMsg = message;
	}

	public String getUrlPath() {
		return urlPath;
	}

	public void setUrlPath(String urlPath) {
		this.urlPath = urlPath;
	}

}
