/**
 * This class uses the access_token fetched with TwitterAuthenticator
 * and fetch user tweets.
 */

package com.kaysush.twitterest.auth;

import com.kaysush.twitterest.models.Twitter;
import org.apache.http.client.methods.HttpGet;

/**
 *
 * @author SUSHIL
 */
public class FetchTweets {
    public static Twitter fetch(String accessToken , String username){
        String TwitterStreamURL = Helper.gerTwitterConfiguration().getString("url.stream");
        HttpGet httpGet = new HttpGet(TwitterStreamURL + "?screen_name=" + username);
        httpGet.setHeader("Authorization" , "Bearer " + accessToken);
        httpGet.setHeader("Content_Type", "application/json");
        String response = Helper.getResponseBody(httpGet);
        Twitter tweets = Helper.jsonToTwitter(response);
        return tweets;
    }
}
