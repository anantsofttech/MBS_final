package com.aspl.task;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.aspl.Utils.NetworkUtil;
import com.aspl.mbs.R;
import com.aspl.mbsmodel.PayWareModel;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.List;

/**
 * Created by admin on 1/10/2018.
 */

public class TaskPayWare extends AsyncTask<String, Void, String> {

    List<PayWareModel> payWareModels;
    String fourdigitCardnum;
    Context context;
    //PayWareModel payWareModel;

    public TaskPayWarEvent taskpayWarEvent;

    public interface TaskPayWarEvent {
        //void onPaywareResponseHandle(String  payWareModel);
        void onPaywareResponseHandle(List<PayWareModel> payWareModel, String fourdigitCardnum);
    }

    public TaskPayWare(Context context, TaskPayWarEvent taskpayWarEvent, String fourdigitcardnumber) {
        this.taskpayWarEvent = taskpayWarEvent;
        fourdigitCardnum = fourdigitcardnumber;
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
                //payWareModel = objectMapper.readValue(response, PayWareModel.class);
                payWareModels = objectMapper.readValue(response, new TypeReference<List<PayWareModel>>() {});
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
        if (taskpayWarEvent != null && payWareModels != null)
            taskpayWarEvent.onPaywareResponseHandle(payWareModels,fourdigitCardnum);
    }
}
