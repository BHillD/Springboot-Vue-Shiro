package com.example.exception;

import com.example.utlis.Response;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.HostUnauthorizedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler({HostUnauthorizedException.class, UnauthorizedException.class})
    @ResponseBody
    public ResponseEntity Unauthorized(){
        return Response.unauth();
    }

    @ExceptionHandler(UnknownAccountException.class)
    @ResponseBody
    public ResponseEntity UnknownAccount(){
        return Response.forbidden();
    }
}
