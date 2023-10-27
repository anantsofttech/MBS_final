package com.aspl.task;

import android.os.AsyncTask;
import android.util.Log;

import com.aspl.Utils.NetworkUtil;
import com.aspl.mbsmodel.GiftWrap;
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

public class TaskGiftWrap extends AsyncTask<String, Void, String> {

    //List<ShippingData> liShippingData;
    GiftWrap giftWrap;

    public TaskGiftWrapEvent myTaskGiftWrapEvent;
    public interface TaskGiftWrapEvent{
        void onGiftWrapResponse(GiftWrap giftWrap);
    }

    public TaskGiftWrap(TaskGiftWrapEvent myTaskGiftWrapEvent){
        this.myTaskGiftWrapEvent = myTaskGiftWrapEvent;
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

                Log.e("log", "Response  " + response);
                ObjectMapper objectMapper = new ObjectMapper();
                giftWrap = objectMapper.readValue(response, GiftWrap.class);
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
        if (myTaskGiftWrapEvent!=null && giftWrap != null)
            myTaskGiftWrapEvent.onGiftWrapResponse(giftWrap);
    }
}
