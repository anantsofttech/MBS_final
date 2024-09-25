package com.aspl.task;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.aspl.Utils.NetworkUtil;
import com.aspl.mbsmodel.GCAddtoCartModel;
import com.aspl.mbsmodel.PayWareModel;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.List;

public class TaskGCAddtoCart extends AsyncTask<String, Void, String> {

    Context context;
    public TaskGCAddtoCartEvent taskGCAddtoCartEvent;
    GCAddtoCartModel gcAddtoCartModel;

    public TaskGCAddtoCart(Context context, TaskGCAddtoCartEvent taskGCAddtoCartEvent) {
        this.context = context;
        this.taskGCAddtoCartEvent = taskGCAddtoCartEvent;
    }

    public interface TaskGCAddtoCartEvent {
        void GCaddToCartEventResult(GCAddtoCartModel gcAddtoCartModel);
    }

    @Override
    protected String doInBackground(String... strings) {

        StringBuilder responseStrBuilder = new StringBuilder();
            try {
                NetworkUtil.doNetworkProcessGet(strings[0], responseStrBuilder);
                String response = responseStrBuilder.toString();
                Log.e("log", "Gift Card Response :" + response);
                ObjectMapper objectMapper = new ObjectMapper();
                gcAddtoCartModel = objectMapper.readValue(response, GCAddtoCartModel.class);
                return response;
            } catch (IOException | JSONException e) {
            e.printStackTrace();
            }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (taskGCAddtoCartEvent != null) {
            taskGCAddtoCartEvent.GCaddToCartEventResult(gcAddtoCartModel);
        }
    }
}
