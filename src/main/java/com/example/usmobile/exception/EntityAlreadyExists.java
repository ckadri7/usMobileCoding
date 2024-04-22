package com.example.usmobile.exception;

public class EntityAlreadyExists extends RuntimeException{

    public EntityAlreadyExists(String msg ){
        super(msg);
    }
}
