package cn.zuel.wlyw.service;

import cn.zuel.wlyw.kit.BaseResponse;
import cn.zuel.wlyw.kit.ResultCodeEnum;
import cn.zuel.wlyw.model.Album;

import java.util.List;

public class AlbumService {
    /**
     * 添加相册
     *
     * @param a_u_id
     * @param a_t_id
     * @param a_name
     * @param a_desc
     * @param a_auth
     * @return
     */
    public BaseResponse createAlbum(String a_u_id, String a_t_id, String a_name, String a_desc, String a_auth) {
        BaseResponse baseResponse = new BaseResponse();
        List<Album> albums = Album.dao.find("select * from user where a_name = " + "'" + a_name + "'");
        if (albums.size() == 0) {
            // 相册不存在，可以添加
            Album album = new Album();
            album.setAUId(Integer.parseInt(a_u_id));
            album.setATId(Integer.parseInt(a_t_id));
            album.setAName(a_name);
            album.setADesc(a_desc);
            album.setAAuth(Integer.parseInt(a_auth));
            if (album.save()) {
                // 添加成功
                baseResponse.setResult(ResultCodeEnum.REGISTER_SUCCESS);
            } else {
                // 添加失败，数据库操作错误
                baseResponse.setResult(ResultCodeEnum.REGISTER_FAILURE_DB_ERROR);
            }
        } else if (albums.size() == 1) {
            // 添加失败，相册已经存在
            baseResponse.setResult(ResultCodeEnum.REGISTER_FAILURE_USER_EXIST);
        } else {
            // 添加失败，未知的系统错误
            baseResponse.setResult(ResultCodeEnum.REGISTER_FAILURE_SYS_ERROR);
        }
        return baseResponse;
    }

    /**
     * 获取用户所有的相册
     *
     * @return
     */
    public BaseResponse getAlbums(String u_id) {
        BaseResponse baseResponse = new BaseResponse();
        List<Album> albums = Album.dao.find("select * from album where a_u_id = " + "'" + u_id + "'");
        if (albums.size() > 0) {
            // 查找相册不为空
            baseResponse.setData(albums);
            baseResponse.setResult(ResultCodeEnum.ALBUM_QUERY_SUCCESS);
        } else {
            // 相册为空
            baseResponse.setResult(ResultCodeEnum.ALBUM_QUERY_NULL);
        }
        return baseResponse;
    }

    /**
     * 获取其它共享的相册（a_auth = 1）
     *
     * @param u_id
     * @return
     */
    public BaseResponse getShareAlbums(String u_id) {
        BaseResponse baseResponse = new BaseResponse();
        List<Album> albums = Album.dao.find("select * from album where a_u_id != " + "'" + u_id + "'" + "and a_auth = 1");
        if (albums.size() > 0) {
            // 查找相册不为空
            baseResponse.setData(albums);
            baseResponse.setResult(ResultCodeEnum.ALBUM_QUERY_SUCCESS);
        } else {
            // 相册为空
            baseResponse.setResult(ResultCodeEnum.ALBUM_QUERY_NULL);
        }
        return baseResponse;
    }

    /**
     * 查找某一相册
     *
     * @param a_id
     * @return
     */
    public BaseResponse getOneAlbum(String a_id) {
        BaseResponse baseResponse = new BaseResponse();
        Album album = Album.dao.findFirst("select * from album where a_id = " + "'" + a_id + "'");
        if (album != null) {
            // 查找相册不为空
            baseResponse.setData(album);
            baseResponse.setResult(ResultCodeEnum.USER_QUERY_SUCCESS);
        } else {
            // 该相册不存在
            baseResponse.setResult(ResultCodeEnum.RECORD_NO_EXIST);
        }
        return baseResponse;
    }

    /**
     * 删除相册
     *
     * @param a_id
     * @return
     */
    public BaseResponse deleteAlbum(String a_id) {
        BaseResponse baseResponse = new BaseResponse();
        Album album = Album.dao.findFirst("select * from album where a_id = " + "'" + a_id + "'");
        if (album != null) {
            // 查找相册不为空
            if (album.delete()) {
                baseResponse.setResult(ResultCodeEnum.ALBUM_DELETE_SUCCESS);
            } else {
                baseResponse.setResult(ResultCodeEnum.ALBUM_DELETE_FAILURE);
            }
        } else {
            // 该相册不存在
            baseResponse.setResult(ResultCodeEnum.ALBUM_NOT_EXIST);
        }
        return baseResponse;
    }

    /**
     * 修改相册的信息
     *
     * @param a_id
     * @param a_t_id
     * @param a_name
     * @param a_desc
     * @param a_auth
     * @return
     */
    public BaseResponse modifyAlbum(String a_id, String a_t_id, String a_name, String a_desc, String a_auth) {
        BaseResponse baseResponse = new BaseResponse();
        Album album = Album.dao.findFirst("select * from album where a_id = " + "'" + a_id + "'");
        if (album != null) {
            // 查找相册不为空
            album.setATId(Integer.parseInt(a_t_id));
            album.setAName(a_name);
            album.setADesc(a_desc);
            album.setAAuth(Integer.parseInt(a_auth));
            if (album.save()) {
                baseResponse.setResult(ResultCodeEnum.DB_UPDATE_SUCCESS);
            } else {
                baseResponse.setResult(ResultCodeEnum.DB_UPDATE_ERROR);
            }
        } else {
            // 该相册不存在
            baseResponse.setResult(ResultCodeEnum.RECORD_NO_EXIST);
        }
        return baseResponse;
    }
}
