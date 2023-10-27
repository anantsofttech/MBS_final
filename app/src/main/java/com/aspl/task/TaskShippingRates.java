package com.aspl.task;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.aspl.Utils.NetworkUtil;
import com.aspl.mbs.R;
import com.aspl.mbsmodel.CalculateShippingModel;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;

import java.io.IOException;
import java.net.SocketTimeoutException;

public class TaskShippingRates extends AsyncTask<String, Void, String> {

    public TaskShippingRatesEvent taskShippingRatesEvent;
    CalculateShippingModel calculateShippingModel;
    public Context context;
    ProgressDialog loading = null;
    String method;
    String servicename;


    public TaskShippingRates(TaskShippingRatesEvent taskShippingRatesEvent, Context context, String method, String servicename) {
        this.context = context;
        this.taskShippingRatesEvent = taskShippingRatesEvent;
        this.method = method;
        this.servicename = servicename;
    }

    public interface TaskShippingRatesEvent {
        void onShippingRatesResult(CalculateShippingModel calculateShippingModel, String method, String servicename);
    }

    @SuppressLint("ResourceType")
    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        loading = new ProgressDialog(context, R.style.MyprogressDTheme);
        loading.setCancelable(false);
        loading.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        loading.show();
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
                    NetworkUtil.doNetworkProcessGet(strings[0], responseStrBuilder);
                    String response = responseStrBuilder.toString();
                    Log.i("web service", "Response : " + response);
                    ObjectMapper objectMapper = new ObjectMapper();
                    calculateShippingModel = objectMapper.readValue(response, CalculateShippingModel.class);
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

        if(loading != null && loading.isShowing()){
            loading.dismiss();
        }

        if(taskShippingRatesEvent != null){
            taskShippingRatesEvent.onShippingRatesResult(calculateShippingModel,method,servicename);
        }
    }
}
