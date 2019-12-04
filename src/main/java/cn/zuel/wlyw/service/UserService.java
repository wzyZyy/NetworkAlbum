package cn.zuel.wlyw.service;

import cn.zuel.wlyw.kit.BaseResponse;
import cn.zuel.wlyw.kit.ResultCodeEnum;
import cn.zuel.wlyw.model.User;

import java.util.List;

public class UserService {
    /**
     * 用户注册
     *
     * @param u_phone
     * @param u_pwd
     * @return
     */
    public BaseResponse register(final String u_phone, final String u_pwd) {
        BaseResponse baseResponse = new BaseResponse();
        List<User> users = User.dao.find("select * from user where u_phone = " + "'" + u_phone + "'");
        if (users.size() == 0) {
            // 手机号不存在，可以注册
            User user = new User();
            user.setUPhone(u_phone);
            user.setUPwd(u_pwd);
            if (user.save()) {
                // 注册成功
                baseResponse.setResult(ResultCodeEnum.REGISTER_SUCCESS);
            } else {
                // 注册失败，数据库操作错误
                baseResponse.setResult(ResultCodeEnum.REGISTER_FAILURE_DB_ERROR);
            }
        } else if (users.size() == 1) {
            // 注册失败，用户已经存在
            baseResponse.setResult(ResultCodeEnum.REGISTER_FAILURE_USER_EXIST);
        } else {
            // 注册失败，未知的系统错误
            baseResponse.setResult(ResultCodeEnum.REGISTER_FAILURE_SYS_ERROR);
        }
        return baseResponse;
    }

    /**
     * 用户登录
     *
     * @param u_phone
     * @param u_pwd
     * @return
     */
    public BaseResponse login(String u_phone, String u_pwd) {
        BaseResponse baseResponse = new BaseResponse();
        User user = User.dao.findFirst("select * from user where u_phone = " + "'" + u_phone + "'");
        if (user == null) {
            // 用户不存在
            baseResponse.setResult(ResultCodeEnum.NO_EXIST_USER);
        } else {
            if (user.getUPwd().equals(u_pwd)) {
                // 登录成功
                baseResponse.setData(user);
                baseResponse.setResult(ResultCodeEnum.LOGIN_SUCCESS);
            } else {
                // 登录失败，密码错误
                baseResponse.setResult(ResultCodeEnum.LOGIN_ERROR);
            }
        }
        return baseResponse;
    }

    /**
     * 修改用户密码
     *
     * @param u_phone
     * @param u_old_pwd
     * @param u_new_pwd
     * @return
     */
    public BaseResponse modifyPwd(String u_phone, String u_old_pwd, String u_new_pwd) {
        BaseResponse baseResponse = new BaseResponse();
        User user = User.dao.findFirst("select * from user where u_phone =" + "'" + u_phone + "'");
        if (user != null) {
            // 查找记录不为空
            if (user.getUPwd().equals(u_old_pwd)) {
                user.setUPwd(u_new_pwd);
                if (user.update()) {
                    // 修改密码成功
                    baseResponse.setResult(ResultCodeEnum.MODIFY_SUCCESS);
                } else {
                    // 修改密码失败，数据库错误
                    baseResponse.setResult(ResultCodeEnum.DB_UPDATE_ERROR);
                }
            } else {
                // 原密码错误
                baseResponse.setResult(ResultCodeEnum.MODIFY_FAILURE_PWD_ERROR);
            }
        } else {
            // 该账户不存在
            baseResponse.setResult(ResultCodeEnum.RECORD_NO_EXIST);
        }
        return baseResponse;
    }

    /**
     * 修改用户的信息
     *
     * @param u_nickname
     * @param u_phone
     * @param u_gender
     * @param u_qq
     * @return
     */
    public BaseResponse modifyInfo(String u_nickname, String u_phone, String u_gender,  String u_qq) {
        BaseResponse baseResponse = new BaseResponse();
        User user = User.dao.findFirst("select * from user where u_phone =" + "'" + u_phone + "'");
        if (user != null) {
            // 查找记录不为空
            user.setUNickname(u_nickname);
            user.setUGender(u_gender);
            user.setUQq(u_qq);
            if (user.update()) {
                // 用户信息修改成功
                baseResponse.setResult(ResultCodeEnum.DB_UPDATE_SUCCESS);
            } else {
                // 用户信息修改失败，数据库错误
                baseResponse.setResult(ResultCodeEnum.DB_UPDATE_ERROR);
            }
        } else {
            // 该账户不存在
            baseResponse.setResult(ResultCodeEnum.RECORD_NO_EXIST);
        }
        return baseResponse;
    }

    /**
     *
     * @param u_id
     * @return
     */
    public BaseResponse getInfo(String u_id) {
        BaseResponse baseResponse = new BaseResponse();
        User user = User.dao.findFirst("select * from user where u_id =" + "'" + u_id + "'");
        if (user != null) {
            // 查找记录不为空
            baseResponse.setData(user);
            baseResponse.setResult(ResultCodeEnum.USER_QUERY_SUCCESS);
        } else {
            // 该账户不存在
            baseResponse.setResult(ResultCodeEnum.RECORD_NO_EXIST);
        }
        return baseResponse;
    }
}
