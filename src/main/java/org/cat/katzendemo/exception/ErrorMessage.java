package org.cat.katzendemo.exception;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

/**
 * container for error messages provided by this application
 * @param status HTTP-status code
 * @param timestamp timestamp of the error
 * @param message exception message
 * @param description human readable error message
 */
public record ErrorMessage(HttpStatus status, ZonedDateTime timestamp, String message, String description) {
}
