package org.cat.katzendemo.exception;

public class KatzenDemoBaseException extends Exception {
    KatzenDemoBaseException(String msg) {
        super(msg);
    }

    KatzenDemoBaseException(String msg, Throwable cause) {
        super(msg, cause);
    }

    KatzenDemoBaseException(Throwable cause) {
        super(cause);
    }
}
