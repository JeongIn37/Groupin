package com.songpyeon.groupin.handler;

import com.songpyeon.groupin.handler.ex.CustomValidationException;
import com.songpyeon.groupin.util.Script;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class ControllerExceptionHandler {
    @ExceptionHandler(CustomValidationException.class)
    public String validationException(CustomValidationException e){
        return Script.back(e.getErrorMap().toString());
    }
}
