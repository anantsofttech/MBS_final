package com.aspl.task;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.aspl.Utils.NetworkUtil;
import com.aspl.mbsmodel.LoyaltyInfo;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.List;

/**
 * Created by Mic on 1/10/2018.
 */

public class TaskLoyaltyInfo extends AsyncTask<String, Void, String> {

    LoyaltyInfo loyaltyInfo;
    List<LoyaltyInfo> liLoyaltyInfo;
    Context context;

    TaskLoyaltyInfoEvent myTaskLoyaltyInfoEvent;

    public interface TaskLoyaltyInfoEvent{
        void onLoyaltyInfoResult(LoyaltyInfo loyaltyInfo);
    }

    public TaskLoyaltyInfo(Context context, TaskLoyaltyInfoEvent myTaskLoyaltyInfoEvent){
        this.myTaskLoyaltyInfoEvent = myTaskLoyaltyInfoEvent;
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

                loyaltyInfo = objectMapper.readValue(response, LoyaltyInfo.class);

                /*liLoyaltyInfo = objectMapper.readValue(response, new TypeReference<List<LoyaltyInfo>>() {
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
        if (loyaltyInfo != null){
            if (myTaskLoyaltyInfoEvent!=null)
                myTaskLoyaltyInfoEvent.onLoyaltyInfoResult(loyaltyInfo);
        }else{
//            Toast.makeText(context, /*R.string.str_network_message */"It seems there is an issue with network connectivity. Please try again later.", Toast.LENGTH_SHORT).show();
        }

    }
}
