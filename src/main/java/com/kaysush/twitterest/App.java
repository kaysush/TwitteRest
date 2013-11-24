package com.kaysush.twitterest;

import com.kaysush.twitterest.auth.AuthenticationException;
import com.kaysush.twitterest.auth.TwitterAuthenticator;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        try {
            TwitterAuthenticator authenticator = TwitterAuthenticator.getInstance();
            String access_token = authenticator.authenticate();
            System.out.println(access_token);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } catch (AuthenticationException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
