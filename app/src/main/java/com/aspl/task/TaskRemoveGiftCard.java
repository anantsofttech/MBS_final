package com.aspl.task;

import android.os.AsyncTask;
import android.util.Log;

import com.aspl.Utils.Constant;
import com.aspl.Utils.NetworkUtil;
import com.aspl.mbsmodel.DatabaseInfo;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;

public class TaskRemoveGiftCard extends AsyncTask<String, Void, String> {

    // Define the interface
    public interface TaskRemoveGiftCardListener {
        void onTaskCompleted(String result, int position);
    }

    private TaskRemoveGiftCardListener listener;
    private int position;

    // Constructor to pass the listener and position
    public TaskRemoveGiftCard(TaskRemoveGiftCardListener listener, int position) {
        this.listener = listener;
        this.position = position;
    }

    @Override
    protected String doInBackground(String... strings) {
        Log.i("web service", "request url : " + strings[0]);
        StringBuilder responseStrBuilder = new StringBuilder();
        try {
            NetworkUtil.doNetworkProcessGet(strings[0], responseStrBuilder);
            String response = responseStrBuilder.toString();
            Log.e("log", "Response  " + response);
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
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        // Log the result
        Log.e("TaskRemoveGiftCard", "Remove Gift Card WS Response: " + result);
        if (listener != null) {
            listener.onTaskCompleted(result, position); // Pass the result back to the listener
        }
    }
}
