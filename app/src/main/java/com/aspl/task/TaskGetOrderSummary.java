package com.aspl.task;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.aspl.Utils.NetworkUtil;
import com.aspl.mbs.R;
import com.aspl.mbsmodel.OrderSummary;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;

import java.io.IOException;
import java.net.SocketTimeoutException;

/**
 * Created by mic on 12/5/2017.
 */

public class TaskGetOrderSummary extends AsyncTask<String, Void, String> {

    /*private List<ShoppingCardModel> liCardModel;*/

    OrderSummary orderSummary;
    ProgressDialog loading = null;
    Context context;
    boolean isDisplayloader;
    String fromwhere;

    TaskOrderSummaryEvent myTaskOrderSummaryEvent;


    public interface TaskOrderSummaryEvent {
        void onOrderSummaryResult(OrderSummary orderSummary, String fromwhere);
    }

    public TaskGetOrderSummary(){}

    public TaskGetOrderSummary(Context context, TaskOrderSummaryEvent myTaskOrderSummaryEvent, boolean isDisplayloader, String fromwhere) {
        this.myTaskOrderSummaryEvent = myTaskOrderSummaryEvent;
        this.context = context;
        this.isDisplayloader = isDisplayloader;
        this.fromwhere = fromwhere;
    }

    @SuppressLint("ResourceType")
    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        if(context != null && isDisplayloader) {
            loading = new ProgressDialog(context, R.style.MyprogressDTheme);
            loading.setCancelable(false);
//        loading.setMessage(Constant.Message.AuthenticatingUser);
            loading.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            loading.show();
        }
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
                orderSummary = objectMapper.readValue(response, OrderSummary.class);
                /*Constant.liCardModel = objectMapper.readValue(response, new TypeReference<List<ShoppingCardModel>>() {
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
        myTaskOrderSummaryEvent.onOrderSummaryResult(orderSummary,fromwhere);

        if(loading != null){
            loading.dismiss();
        }
    }
}
