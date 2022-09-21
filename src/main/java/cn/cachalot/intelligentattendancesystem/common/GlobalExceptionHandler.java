package cn.cachalot.intelligentattendancesystem.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLIntegrityConstraintViolationException;


//全局异常处理
@ControllerAdvice(annotations = {RestController.class, Controller.class})//处理加了 RestController注解的类
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {
    //针对每一种异常写一个方法
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public R<String> handleException(SQLIntegrityConstraintViolationException e) {
        //Duplicate entry 'lijingyu' for key 'employee.idx_username'
        log.error(e.getMessage());
        if (e.getMessage().contains("Duplicate entry")) {
            String[] split = e.getMessage().split(" ");
            String msg = split[2] + "已存在";
            return R.error(msg);
        }
        return R.error("系统异常");
    }

    @ExceptionHandler(CustomException.class)
    public R<String> handleException(CustomException e) {
        //Duplicate entry 'lijingyu' for key 'employee.idx_username'
        log.error(e.getMessage());
        return R.error(e.getMessage());
    }
}
