package org.cat.katzendemo.exception;

public class EntityNotFound extends KatzenDemoBaseException {
    EntityNotFound(String msg) {
        super(msg);
    }

    EntityNotFound(String msg, Throwable cause) {
        super(msg, cause);
    }

    EntityNotFound(Throwable cause) {
        super(cause);
    }
}
