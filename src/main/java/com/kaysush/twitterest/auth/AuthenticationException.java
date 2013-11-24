/**
 * This exception is raised if there is some error
 * during authentication process.
 * This is exception template provided by NetBeans which is used as it is.
 */

package com.kaysush.twitterest.auth;

/**
 *
 * @author SUSHIL
 */
public class AuthenticationException extends Exception {

    /**
     * Creates a new instance of <code>AuthenticationException</code> without
     * detail message.
     */
    public AuthenticationException() {
    }

    /**
     * Constructs an instance of <code>AuthenticationException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public AuthenticationException(String msg) {
        super(msg);
    }
}
