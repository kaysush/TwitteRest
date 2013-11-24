/*
 * This helper class contains methods which will be
 * used in various parts of this wrapper.
 * So just to shorten down the code all the methods will be static.
 */
package com.kaysush.twitterest.auth;

import com.google.gson.Gson;
import com.kaysush.twitterest.models.Authentication;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.ResourceBundle;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;

/**
 *
 * @author SUSHIL
 */
public class Helper {

    public static ResourceBundle gerTwitterConfiguration() {
        ResourceBundle props = ResourceBundle.getBundle("TwitterConfiguration");
        return props;
    }

    /**
     * A method to fetch the response of given request. Adopted as it is from
     * https://github.com/Rockncoder/TwitterTutorial/blob/master/src/com/example/TwitterTutorial/MainActivity.java#L115
     *
     * @param request
     * @return String representation of the response
     */
    public static String getResponseBody(HttpRequestBase request) {
        StringBuilder sb = new StringBuilder();
        try {

            DefaultHttpClient httpClient = new DefaultHttpClient(new BasicHttpParams());
            HttpResponse response = httpClient.execute(request);
            int statusCode = response.getStatusLine().getStatusCode();
            String reason = response.getStatusLine().getReasonPhrase();

            if (statusCode == 200) {

                HttpEntity entity = response.getEntity();
                InputStream inputStream = entity.getContent();

                BufferedReader bReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
                String line = null;
                while ((line = bReader.readLine()) != null) {
                    sb.append(line);
                }
            } else {
                sb.append(reason);
            }
        } catch (UnsupportedEncodingException ex) {
        } catch (ClientProtocolException ex1) {
        } catch (IOException ex2) {
        }
        return sb.toString();
    }

    public static Authentication jsonToAuthentication(String response) {
        Authentication auth = null;
        if (response != null && response.length() > 0) {
            try {
                Gson gson = new Gson();
                auth = gson.fromJson(response, Authentication.class);
            } catch (IllegalStateException e) {

            }
        }
        return auth;
    }
}
