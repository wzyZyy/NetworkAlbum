package cn.zuel.wlyw.controller;

import cn.zuel.wlyw.kit.BaseResponse;
import cn.zuel.wlyw.kit.ResultCodeEnum;
import cn.zuel.wlyw.service.AlbumService;
import com.jfinal.core.Controller;
import com.jfinal.kit.StrKit;

public class AlbumController extends Controller {
    AlbumService albumService = new AlbumService();

    /**
     * 添加相册
     */
    public void createAlbum() {
        BaseResponse baseResponse = new BaseResponse();
        String a_u_id = getPara("a_u_id");
        String a_t_id = getPara("a_t_id");
        String a_name = getPara("a_name");
        String a_desc = getPara("a_desc");
        String a_auth = getPara("a_auth");

        if (!StrKit.isBlank(a_u_id) && !StrKit.isBlank(a_t_id) && !StrKit.isBlank(a_name) && !StrKit.isBlank(a_auth)) {
            // 需要的相册信息完整
            baseResponse = albumService.createAlbum(a_u_id, a_t_id, a_name, a_desc, a_auth);
        } else {
            // 提交的必要表单信息不完整
            baseResponse.setResult(ResultCodeEnum.PARA_NUM_ERROR);
        }
        renderJson(baseResponse);
    }

    /**
     * 获取某一用户所有的相册
     */
    public void getAlbums() {
        BaseResponse baseResponse = new BaseResponse();
        String u_id = getPara("u_id");
        if (!StrKit.isBlank(u_id)) {
            baseResponse = albumService.getAlbums(u_id);
        } else {
            baseResponse.setResult(ResultCodeEnum.PARA_NUM_ERROR);
        }
        renderJson(baseResponse);
    }

    /**
     * 获取其它共享的相册
     */
    public void getShareAlbums() {
        BaseResponse baseResponse = new BaseResponse();
        String u_id = getPara("u_id");
        if (!StrKit.isBlank(u_id)) {
            baseResponse = albumService.getShareAlbums(u_id);
        } else {
            baseResponse.setResult(ResultCodeEnum.PARA_NUM_ERROR);
        }
        renderJson(baseResponse);
    }

    /**
     * 获取某一相册
     */
    public void getOneAlbum() {
        BaseResponse baseResponse = new BaseResponse();
        String a_id = getPara("a_id");
        if (!StrKit.isBlank(a_id)) {
            baseResponse = albumService.getOneAlbum(a_id);
        } else {
            baseResponse.setResult(ResultCodeEnum.PARA_NUM_ERROR);
        }
        renderJson(baseResponse);
    }

    /**
     * 删除相册
     */
    public void deleteAlbum() {
        BaseResponse baseResponse = new BaseResponse();
        String a_id = getPara("a_id");
        if (!StrKit.isBlank(a_id)) {
            baseResponse = albumService.deleteAlbum(a_id);
        } else {
            baseResponse.setResult(ResultCodeEnum.PARA_NUM_ERROR);
        }
        renderJson(baseResponse);
    }

    /**
     * 修改相册的信息
     */
    public void modifyAlbum() {
        BaseResponse baseResponse = new BaseResponse();
        String a_id = getPara("a_id");
        String a_t_id = getPara("a_t_id");
        String a_name = getPara("a_name");
        String a_desc = getPara("a_desc");
        String a_auth = getPara("a_auth");
        if (!StrKit.isBlank(a_id) && !StrKit.isBlank(a_t_id) && !StrKit.isBlank(a_name) && !StrKit.isBlank(a_desc) && !StrKit.isBlank(a_auth)) {
            baseResponse = albumService.modifyAlbum(a_id, a_t_id, a_name, a_desc, a_auth);
        } else {
            baseResponse.setResult(ResultCodeEnum.PARA_NUM_ERROR);
        }
        renderJson(baseResponse);
    }
}
