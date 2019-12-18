package cn.zuel.wlyw.kit;


public enum ResultCodeEnum {

    RECORD_NO_EXIST("1006", "记录不存在"),

    DB_UPDATE_SUCCESS("2002", "数据库修改成功"),
    DB_UPDATE_ERROR("2003", "数据库修改失败"),

    PARA_NUM_ERROR("3001", "请求的参数个数错误"),

    LOGIN_SUCCESS("4000", "登录成功"),
    LOGIN_ERROR("4001", "登录失败_账号或密码错误"),
    NO_EXIST_USER("4002", "登录失败_用户不存在"),

    LOGOUT_SUCCESS("4004", "退出登录成功"),

    LOGOUT_FAILURE("4009", "退出登录失败"),
    // 注册
    MODIFY_FAILURE_PWD_ERROR("5000", "修改失败，密码错误"),
    REGISTER_SUCCESS("5001", "注册成功"),
    REGISTER_FAILURE_DB_ERROR("5002", "注册失败_数据库操作错误"),
    REGISTER_FAILURE_USER_EXIST("5003", "注册失败_账户已存在"),
    REGISTER_FAILURE_SYS_ERROR("5004", "注册失败_系统错误"),


    MODIFY_SUCCESS("5005", "修改成功"),

    ALBUM_ADD_SUCCESS("5006", "新建相册成功"),
    ALBUM_ADD_FAILURE_DB_ERROR("5007", "新建相册失败_数据库操作错误"),
    ALBUM_ADD_FAILURE_ALBUM_EXIST("5008", "新建相册失败_账户已存在"),
    ALBUM_ADD_FAILURE_SYS_ERROR("5009", "新建相册失败_系统错误"),

    IMAGE_DELETE_SUCCESS("6004", "照片删除成功"),
    IMAGE_NOT_EXIST("6005", "照片不存在"),
    IMAGE_DELETE_FAILURE_DB_ERROR("6006", "照片删除失败_数据库错误"),

    IMAGE_UPLOAD_FAILURE_DB_ERROR("6015", "图片上传失败_数据库错误"),
    IMAGE_UPLOAD_SUCCESS("6016", "图片上传成功"),
    IMAGE_QUERY_SUCCESS("6021", "图片查询成功"),
    IMAGE_QUERY_NULL("6022", "图片查询为空"),

    ALBUM_QUERY_SUCCESS("6023", "相册查询成功"),
    ALBUM_QUERY_NULL("6024", "相册查询为空"),
    ALBUM_DELETE_SUCCESS("6025", "相册删除成功"),
    ALBUM_DELETE_FAILURE("6026", "相册删除失败"),
    ALBUM_NOT_EXIST("6027", "相册不存在"),


    USER_QUERY_SUCCESS("7000", "用户查询成功"),
    USER_QUERY_NULL("7001", "用户不存在"),
    USER_MODIFY_INFO_SUCCESS("7002", "用户修改信息成功"),
    USER_MODIFY_INFO_FAILURE("7003", "用户修改信息失败");


    private String code;
    private String desc;

    ResultCodeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }


}
