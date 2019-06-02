package com.example.utlis;

import com.alibaba.fastjson.JSONObject;

/**
 * @author
 * @date 2019/5/31
 * @description  JSON工具类
 */


public class Response {
    public static JSONObject ok(){
        JSONObject response = new JSONObject();
        response.put("status", "200");
        response.put("msg", "success");
        return response;
    }

    public static JSONObject ok(Object data){
        JSONObject response = new JSONObject();
        response.put("status", "200");
        response.put("msg", "success");
        response.put("obj", data);
        return response;
    }

    public static JSONObject err(String description){
        JSONObject response = new JSONObject();
        response.put("status", "400");
        response.put("msg", description);
        return response;
    }

    public static JSONObject err(){
        JSONObject response = new JSONObject();
        response.put("status", "400");
        response.put("msg", "fail");
        return response;
    }
}
