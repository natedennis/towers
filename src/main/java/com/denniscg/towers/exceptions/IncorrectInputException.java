package com.denniscg.towers.exceptions;

public class IncorrectInputException extends Exception {
    public static final long serialVersionUID = 22L;
    public IncorrectInputException(String msg){
        super(msg);
    }
}