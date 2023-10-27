package com.aspl.task;

import android.os.AsyncTask;
import android.util.Log;

import com.aspl.Utils.NetworkUtil;
import com.aspl.mbsmodel.UpdatePOSBillingAddress;
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

public class TaskUpdatePOSShippingAddress extends AsyncTask<String, Void, String> {

    //ShippingData shippingData;
    UpdatePOSBillingAddress updatePOSBillingAddress;

    UpdatePOSShippingAddressEvent myUpdatePOSShippingAddressEvent;

    public interface UpdatePOSShippingAddressEvent{
        void onPOSShippingAddressResult(UpdatePOSBillingAddress updatePOSBillingAddress);
    }

    public TaskUpdatePOSShippingAddress(UpdatePOSShippingAddressEvent myUpdatePOSShippingAddressEvent){
        this.myUpdatePOSShippingAddressEvent = myUpdatePOSShippingAddressEvent;
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

                if (response.length() >= 2 && response.charAt(0) == '"' && response.charAt(response.length() - 1) == '"') {
                    response = response.substring(1, response.length() - 1);
                }
                response = response.replace("\\","");

                ObjectMapper objectMapper = new ObjectMapper();
                updatePOSBillingAddress = objectMapper.readValue(response, UpdatePOSBillingAddress.class);
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
        if (updatePOSBillingAddress != null){
            if (myUpdatePOSShippingAddressEvent!=null)
                myUpdatePOSShippingAddressEvent.onPOSShippingAddressResult(updatePOSBillingAddress);
        }
    }
}
