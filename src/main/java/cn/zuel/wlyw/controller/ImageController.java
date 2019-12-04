package cn.zuel.wlyw.controller;

import cn.zuel.wlyw.kit.BaseResponse;
import cn.zuel.wlyw.kit.ResultCodeEnum;
import cn.zuel.wlyw.service.ImageService;
import com.jfinal.core.Controller;
import com.jfinal.kit.StrKit;
import com.jfinal.upload.UploadFile;

import java.util.List;

public class ImageController extends Controller {
    ImageService imageService = new ImageService();

    /**
     * 上传图片
     */
    public void uploadImages() {
        BaseResponse baseResponse = new BaseResponse();
        String i_a_id = getPara("i_a_id");
        List<UploadFile> images = getFiles("files");
        if (!StrKit.isBlank(i_a_id)) {
            baseResponse = imageService.uploadImages(i_a_id, images);
        } else {
            baseResponse.setResult(ResultCodeEnum.PARA_NUM_ERROR);
        }
        renderJson(baseResponse);
    }
}
