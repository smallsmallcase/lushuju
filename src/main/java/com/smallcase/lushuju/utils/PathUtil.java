package com.smallcase.lushuju.utils;

public class PathUtil {
    //获取文件分割符号
    private static String separator = System.getProperty("file.separator");

    /**
     * 返回图片根路径
     * @return
     */
    public static String getImgBasePath() {
        String os = System.getProperty("os.name");
        String basePath;
        if (os.startsWith("Win")) {
            basePath = "D:/workspace/imag";
        } else {
            basePath = "/root/image";
        }

        basePath = basePath.replace("/", separator);
        return basePath;
    }

    public static String getSeparator() {
        return separator;
    }


    /**
     * 返回图片子路径
     * @param personId
     * @return
     */
    public static String getImageChildPath(String personId) {
        String imagePath = "/file" + "/" + personId + "/";
        return imagePath.replace("/", separator);
    }



}
