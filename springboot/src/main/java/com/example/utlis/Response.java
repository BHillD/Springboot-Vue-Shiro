package com.example.utlis;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * @author
 * @date 2019/5/31
 * @description  JSON工具类
 */


public class Response {
    public static ResponseEntity ok(Object o){ return new ResponseEntity(o, HttpStatus.OK); }

    public static ResponseEntity ok(){
        return new ResponseEntity(HttpStatus.OK);
    }

    public static ResponseEntity unauth(Object o){
        return new ResponseEntity(o, HttpStatus.UNAUTHORIZED);
    }

    public static ResponseEntity unauth(){ return new ResponseEntity(HttpStatus.UNAUTHORIZED); }

    public static ResponseEntity err(){
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static ResponseEntity err(Object o){
        return new ResponseEntity(o, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static ResponseEntity badReq(){
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    public static ResponseEntity forbidden(){
        return new ResponseEntity(HttpStatus.FORBIDDEN);
    }
}
