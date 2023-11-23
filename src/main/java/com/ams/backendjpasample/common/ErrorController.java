package com.ams.backendjpasample.common;

import com.ams.backendjpasample.common.dto.ResMsgDto;
import com.ams.backendjpasample.common.enums.CommonError;
import com.ams.backendjpasample.common.exceptions.BizRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path.Node;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestControllerAdvice
@Slf4j
public class ErrorController {

    @ExceptionHandler(BindException.class)
    public Object handleBindException(BindException e) {
        String errorMessage = getErrorMessageFromBindingResult( e.getBindingResult() );
        return new ResponseEntity<>( new ResMsgDto<>(CommonError.INVALID_PARAMETERS, errorMessage ), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public Object handleConstraintViolationException(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        String errorMessage = "";
        if (!violations.isEmpty()) {
            StringBuilder builder = new StringBuilder();
            violations.forEach(violation -> builder.append( getErrorMessageFromConstraintViolation( violation ) ));
            errorMessage = builder.toString();
        } else {
            errorMessage = "ConstraintViolationException occured.";
        }
        return new ResponseEntity<>( new ResMsgDto<>(CommonError.INVALID_PARAMETERS, errorMessage ), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public Object handleException(Exception e) {
        log.error( e.getMessage(), e );
        return new ResponseEntity<>( new ResMsgDto<>(CommonError.UNKNOWN), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BizRuntimeException.class)
    public Object handleException(BizRuntimeException e) {
        log.error( e.getMessage(), e );
        return ResponseEntity.ok().body( new ResMsgDto<>(e.getCommonErr(), e.getDetailMsg()) );
    }

    private String getErrorMessageFromBindingResult( BindingResult bindingResult ) {
        List<String> messages = new ArrayList<>();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            messages.add( String.format( "%s 필드는 %s (입력 : %s)", fieldError.getField(), fieldError.getDefaultMessage(), fieldError.getRejectedValue() ) );
        }
        return String.join( " / ", messages );
    }

    private String getErrorMessageFromConstraintViolation( ConstraintViolation<?> violation ) {
        List<String> messages = new ArrayList<>();
        for (Node node : violation.getPropertyPath() ) {
            messages.add( String.format( "%s 필드는 %s (입력 : %s, %s)", node.getName(), violation.getMessage(), violation.getInvalidValue(), violation.getRootBeanClass().getCanonicalName() ) );
        }
        return String.join( " / ", messages );
    }

}
