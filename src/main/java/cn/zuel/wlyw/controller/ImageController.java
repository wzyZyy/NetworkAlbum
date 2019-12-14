package cn.zuel.wlyw.controller;

import cn.zuel.wlyw.kit.BaseResponse;
import cn.zuel.wlyw.kit.ResultCodeEnum;
import cn.zuel.wlyw.service.ImageService;
import com.jfinal.core.Controller;
import com.jfinal.kit.StrKit;
import com.jfinal.upload.UploadFile;

public class ImageController extends Controller {
    ImageService imageService = new ImageService();

    /**
     * 上传图片
     */
    public void uploadImage() {
        BaseResponse baseResponse = new BaseResponse();
        String i_a_id = getPara("a_id");
        UploadFile image = getFile("file");
        System.out.println("i_a_id = " + i_a_id);
        if (!StrKit.isBlank(i_a_id)) {
            baseResponse = imageService.uploadImage(i_a_id, image);
        } else {
            baseResponse.setResult(ResultCodeEnum.PARA_NUM_ERROR);
        }
        renderJson(baseResponse);
    }

    /**
     * 获取相册中的图片
     */
    public void getImages() {
        BaseResponse baseResponse = new BaseResponse();
        String i_a_id = getPara("a_id");
        if (!StrKit.isBlank(i_a_id)) {
            baseResponse = imageService.getImages(i_a_id);
        } else {
            baseResponse.setResult(ResultCodeEnum.PARA_NUM_ERROR);
        }
        renderJson(baseResponse);
    }
}
