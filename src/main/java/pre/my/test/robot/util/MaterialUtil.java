package pre.my.test.robot.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pre.my.test.robot.dto.material.AppendNewsMaterial;
import pre.my.test.robot.dto.material.Material;
import pre.my.test.robot.dto.material.MaterialBatchGetParam;
import pre.my.test.robot.dto.material.MaterialCount;
import pre.my.test.robot.dto.material.Materials;
import pre.my.test.robot.dto.material.newslist.NewsMaterials;
import pre.my.test.robot.dto.material.otherList.OtherMaterials;

import java.io.IOException;

/**
 * Author:qiang.zeng on 2017/3/28.
 */
public class MaterialUtil {
    private static final Logger logger = LoggerFactory.getLogger(MaterialUtil.class);

    /**
     * 新增永久图文素材
     * @param token
     * @param appendNewsMaterial
     * @return
     * @throws IOException
     */
    public static JSONObject appendNewsMaterial(String token, String appendNewsMaterial) throws IOException {
        String url = Constants.MATERIAL_PERMANENT_NEWS_UPLOAD_URL.replace("ACCESS_TOKEN", token);
        JSONObject jsonObject = HttpConnectUtil.doPostStr(url,appendNewsMaterial);
        logger.debug(jsonObject.toJSONString());
        return jsonObject;
    }

    /**
     * 获取素材总数
     * @param token
     * @return
     * @throws IOException
     */
    public static MaterialCount getMaterialCount(String token) throws IOException {
        MaterialCount materialCount = null;
        String url = Constants.MATERIAL_COUNT_GET_URL.replace("ACCESS_TOKEN", token);
        JSONObject jsonObject = HttpConnectUtil.doGetStr(url);
        if (null == jsonObject.getInteger("errcode")) {
            materialCount = new MaterialCount();
            materialCount.setImage_count(jsonObject.getString("image_count"));
            materialCount.setNews_count(jsonObject.getString("news_count"));
            materialCount.setVideo_count(jsonObject.getString("video_count"));
            materialCount.setVoice_count(jsonObject.getString("voice_count"));
        } else {
            logger.debug(jsonObject.toJSONString());
        }
        return materialCount;
    }

    /**
     * 获取素材列表 图文列表，图片列表
     * @param token
     * @param materialBatchGetParam
     * @return
     * @throws IOException
     */
    public static Materials getMaterialList(String token, MaterialBatchGetParam materialBatchGetParam) throws IOException {
        String url = Constants.MATERIAL_DETAIL_LIST_GET_URL.replace("ACCESS_TOKEN", token);
        JSONObject jsonObject = HttpConnectUtil.doPostStr(url, JSONObject.toJSONString(materialBatchGetParam));
        if (jsonObject.getInteger("errcode") != null) {
            logger.debug(jsonObject.toJSONString());
            return null;
        }
        if (Constants.MEDIA_TYPE_NEWS.equals(materialBatchGetParam.getType())) {
            NewsMaterials newsMaterials = JSON.parseObject(jsonObject.toJSONString(), NewsMaterials.class);
            return newsMaterials;
        } else {
            OtherMaterials otherMaterials = JSON.parseObject(jsonObject.toJSONString(), OtherMaterials.class);
            return otherMaterials;
        }
    }

    /**
     * 删除素材，包括图文和其他图片、视频等
     * @param token
     * @param mediaId
     * @return
     */
    public static JSONObject deleteMaterial(String token, String mediaId) {
        String url = Constants.MATERIAL_DELETE_URL.replace("ACCESS_TOKEN", token);
        JSONObject jsonObject = null;
        try {
            jsonObject = HttpConnectUtil.doPostStr(url, mediaId);
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.debug(jsonObject.toJSONString());
        return jsonObject;
    }


}
