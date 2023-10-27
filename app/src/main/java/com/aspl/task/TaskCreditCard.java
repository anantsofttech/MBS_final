package com.aspl.task;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.aspl.Utils.NetworkUtil;
import com.aspl.mbs.R;
import com.aspl.mbsmodel.CustomerCard;
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

public class TaskCreditCard extends AsyncTask<String, Void, String>{
    Context context;

    TaskCreditCardEvent taskCreditCardEvent;
    ProgressDialog loading = null;
    String response = "";
    List<PayWareModel> liCreditCard;
    String forAction = "";


    public interface TaskCreditCardEvent{
        void creditCardResult(List<PayWareModel> liCreditCard, String forAction);

//        void creditCardDeleteResult(List<PayWareModel> liCreditCard);
    }

    public TaskCreditCard(Context context, TaskCreditCardEvent taskCreditCardEvent, String s) {
        this.context = context;
        this.taskCreditCardEvent = taskCreditCardEvent;
        forAction = s;
    }

    @SuppressLint("ResourceType")
    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        if(context != null) {
            loading = new ProgressDialog(context, R.style.MyprogressDTheme);
            loading.setCancelable(false);
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
                NetworkUtil.doNetworkProcessGet_WithTimeout(strings[0], responseStrBuilder);
                response = responseStrBuilder.toString();
                Log.e("Log","TimeOut Resp="+response);
                ObjectMapper objectMapper = new ObjectMapper();
                //getShippingData = objectMapper.readValue(response, ShippingData.class);
                liCreditCard = objectMapper.readValue(response, new TypeReference<List<PayWareModel>>() {
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

//        if(forAction.equals("delete")){
//            if(taskCreditCardEvent != null){
//                taskCreditCardEvent.creditCardDeleteResult(liCreditCard);
//            }
//        }else{
            if(taskCreditCardEvent != null){
                taskCreditCardEvent.creditCardResult(liCreditCard,forAction);
            }
//        }

        if(loading != null && loading.isShowing()){
            loading.dismiss();
        }

    }
}
