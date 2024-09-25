package com.aspl.task;

import android.os.AsyncTask;
import android.util.Log;
import com.aspl.Utils.NetworkUtil;
import org.json.JSONException;
import java.io.IOException;

public class TaskSessionToCart_GC extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... strings) {
        Log.i("web service", "request url : " + strings[0]);
        int count = 0;
        boolean retry;
        StringBuilder responseStrBuilder = new StringBuilder();

        do {
            retry = false;
            try {
                NetworkUtil.doNetworkProcessGet(strings[0], responseStrBuilder);
                String response = responseStrBuilder.toString();
                Log.i("web service", "Response cart: " + response);
                return response;

            } catch (IOException | JSONException e) {
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

    }
}
