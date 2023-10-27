package com.aspl.Utils;


import com.fasterxml.jackson.core.JsonGenerationException;

import org.json.JSONException;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;

/**
 * Created by admin on 11/29/2017.
 */

public class NetworkUtil {
    public static final String WS_URL = "https://ecomtest.lightningpos.com/";

    public static void doNetworkProcessPost(String request_str,
                                            StringBuilder responseStrBuilder, String web_service_method)
            throws SocketTimeoutException, JsonGenerationException, IOException {
        URL url = new URL(WS_URL + web_service_method);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        try {
            httpURLConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setRequestMethod("POST");

            if (request_str != null) {
                OutputStream os = httpURLConnection.getOutputStream();
                OutputStreamWriter osw = new OutputStreamWriter(os);
                osw.write(request_str);
                osw.flush();
                osw.close();
                os.close();
            }

            InputStream is;

            if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                is = new BufferedInputStream(httpURLConnection.getInputStream());
            } else {
                is = new BufferedInputStream(httpURLConnection.getErrorStream());
            }

            BufferedReader streamReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));

            String line = null;

            while ((line = streamReader.readLine()) != null) {
                responseStrBuilder.append(line);
            }

            streamReader.close();
            is.close();
        } finally {
            if (httpURLConnection != null) httpURLConnection.disconnect();
        }
    }

    public static void doNetworkProcessGet(String request_str
            , StringBuilder responseStrBuilder /*, String web_service_method*/)
            throws SocketTimeoutException, JsonGenerationException, IOException, JSONException {

        HttpURLConnection connection = null;
        BufferedReader reader = null;
        try {
            //Log.e("Log","Request Url="+request_str);
            if(request_str.contains(" ")){
                request_str=request_str.replace(" ","%20");
            }
            URL url = new URL(request_str);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            connection.setConnectTimeout(1000);
            InputStream stream = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(stream));
            StringBuffer buffer = new StringBuffer();

            String line = " ";
            while ((line = reader.readLine()) != null) {
                responseStrBuilder.append(line);
            }
        } finally {
            if (connection != null) connection.disconnect();
        }

    }
    public static void doNetworkProcessGet_WithTimeout(String request_str
            , StringBuilder responseStrBuilder /*, String web_service_method*/)
            throws SocketTimeoutException, JsonGenerationException, IOException, JSONException {

        HttpURLConnection connection = null;
        BufferedReader reader = null;
        try {
            //Log.e("Log","Request Url="+request_str);
            if(request_str.contains(" ")){
                request_str=request_str.replace(" ","%20");
            }
            URL url = new URL(request_str);
            connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            connection.connect();

            InputStream stream = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(stream));
            StringBuffer buffer = new StringBuffer();

            String line = " ";
            while ((line = reader.readLine()) != null) {
                responseStrBuilder.append(line);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (connection != null) connection.disconnect();
        }

    }
}
