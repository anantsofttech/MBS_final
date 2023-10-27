package com.aspl.task;

import android.os.AsyncTask;
import android.util.Log;

import com.aspl.Utils.Constant;
import com.aspl.Utils.NetworkUtil;
import com.aspl.mbsmodel.ShoppingCardModel;
import com.aspl.mbsmodel.StoreHour;
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
 * Created by mic on April 17 2017.
 */

public class TaskStoreHour extends AsyncTask<String, Void, String> {

    private List<StoreHour> liStoreHour;
    int status = 0;

    TaskStoreHourEvent myTaskStoreHourEvent;

    public interface TaskStoreHourEvent {
        void onStoreHourResult(List<StoreHour> liStoreHour);
        void onDeliveryHourResult(List<StoreHour> liStoreHour);
    }

    public TaskStoreHour(){}

    public TaskStoreHour(TaskStoreHourEvent taskStoreHourEvent, int status) {
        this.myTaskStoreHourEvent = taskStoreHourEvent;
        this.status = status;
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
                //cardModel = objectMapper.readValue(response, ShoppingCardModel.class);
                liStoreHour = objectMapper.readValue(response, new TypeReference<List<StoreHour>>() {
                });
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

        if (myTaskStoreHourEvent != null && liStoreHour != null && status == 1) {
            myTaskStoreHourEvent.onDeliveryHourResult(liStoreHour);
        }
        if (myTaskStoreHourEvent != null && liStoreHour != null && status == 2) {
            myTaskStoreHourEvent.onStoreHourResult(liStoreHour);
        }
    }
}
