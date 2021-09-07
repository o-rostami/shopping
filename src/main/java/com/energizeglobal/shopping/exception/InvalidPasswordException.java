package com.energizeglobal.shopping.exception;


/**
 * A<i>InvalidPasswordException</i>.This Exception will be thrown whenever every filed value would be null<p>
 *
 * @author Omid Rostami
 */

public class InvalidPasswordException extends BaseException {

    public InvalidPasswordException(String message, String... args) {
        super(message);
        setArguments(args);
    }

    public InvalidPasswordException() {
    }
}
