package com.bogdanmaier;

public class LexicException extends Throwable {

    private int line;
    private String message;

    public LexicException (int line, String message) {
        this.line = line;
        this.message = message;
    }

    public int getLine () {
        return line;
    }

    public String getMessage () {
        return message;
    }

}
