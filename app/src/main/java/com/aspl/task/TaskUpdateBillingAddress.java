package com.aspl.task;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.aspl.Utils.NetworkUtil;
import com.aspl.mbsmodel.ShippingData;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;

import java.io.IOException;
import java.net.SocketTimeoutException;

/**
 * Created by Mic on 1/10/2018.
 */

public class TaskUpdateBillingAddress extends AsyncTask<String, Void, String> {

    ShippingData shippingData;
    Context context;

    UpdateBillingAddressEvent myUpdateBillingAddressEvent;
//    ProgressDialog loading = null;

    public interface UpdateBillingAddressEvent{
        void onBillingAddressResult(ShippingData shippingData);
    }

    public TaskUpdateBillingAddress(Context context, UpdateBillingAddressEvent myUpdateBillingAddressEvent){
        this.myUpdateBillingAddressEvent = myUpdateBillingAddressEvent;
        this.context = context;
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
                shippingData = objectMapper.readValue(response, ShippingData.class);
                return response;

            } catch (JSONException | IOException e) {
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
        myUpdateBillingAddressEvent.onBillingAddressResult(shippingData);
    }
}
