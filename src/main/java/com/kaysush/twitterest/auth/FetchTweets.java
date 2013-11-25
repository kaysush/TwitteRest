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
    final static String TwitterStreamURL = "https://api.twitter.com/1.1/statuses/user_timeline.json?screen_name=";
    public static Twitter fetch(String accessToken , String username){
        HttpGet httpGet = new HttpGet(TwitterStreamURL + username);
        httpGet.setHeader("Authorization" , "Bearer " + accessToken);
        httpGet.setHeader("Content_Type", "application/json");
        String response = Helper.getResponseBody(httpGet);
        Twitter tweets = Helper.jsonToTwitter(response);
        return tweets;
    }
}
