package com.aspl.task;

import android.os.AsyncTask;
import android.util.Log;

import com.aspl.Utils.NetworkUtil;
import com.aspl.mbsmodel.CreditCartSetting;
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

public class TaskDeleteOrderForDemoStore extends AsyncTask<String, Void, String> {

   /* CreditCartSetting creditCartSetting;

    public TaskGetCreditCardSettingEvent myTaskGetCreditCardSettingEvent;
    public interface TaskGetCreditCardSettingEvent{
        void creditCartSettingResult(CreditCartSetting creditCartSetting);
    }*/

/*    public TaskDeleteOrderForDemoStore(TaskGetCreditCardSettingEvent myTaskGetCreditCardSettingEvent){
        this.myTaskGetCreditCardSettingEvent = myTaskGetCreditCardSettingEvent;
    }*/

    @Override
    protected String doInBackground(String... strings) {
        Log.i("web service", "request url : " + strings[0]);
        int count = 0;
        boolean retry = false;
        String response="";
        StringBuilder responseStrBuilder = new StringBuilder();
        do {
            retry = false;
            try {
                NetworkUtil.doNetworkProcessGet(strings[0], responseStrBuilder);
                response = responseStrBuilder.toString();

                Log.e("log", "Response--->" + response);
                //ObjectMapper objectMapper = new ObjectMapper();
                //creditCartSetting = objectMapper.readValue(response, CreditCartSetting.class);
                //payWareModels = objectMapper.readValue(response, new TypeReference<List<PayWareModel>>() {});
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
        /*if (myTaskGetCreditCardSettingEvent!=null)
            myTaskGetCreditCardSettingEvent.creditCartSettingResult(creditCartSetting);*/
    }
}
