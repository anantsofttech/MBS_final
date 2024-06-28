package com.aspl.task;

import android.os.AsyncTask;
import android.util.Log;

import com.aspl.mbsmodel.UpdateCartQuantity;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;

public class TaskAddtoCart extends AsyncTask<String, Void, String> {

    private UpdateCartQuantity addtocart; //use updateCartQuntity bean class to addtocart functionality so, we have
                                        // not to create two class bean diffrently with same vaiables in both

    private TaskAddToCartEvent taskAddToCartEvent;

    public TaskAddtoCart(TaskAddToCartEvent taskAddToCartEvent) {
        this.taskAddToCartEvent = taskAddToCartEvent;
    }

    public interface TaskAddToCartEvent {
        void addToCartEventResult(UpdateCartQuantity updateCartQuantity);
    }

    @Override
    protected String doInBackground(String... strings) {

        Log.i("web service", "request url : " + strings[0]);
        int count = 0;
        boolean retry = false;
        StringBuilder responseStrBuilder = new StringBuilder();
        do {
            retry = false;
            try {
                doNetworkProcessGet(strings[0], responseStrBuilder);
                String response = responseStrBuilder.toString();
                Log.e("log", "Response  " + response);
                ObjectMapper objectMapper = new ObjectMapper();
                addtocart = objectMapper.readValue(response, UpdateCartQuantity.class);
                /*liCardModel = objectMapper.readValue(response, new TypeReference<List<ShoppingCardModel>>() {
                });*/
                return response;

            } catch (JsonParseException e) {
                e.printStackTrace();
            } catch (JsonGenerationException e) {
                e.printStackTrace();
            } catch (SocketTimeoutException e) {
                e.printStackTrace();
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            retry = true;
            count += 1;
        } while (count < 3 && retry);
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        if(taskAddToCartEvent != null){
            taskAddToCartEvent.addToCartEventResult(addtocart);
        }
    }

    public static String doNetworkProcessGet(String request_str, StringBuilder responseStrBuilder)
            throws SocketTimeoutException, JsonGenerationException, IOException, JSONException {

        HttpURLConnection connection = null;
        BufferedReader reader = null;
        try {
            // Handle spaces in URL
            if (request_str.contains(" ")) {
                request_str = request_str.replace(" ", "%20");
            }

            URL url = new URL(request_str);
            connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(6000); // Set connection timeout

            // Connect and check response code
            connection.connect();
            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Successful response, proceed with reading data
                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));

                String line;
                while ((line = reader.readLine()) != null) {
                    responseStrBuilder.append(line);
                }

                return responseStrBuilder.toString(); // Return the response
            } else {
                // Handle non-successful response codes (e.g., log error)
                System.err.println("Error: Web service response code " + responseCode);
                return null;
            }
        } catch (SocketTimeoutException e) {
            // Handle timeout exception
            System.err.println("Error: Connection timed out.");
            return null; // Return null for timeout
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }


}
