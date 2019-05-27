package com.smallcase.lushuju.utils;

public class PathUtil {

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

        basePath = basePath.replace("/", getSeparator());
        return basePath;
    }

    /**
     * 获取解析excel时，上传Excel的根路径
     * @return
     */
    public static String getExcelTempPath(){
        String os = System.getProperty("os.name");
        String basePath;
        if (os.startsWith("Win")){
            basePath = "D:/workspace/excel/temp";
        }else{
            basePath="root/excel/temp";
        }
        basePath = basePath.replace("/",getSeparator());
        return basePath;

    }

    private static String getSeparator() {
        return System.getProperty("file.separator");
    }


    /**
     * 返回图片子路径
     * @param personId
     * @return
     */
    public static String getImageChildPath(String personId) {
        String imagePath = "/file" + "/" + personId + "/";
        return imagePath.replace("/", getSeparator());
    }

    public static String getExcelChildPath(String userId){
        String excelPath = "/" + userId;
        return excelPath.replace("/", getSeparator());
    }



}
