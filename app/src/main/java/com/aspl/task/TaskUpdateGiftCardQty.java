package com.aspl.task;

import android.os.AsyncTask;
import android.util.Log;

import com.aspl.Utils.NetworkUtil;

import org.json.JSONException;

import java.io.IOException;

public class TaskUpdateGiftCardQty extends AsyncTask<String, Void, String> {

    // Define the interface
    public interface onTaskCompleted_GC_QTY_Updated {
        void onTaskCompleted_GC_QTY_Updated(String result, int position);
    }

    private onTaskCompleted_GC_QTY_Updated listener;
    private int position;

    // Constructor to pass the listener and position
    public TaskUpdateGiftCardQty(onTaskCompleted_GC_QTY_Updated listener, int position) {
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

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        // Log the result
        Log.e("TaskUpdateGiftCardQty", "Gift Card Qty Update WS Response: " + result);
        if (listener != null) {
            listener.onTaskCompleted_GC_QTY_Updated(result, position); // Pass the result back to the listener
        }
    }
}
