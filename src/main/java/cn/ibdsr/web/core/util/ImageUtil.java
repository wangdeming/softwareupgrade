package cn.ibdsr.web.core.util;

import cn.ibdsr.core.util.SpringContextHolder;
import cn.ibdsr.fastdfs.config.FastdfsProperties;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

/**
 * @Description 图片路径拼接工具类
 * @Version V1.0
 * @CreateDate 2018/5/9 11:27
 *
 * Date                  Author               Description
 * ------------------------------------------------------
 * 2019-05-09 9:27:00    XuZhipeng               类说明
 *
 */
public class ImageUtil {


    // 图片访问的前缀
    public static String PREFIX_IMAGE_URL = SpringContextHolder.getBean(FastdfsProperties.class).getVisit();

    // 图片后缀认证集合
    public static List<String> IMAGE_SUFFIX_LIST = Arrays.asList("jpg", "jpeg", "png", "svg", "gif", "ico");

    /**
     * @Description 裁剪 图片的URL
     * 比如将http://172.16.1.200:10080/group1/M00/00/00/rBAByFmDy-WAT7FXAALo7BXRELY084.jpg
     * 截取为 group1/M00/00/00/rBAByFmDy-WAT7FXAALo7BXRELY084.jpg
     * @Date 2017/8/17 15:33
     * @param targetImageURL 目标的图片的URL
     * @throws
     * @return
     */
    public static String cutImageURL(String targetImageURL) {

        if (StringUtils.isEmpty(targetImageURL)) {
            return targetImageURL;
        }
        int imageIndex = targetImageURL.indexOf(PREFIX_IMAGE_URL);
        if (imageIndex < 0) {
            return targetImageURL;
        }
        return targetImageURL.substring(imageIndex + PREFIX_IMAGE_URL.length());
    }

    /**
     * @Description 补全 图片的URL
     * 比如将/group1/M00/00/00/rBAByFmDy-WAT7FXAALo7BXRELY084.jpg
     * 补全为 http://172.16.1.200:10080/group1/M00/00/00/rBAByFmDy-WAT7FXAALo7BXRELY084.jpg
     * @Date 2017/8/17 15:33
     * @param targetImageURL 目标的图片的URL
     * @throws
     * @return
     */
    public static String setImageURL(Object targetImageURL) {
        if (null == targetImageURL) {
            return "";
        }
        int imageIndex = targetImageURL.toString().indexOf(PREFIX_IMAGE_URL);
        if (imageIndex > -1) {
            return targetImageURL.toString();
        }
        return PREFIX_IMAGE_URL + targetImageURL;
    }

    /**
     * 根据ImageIO判断图片是否正确
     *
     * @param file
     * @return
     */
    public static Boolean isImage(MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            if (inputStream == null) {
                return false;
            }
            Image img = ImageIO.read(inputStream);
            return !(img == null || img.getWidth(null) <= 0 || img.getHeight(null) <= 0);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 校验该文件是否为图片类型
     *
     * @param fType 文件类型
     * @return
     */
    public static Boolean checkFileOfImage(String fType) {
        for (String suffix : IMAGE_SUFFIX_LIST) {
            if (fType.indexOf(suffix) > -1) {
                return true;
            }
        }
        return false;
    }

    /**
     * 根据图片链接，把图片转为byte数组
     *
     * @param urlPath 图片链接
     * @return byte[]
     */
    public static byte[] getImageFromURL(String urlPath) {
        byte[] data = null;
        InputStream in = null;
        HttpURLConnection conn = null;
        try {
            URL url = new URL(urlPath);
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            // conn.setDoOutput(true);
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(6000);
            in = conn.getInputStream();
            if (conn.getResponseCode() == HttpStatus.OK.value()) {
                data = readInputStream(in);
            } else{
                data = null;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            conn.disconnect();
        }
        return data;
    }

    /**
     * 读取InputStream数据，转为byte[]数据类型
     *
     * @param in InputStream数据
     * @return 返回byte[]数据
     */
    public static byte[] readInputStream(InputStream in) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length = -1;
        try {
            while ((length = in.read(buffer)) != -1) {
                baos.write(buffer, 0, length);
            }
            baos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] data = baos.toByteArray();
        try {
            in.close();
            baos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

}
