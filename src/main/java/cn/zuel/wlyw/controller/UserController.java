package cn.zuel.wlyw.controller;

import cn.zuel.wlyw.kit.BaseResponse;
import cn.zuel.wlyw.kit.ResultCodeEnum;
import cn.zuel.wlyw.service.UserService;
import com.jfinal.core.Controller;
import com.jfinal.kit.StrKit;

public class UserController extends Controller {
    UserService userService = new UserService();

    /**
     * 注册账号
     */
    public void register() {
        BaseResponse baseResponse = new BaseResponse();
        String u_phone = getPara("u_phone");
        String u_pwd = getPara("u_pwd");

        if (!StrKit.isBlank(u_phone) && !StrKit.isBlank(u_pwd)) {
            // 注册信息不为空
            baseResponse = userService.register(u_phone, u_pwd);
        } else {
            // 提交的表单信息不完整
            baseResponse.setResult(ResultCodeEnum.PARA_NUM_ERROR);
        }
        renderJson(baseResponse);
    }

    /**
     * 用户登录
     */
    public void login() {
        BaseResponse baseResponse = new BaseResponse();

        String u_phone = getPara("u_phone");
        String u_pwd = getPara("u_pwd");
        if (!StrKit.isBlank(u_phone) && !StrKit.isBlank(u_pwd)) {
            baseResponse = userService.login(u_phone, u_pwd);
        } else {
            // 请求的参数不足
            baseResponse.setResult(ResultCodeEnum.PARA_NUM_ERROR);
        }
        if (baseResponse.getResultCode().equals("4000"))
            setSessionAttr(u_phone, "ready");
        else
            setSessionAttr(u_phone, "unready");
        renderJson(baseResponse);
    }

    /**
     * 用户退出登录
     */
    public void logout() {
        BaseResponse baseResponse = new BaseResponse();
        String u_phone = getPara("u_phone");
        if (getSessionAttr(u_phone) != null && getSessionAttr(u_phone).equals("ready")) {
            removeSessionAttr(u_phone);
            baseResponse.setResult(ResultCodeEnum.LOGOUT_SUCCESS);
        } else {
            baseResponse.setResult(ResultCodeEnum.LOGOUT_FAILURE);
        }
        renderJson(baseResponse);
    }

    /**
     * 修改用户密码
     */
    public void modifyPwd() {
        BaseResponse baseResponse = new BaseResponse();
        String u_phone = getPara("u_phone");
        String u_old_pwd = getPara("u_old_pwd");
        String u_new_pwd = getPara("u_new_pwd");
        if (!StrKit.isBlank(u_phone) && !StrKit.isBlank(u_old_pwd) && !StrKit.isBlank(u_new_pwd)) {
            // 请求的参数不为空
            baseResponse = userService.modifyPwd(u_phone, u_old_pwd, u_new_pwd);
        } else {
            // 请求的参数个数不足
            baseResponse.setResult(ResultCodeEnum.PARA_NUM_ERROR);
        }
        renderJson(baseResponse);
    }

    /**
     * 修改用户的信息
     */
    public void modifyInfo() {
        BaseResponse baseResponse = new BaseResponse();
        String u_nickname = getPara("u_nickname");
        String u_phone = getPara("u_phone");
        String u_gender = getPara("u_gender");
        String u_qq = getPara("u_qq");

        if (!StrKit.isBlank(u_nickname) && !StrKit.isBlank(u_phone) && !StrKit.isBlank(u_gender) && !StrKit.isBlank(u_qq)) {
            baseResponse = userService.modifyInfo(u_nickname, u_phone, u_gender, u_qq);
        } else {
            // 请求参数有空
            baseResponse.setResult(ResultCodeEnum.PARA_NUM_ERROR);
        }
        renderJson(baseResponse);
    }

    /**
     * 获取用户的信息
     */
    public void getInfo() {
        BaseResponse baseResponse = new BaseResponse();
        String u_id = getPara("u_id");
        if (!StrKit.isBlank(u_id)) {
            baseResponse = userService.getInfo(u_id);
        } else {
            // 请求学号、名字、qq不能为空
            baseResponse.setResult(ResultCodeEnum.PARA_NUM_ERROR);
        }
        renderJson(baseResponse);
    }
}
