package com.aspl.task;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.aspl.Utils.NetworkUtil;
import com.aspl.mbsmodel.PayWareModel;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.List;

public class TaskPayWare extends AsyncTask<String, Void, String> {

    private List<PayWareModel> payWareModels;
    private String fourdigitCardnum;
    private Context context;
    private TaskPayWarEvent taskpayWarEvent;

    public interface TaskPayWarEvent {
        void onPaywareResponseHandle(List<PayWareModel> payWareModels, String fourdigitCardnum);
    }

    public TaskPayWare(Context context, TaskPayWarEvent taskpayWarEvent, String fourdigitcardnumber) {
        this.taskpayWarEvent = taskpayWarEvent;
        this.fourdigitCardnum = fourdigitcardnumber;
        this.context = context;
    }

    @Override
    protected String doInBackground(String... strings) {
        Log.i("web service", "request url : " + strings[0]);
        int count = 0;
        boolean retry = false;
        String response = "";
        StringBuilder responseStrBuilder = new StringBuilder();
        do {
            retry = false;
            try {
                NetworkUtil.doNetworkProcessGet(strings[0], responseStrBuilder);
                response = responseStrBuilder.toString();
                Log.i("web service", "Response : " + response);
                ObjectMapper objectMapper = new ObjectMapper();
                payWareModels = objectMapper.readValue(response, new TypeReference<List<PayWareModel>>() {});
                return response;

            } catch (SocketTimeoutException e) {
                Log.e("Network Error", "SocketTimeoutException: " + e.getMessage());
                retry = true;
                count++;
            } catch (IOException | JSONException e) {
                Log.e("Network Error", "Exception: " + e.getMessage());
                retry = true;
                count++;
            }
        } while (count < 3 && retry);

        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        taskpayWarEvent.onPaywareResponseHandle(payWareModels, fourdigitCardnum);
    }
}
