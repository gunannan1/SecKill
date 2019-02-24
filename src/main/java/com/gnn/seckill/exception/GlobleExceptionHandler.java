package com.gnn.seckill.exception;

import com.gnn.seckill.common.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.gnn.seckill.enums.ResultStatus.*;


/**
 * 拦截异常
 */
@ControllerAdvice  //用于定义异常处理器
@ResponseBody
public class GlobleExceptionHandler {

    private static Logger logger =  LoggerFactory.getLogger(GlobleExceptionHandler.class);

    @ExceptionHandler(value=Exception.class)
    public Result<String> exceptionHandler(HttpServletRequest request , Exception e){
        e.printStackTrace();
        if(e instanceof GlobleException){
            GlobleException ex= (GlobleException)e;
            return Result.error(ex.getStatus());
        }else if( e instanceof BindException){
            BindException ex = (BindException) e  ;
            List<ObjectError> errors = ex.getAllErrors();
            ObjectError error = errors.get(0);
            String msg = error.getDefaultMessage();
            /**
             * 打印堆栈信息
             */
            logger.error(String.format(msg, msg));
            return Result.error(BIND_ERROR);
        }else {
            return Result.error(SYSTEM_ERROR);
        }
    }
}
