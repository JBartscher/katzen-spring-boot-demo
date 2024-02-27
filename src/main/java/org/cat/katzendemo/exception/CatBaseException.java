package org.cat.katzendemo.exception;

public class CatBaseException extends Exception {
    public CatBaseException(String msg) {
        super(msg);
    }

    public CatBaseException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public CatBaseException(Throwable cause) {
        super(cause);
    }
}
