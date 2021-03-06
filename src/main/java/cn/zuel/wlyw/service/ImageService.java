package cn.zuel.wlyw.service;

import cn.zuel.wlyw.kit.BaseResponse;
import cn.zuel.wlyw.kit.ResultCodeEnum;
import cn.zuel.wlyw.model.Image;
import com.jfinal.upload.UploadFile;

import java.util.List;

public class ImageService {
    /**
     * 上传图片
     *
     * @param i_a_id
     * @param uploadFile
     * @return
     */
    public BaseResponse uploadImage(final String i_a_id, final UploadFile uploadFile) {
        final BaseResponse baseResponse = new BaseResponse();
        String i_name = uploadFile.getFileName();
        String i_path = "/upload/" + i_name;
        Image image = new Image();
        image.setIAId(Integer.parseInt(i_a_id));
        image.setIName(i_name);
        image.setIPath(i_path);
        if (image.save()) {
            baseResponse.setResult(ResultCodeEnum.IMAGE_UPLOAD_SUCCESS);
        } else {
            baseResponse.setResult(ResultCodeEnum.IMAGE_UPLOAD_FAILURE_DB_ERROR);
        }
        return baseResponse;
    }

    /**
     * 查询相册中的图片
     *
     * @param i_a_id
     * @return
     */
    public BaseResponse getImages(String i_a_id) {
        BaseResponse baseResponse = new BaseResponse();
        List<Image> imageList = Image.dao.find("select * from image where i_a_id = " + "'" + i_a_id + "'");
        if (imageList.size() > 0) {
            baseResponse.setData(imageList);
            baseResponse.setResult(ResultCodeEnum.IMAGE_QUERY_SUCCESS);
        } else {
            baseResponse.setResult(ResultCodeEnum.IMAGE_QUERY_NULL);
        }
        return baseResponse;
    }

    /**
     * 删除照片
     *
     * @param i_id
     * @return
     */
    public BaseResponse deleteImage(String i_id) {
        BaseResponse baseResponse = new BaseResponse();
        Image image = Image.dao.findById(i_id);

        if (image != null) {
            if (image.delete()) {
                baseResponse.setResult(ResultCodeEnum.IMAGE_DELETE_SUCCESS);
            } else {
                baseResponse.setResult(ResultCodeEnum.IMAGE_DELETE_FAILURE_DB_ERROR);
            }
        } else {
            baseResponse.setResult(ResultCodeEnum.IMAGE_NOT_EXIST);
        }
        return baseResponse;
    }
}
