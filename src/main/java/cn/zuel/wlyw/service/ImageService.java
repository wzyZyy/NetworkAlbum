package cn.zuel.wlyw.service;

import cn.zuel.wlyw.kit.BaseResponse;
import cn.zuel.wlyw.kit.ResultCodeEnum;
import cn.zuel.wlyw.model.Image;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.IAtom;
import com.jfinal.upload.UploadFile;

import java.sql.SQLException;
import java.util.List;

public class ImageService {
    /**
     * 上传图片
     *
     * @param i_a_id
     * @param uploadFiles
     * @return
     */
    public BaseResponse uploadImages(final String i_a_id, final List<UploadFile> uploadFiles) {
        final BaseResponse baseResponse = new BaseResponse();

        final boolean succeed = Db.tx(new IAtom() {
            boolean result = true;

            @Override
            public boolean run() throws SQLException {
                for (UploadFile image :
                        uploadFiles) {
                    String i_name = image.getFileName();
                    String i_path = "/upload/" + i_name;
                    Image image1 = new Image();
                    image1.setIAId(Integer.parseInt(i_a_id));
                    image1.setIName(i_name);
                    image1.setIPath(i_path);
                    if (!image1.save()) {
                        result = false;
                    }
                }
                return result;
            }
        });
        if (succeed) {
            baseResponse.setResult(ResultCodeEnum.GOODS_UPLOAD_SUCCESS);
        } else {
            baseResponse.setResult(ResultCodeEnum.GOODS_UPLOAD_FAILURE_DB_ERROR);
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
        } else if (imageList.size() == 0) {
            baseResponse.setResult(ResultCodeEnum.IMAGE_QUERY_NULL);
        } else {
            // 服务器未知错误
            baseResponse.setResult(ResultCodeEnum.UNKOWN_ERROE);
        }
        return baseResponse;
    }
}
