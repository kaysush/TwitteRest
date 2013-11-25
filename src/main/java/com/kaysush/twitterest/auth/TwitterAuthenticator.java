/**
 * Twitter Authenticator. Does In-App 
 * Provides the authentication token for further use.
 * This is a singleton class. Use getInstance() method to initiate the authentication process.
 * After getting the instance call the authenticate method to get the access_token to be used for
 * future API calls.
 */
package com.kaysush.twitterest.auth;

import com.kaysush.twitterest.models.Authentication;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ResourceBundle;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;

/**
 *
 * @author SUSHIL
 */
public class TwitterAuthenticator {

    private static TwitterAuthenticator INSTANCE;
    private ResourceBundle props;

    public TwitterAuthenticator() {
        init();
    }

    public static TwitterAuthenticator getInstance() {
        if (INSTANCE != null) {
            return INSTANCE;
        } else {
            INSTANCE = new TwitterAuthenticator();
            return INSTANCE;
        }

    }

    /**
     * Read the configuration file to which contains 
     * API credentials.
     */
    final public void init() {
        this.props = Helper.gerTwitterConfiguration();
    }
    
    /**
     * This method initialize the authentication process and returns
     * the access_token which will be used to make future API calls. 
     * @return access_token for further use
     * @throws UnsupportedEncodingException
     * @throws AuthenticationException 
     */
    public  String authenticate() throws UnsupportedEncodingException, AuthenticationException {
        String CONSUMER_KEY = props.getString("consumer.key");
        String CONSUMER_SECRET = props.getString("consumer.secret");
        String AUTH_URL = props.getString("url.auth");

        String urlConsumerKey = URLEncoder.encode(CONSUMER_KEY, "UTF-8");
        String urlConsumerSecret = URLEncoder.encode(CONSUMER_SECRET, "UTF-8");
        String combined = urlConsumerKey + ":" + urlConsumerSecret;
        String base64Encoded = Base64.encodeBase64String(combined.getBytes());

        HttpPost httpPost = new HttpPost(AUTH_URL);
        httpPost.setHeader("Authorization", "Basic " + base64Encoded);
        httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
        httpPost.setEntity(new StringEntity("grant_type=client_credentials"));

        String rawAuthentication = Helper.getResponseBody(httpPost);
        Authentication auth = Helper.jsonToAuthentication(rawAuthentication);
        if (!auth.token_type.equals("bearer")) {
            throw new AuthenticationException("Unable to authenticate");
        } else {
            return auth.access_token;
        }
    }

}
