package com.aspl.task;

import android.os.AsyncTask;
import android.util.Log;

import com.aspl.Utils.NetworkUtil;
import com.aspl.mbsmodel.OrderDetails;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;

import java.io.IOException;
import java.net.SocketTimeoutException;

/**
 * Created by admin on 1/10/2018.
 */

public class TaskInsertTempOrder extends AsyncTask<String, Void, String> {

    //List<ShippingData> liShippingData;
    OrderDetails orderDetails;

    TaskInsertTempOrderEvent myTaskInsertTempOrderEvent;
    public interface TaskInsertTempOrderEvent{
        void onTempOrderResult(OrderDetails orderDetails);
    }

    public TaskInsertTempOrder(TaskInsertTempOrderEvent myTaskInsertTempOrderEvent){
        this.myTaskInsertTempOrderEvent = myTaskInsertTempOrderEvent;
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
                orderDetails = objectMapper.readValue(response, OrderDetails.class);
                /*orderDetails = objectMapper.readValue(response, new TypeReference<List<ShippingData>>() {});*/
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
        if (myTaskInsertTempOrderEvent!=null && orderDetails != null)
            myTaskInsertTempOrderEvent.onTempOrderResult(orderDetails);
    }
}
