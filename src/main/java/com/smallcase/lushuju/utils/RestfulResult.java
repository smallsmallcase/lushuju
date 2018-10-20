package com.smallcase.lushuju.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Package: com.smallcase.lushuju.utils
 * Author: smallcase
 * Date: Created in 2018/7/23 20:02
 */
public class RestfulResult {

    /**
     * 200状态码。OK - [GET]：服务器成功返回用户请求的数据，该操作是幂等的（Idempotent）。
     * @param o
     * @return
     */
    public static ResponseEntity ok(Object o) {
        return new ResponseEntity(o, HttpStatus.OK);
    }

    public static ResponseEntity ok() {
        return new ResponseEntity(HttpStatus.OK);
    }


    /**
     * 201状态码。CREATED - [POST/PUT/PATCH]：用户新建或修改数据成功。
     * @return
     */
    public static ResponseEntity createSuccess() {
        return new ResponseEntity(HttpStatus.CREATED);
    }


    /**
     * 204状态码。NO CONTENT - [DELETE]：用户删除数据成功。
     * @return
     */
    public static ResponseEntity deleteSuccess() {
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


    /**
     * 503状态码，服务器过载或其他导致请求的数据获取不到的错误。
     * @return
     */
    public static ResponseEntity serviceErr() {
        return new ResponseEntity(HttpStatus.SERVICE_UNAVAILABLE);
    }

    public static ResponseEntity serviceErr(Object o) {
        return new ResponseEntity(o,HttpStatus.OK);
    }

}
