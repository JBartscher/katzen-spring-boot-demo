package org.cat.katzendemo.exception;

public class KatzenBaseException extends Exception {
    KatzenBaseException(String msg) {
        super(msg);
    }

    KatzenBaseException(String msg, Throwable cause) {
        super(msg, cause);
    }

    KatzenBaseException(Throwable cause) {
        super(cause);
    }
}
