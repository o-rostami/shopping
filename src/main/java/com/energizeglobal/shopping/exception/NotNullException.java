package com.energizeglobal.shopping.exception;


/**
 * A<i>NotNullException</i>.This Exception will be thrown whenever every filed value would be null<p>
 *
 * @author Omid Rostami
 */

public class NotNullException extends BaseException {

    public NotNullException(String message, String... args) {
        super(message);
        setArguments(args);
    }

    public NotNullException() {
    }
}
