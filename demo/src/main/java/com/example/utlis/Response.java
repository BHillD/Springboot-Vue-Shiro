package com.example.utlis;

import com.alibaba.fastjson.JSONObject;

/**
 * @author
 * @date 2019/5/31
 * @description  JSON封装类
 */


public class Response {
    public static JSONObject ok(){
        JSONObject Response = new JSONObject();
        Response.put("status", "200");
        Response.put("msg", "success");
        return Response;
    }

    public static JSONObject ok(Object data){
        JSONObject Response = new JSONObject();
        Response.put("status", "200");
        Response.put("msg", "success");
        Response.put("obj", data);
        return Response;
    }

    public static JSONObject err(String description){
        JSONObject Response = new JSONObject();
        Response.put("status", "400");
        Response.put("msg", description);
        return Response;
    }

    public static JSONObject err(){
        JSONObject Response = new JSONObject();
        Response.put("status", "400");
        Response.put("msg", "fail");
        return Response;
    }
}
