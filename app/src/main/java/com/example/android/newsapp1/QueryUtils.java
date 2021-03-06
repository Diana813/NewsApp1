package com.example.android.newsapp1;


import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public final class QueryUtils {

    public static final String LOG_TAG = QueryUtils.class.getSimpleName();
    public static final int READ_TIMEOUT = 10000; //milliseconds
    public static final int CONNECT_TIMEOUT = 15000; //milliseconds


    private QueryUtils() {
    }
    /**
     * Return a list of {@link News} objects that has been built up from
     * parsing the given JSON response.
     */
    public static List<News> extractFeatureFromJson(String newsJSON) {
        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(newsJSON)) {
            return null;
        }
        // Create an empty ArrayList that we can start adding news to
        List<News> news = new ArrayList<>();

        try {

            // Create a JSONObject from the JSON response string
            JSONObject baseJsonResponse = new JSONObject(newsJSON);

            // Extract the JSONObject associated with the key called "response",
            // which represents a list of responses (or articles).
            JSONObject newsObject = baseJsonResponse.getJSONObject("response");

            // For a given news, extract the JSONObject associated with the
            // key called "results", which represents a list of all results
            // for that article.
            JSONArray currentNews = newsObject.getJSONArray("results");


            // For each article in the newsObject, create an {@link News} object
            for (int i = 0; i < newsObject.getJSONArray("results").length(); i++) {

                // Get a single article at position "i" within the list of news
                JSONObject contents = currentNews.getJSONObject(i);

                String sectionName = contents.optString("sectionName");

                String date = contents.optString("webPublicationDate");

                String title = contents.optString("webTitle");

                JSONArray tags = contents.getJSONArray("tags");
                String author = null;
                for (int j = 0; j < tags.length(); j++) {
                    JSONObject tagsContents = tags.getJSONObject(j);

                    if (tagsContents.has("webTitle")) {
                        author = tagsContents.optString("webTitle");
                    }
                }

                String url = contents.optString("webUrl");

                // Create a new {@link News} object with the magazine, title, time,
                // and url from the JSON response.
                News article = new News(sectionName, title, date, author, url);
                news.add(article);
            }

        } catch (JSONException e) {
            Log.e("QueryUtils", "Problem parsing the news JSON results", e);
        }
        return news;
    }

    /**
     * Returns new URL object from the given string URL.
     */
    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Problem building the URL ", e);
        }
        return url;
    }

    /**
     * Make an HTTP request to the given URL and return a String as the response.
     */
    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        // If the URL is null, then return early.
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(READ_TIMEOUT);
            urlConnection.setConnectTimeout(CONNECT_TIMEOUT);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the news JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    /**
     * Convert the {@link InputStream} into a String which contains the
     * whole JSON response from the server.
     */
    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    /**
     * Query the theguardian dataset and return a list of {@link News} objects.
     */
    public static List<News> fetchNewsData(String requestUrl) {

        URL url = createUrl(requestUrl);

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }

        // Extract relevant fields from the JSON response and create a list of {@link News}s
        List<News> news = extractFeatureFromJson(jsonResponse);

        return news;
    }

}
