package com.smallcase.lushuju.controller;

import com.smallcase.lushuju.service.PersonInfoService;
import com.smallcase.lushuju.utils.PathUtil;
import com.smallcase.lushuju.utils.RestfulResult;
import com.smallcase.lushuju.utils.ResultVOUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.*;

/**
 * Package: com.smallcase.lushuju.controller
 * Author: smallcase
 * Date: Created in 2018/10/18 22:09
 * 由前端解决
 */
@Controller

public class PicController {

    @Autowired
    private PersonInfoService service;

//    @RequestMapping(value = "/file", method = RequestMethod.GET)
//    public String file() {
//        return "uploadForm";
//    }




    @RequestMapping(value = "/upload/img", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity upf(HttpServletRequest request) {
        String personId = (String) request.getSession().getAttribute("personId");

        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        if (multipartResolver.isMultipart(request) && personId != null) {

            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            CommonsMultipartFile image = (CommonsMultipartFile) multipartHttpServletRequest.getFile("image");

            //获取上传文件名字
            String filename = image.getOriginalFilename();
            String basePath = PathUtil.getImgBasePath();
            String childPath = PathUtil.getImageChildPath(personId);
            File filepath = new File(basePath + childPath, filename);

            if (!filepath.getParentFile().exists()) {
                filepath.getParentFile().mkdirs();
            }

            //上传图片
            try {
                image.transferTo(new File(basePath + childPath + filename));
            } catch (IOException e) {
                return RestfulResult.serviceErr(ResultVOUtil.error(e.getMessage()));
            }

            //将图片信息写入数据库
            try {
                service.insertImg(childPath + filename, filename, personId);

            } catch (RuntimeException e) {
                return RestfulResult.serviceErr(ResultVOUtil.error(e.getMessage()));
            }


            return RestfulResult.ok(ResultVOUtil.success("成功"));

        }
        return RestfulResult.serviceErr(ResultVOUtil.error("出错，可能personId为空"));
    }


    @RequestMapping(value = "/download/img", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity downImg(@RequestParam String personId) {
        String imgPath;
        String filename;
        try {
            imgPath = service.getImgPath(personId);
            filename = service.getFileName(personId);
        } catch (RuntimeException e) {
            return RestfulResult.serviceErr(ResultVOUtil.success(e.getMessage()));
        }


        String basePath = PathUtil.getImgBasePath();
        File file = new File(basePath + imgPath);

        //下载显示的文件名，解决中文名的乱码
        String downloadFileName;
        try {
            downloadFileName = new String(filename.getBytes("UTF-8"), "iso-8859-1");
        } catch (UnsupportedEncodingException e) {
            return RestfulResult.serviceErr(ResultVOUtil.success(e.getMessage()));
        }

        HttpHeaders httpHeaders = new HttpHeaders();
        //通知浏览器以attachment方式打开图片（下载方式）
        httpHeaders.setContentDispositionFormData("attachment", downloadFileName);
        //二进制数据流文件的下载方式
        httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        try {
            return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), httpHeaders, HttpStatus.CREATED);
        } catch (IOException e) {
            return RestfulResult.serviceErr(ResultVOUtil.success(e.getMessage()));

        }

    }

}