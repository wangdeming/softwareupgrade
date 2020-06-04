package cn.ibdsr.web.modular.versionrecord.util;

import com.dd.plist.NSArray;
import com.dd.plist.NSDictionary;
import com.dd.plist.NSString;
import com.dd.plist.PropertyListParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * 解析ipa的工具类
 * 参考网址：https://blog.csdn.net/moyanxuan_1993_2_24/article/details/51330060
 */
public class AnalyseIpaUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(AnalyseIpaUtils.class);
    /**
     * 读取ipa
     */
    public static Map<String,Object> readIPA(File file){
        Map<String,Object> map = new HashMap<>();
        try {
            InputStream is = new FileInputStream(file);
            ZipInputStream zipIns = new ZipInputStream(is);
            ZipEntry ze;
            InputStream infoIs = null;
            NSDictionary rootDict = null;
            String icon = null;
            while ((ze = zipIns.getNextEntry()) != null) {
                if (!ze.isDirectory()) {
                    String name = ze.getName();
                    if (null != name &&
                            name.toLowerCase().contains(".app/info.plist")) {
                        ByteArrayOutputStream _copy = new
                                ByteArrayOutputStream();
                        int chunk = 0;
                        byte[] data = new byte[1024];
                        while(-1!=(chunk=zipIns.read(data))){
                            _copy.write(data, 0, chunk);
                        }
                        infoIs = new ByteArrayInputStream(_copy.toByteArray());
                        rootDict = (NSDictionary) PropertyListParser.parse(infoIs);

                        //我们可以根据info.plist结构获取任意我们需要的东西
                        //比如下面我获取图标名称，图标的目录结构请下面图片
                        //获取图标名称
                        NSDictionary iconDict = (NSDictionary) rootDict.get("CFBundleIcons");

                        while (null != iconDict) {
                            if(iconDict.containsKey("CFBundlePrimaryIcon")){
                                NSDictionary CFBundlePrimaryIcon = (NSDictionary)iconDict.get("CFBundlePrimaryIcon");
                                if(CFBundlePrimaryIcon.containsKey("CFBundleIconFiles")){
                                    NSArray CFBundleIconFiles =(NSArray)CFBundlePrimaryIcon.get("CFBundleIconFiles");
                                    icon = CFBundleIconFiles.getArray()[0].toString();
                                    if(icon.contains(".png")){
                                        icon = icon.replace(".png", "");
                                    }
                                    System.out.println("获取icon名称:" + icon);
                                    break;
                                }
                            }
                        }
                        break;
                    }
                }
            }


            ////////////////////////////////////////////////////////////////
            //如果想要查看有哪些key ，可以把下面注释放开
//            for (String keyName : rootDict.allKeys()) {
//              System.out.println(keyName + ":" + rootDict.get(keyName).toString());
//            }


            // 应用包名
            NSString parameters = (NSString) rootDict.get("CFBundleIdentifier");
            map.put("packageName", parameters.toString());
            // 应用版本名
            parameters = (NSString) rootDict.objectForKey("CFBundleShortVersionString");
            map.put("versionNo", parameters.toString());
            //应用版本号
            //parameters = (NSString) rootDict.get("CFBundleVersion");
            //map.put("versionCode", parameters.toString());

            /////////////////////////////////////////////////
            infoIs.close();
            is.close();
            zipIns.close();

        } catch (Exception e) {
            LOGGER.error("解析IPA文件失败，原因是{}", e.getMessage());
            throw new IllegalStateException("IPA文件解析失败", e);
        }
        return map;
    }
}
