package com.bank.exception.handler;

import com.bank.exception.CustomJwtException;
import com.bank.exception.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

//Exception Handler olduğunu ifade eder
@ControllerAdvice
public class GenericExceptionHandler {

    //Hangi Exception ı handler edeceğini ifade eder
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity handleEntityNotFoundException(EntityNotFoundException entityNotFoundException){
        Map<String,String> map=new HashMap<>();
        map.put("error_message",entityNotFoundException.getMessage());
        map.put("error_details",entityNotFoundException.getDetails());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
    }
    @ExceptionHandler(CustomJwtException.class)
    public ResponseEntity<Map<String, String>> handleCustomJwtException(CustomJwtException exception) {
        Map<String, String> map = new HashMap<>();
        map.put("error_message", exception.getMessage());
        map.put("error_status", exception.getHttpStatus().toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
    }

    //Exception ana hata sınıfını da buraya ekleriz çünkü tahmin ettiğimiz hataların dışında bir hata çıktığında da o hatayı yakalamak isteriz
    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException(Exception entityNotFoundException){
        Map<String,String> map=new HashMap<>();
        map.put("error_message",entityNotFoundException.getMessage());
        map.put("\nerror_cause",entityNotFoundException.getCause().toString());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
    }
}
