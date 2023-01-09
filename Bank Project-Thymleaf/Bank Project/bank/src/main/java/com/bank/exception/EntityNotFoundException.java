package com.bank.exception;

import lombok.Data;

@Data
public class EntityNotFoundException extends RuntimeException{

    private String details;
    public EntityNotFoundException(String entityName,Integer id) {
        super("Related "+entityName+" not found ! with id : ["+id+"]");
        details="Some Speciat Details";
    }
}
