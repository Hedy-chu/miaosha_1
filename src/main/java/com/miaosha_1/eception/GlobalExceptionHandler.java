package com.miaosha_1.eception;

import com.miaosha_1.result.CodeMsg;
import com.miaosha_1.result.Result;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author qqtang
 * @Date 2020/11/18 10:24
 * @Desc 全局异常处理器
 */
  @ControllerAdvice
  @ResponseBody
  public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public Result<String> exceptionHandler(HttpServletRequest request,Exception e){
        if (e instanceof GlobalException){
            GlobalException ex = (GlobalException) e;
            return Result.error(ex.getCm());
        }
        if (e instanceof BindException){
            BindException ex = (BindException) e;
            List<ObjectError> errors = ex.getAllErrors();
            ObjectError error = errors.get(0);
            String message = error.getDefaultMessage();
            return Result.error(CodeMsg.BIND_ERROR.fillArgs(message));
        }else{
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }
}
