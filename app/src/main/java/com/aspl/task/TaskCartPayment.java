package com.aspl.task;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.aspl.Utils.Constant;
import com.aspl.Utils.NetworkUtil;
import com.aspl.fragment.PaymentFragment;
import com.aspl.mbs.R;
import com.aspl.mbsmodel.ShoppingCardModel;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.List;

public class TaskCartPayment extends AsyncTask<String, Void, String> {

    TaskCardPaymentEvent myTaskCardPaymentEvent;
    String s;
    Context context;
    ProgressDialog loading = null;

//    public TaskCartPayment(Context context, TaskCardPaymentEvent myTaskCardPaymentEvent, String s) {
//        this.context = context;
//        this.myTaskCardPaymentEvent = myTaskCardPaymentEvent;
//        this.s = s;
//    }

    public TaskCartPayment(Context context, TaskCardPaymentEvent myTaskCardPaymentEvent) {
        this.context = context;
        this.myTaskCardPaymentEvent = myTaskCardPaymentEvent;
    }

//    public TaskCartPayment(TaskCardPaymentEvent myTaskCardPaymentEvent, String s) {
//        this.myTaskCardPaymentEvent = myTaskCardPaymentEvent;
//        this.s = s;
//    }

    public interface TaskCardPaymentEvent {
        void onCardPaymentDataResult(List<ShoppingCardModel> liShoppingCard, String s);
    }

    @SuppressLint("ResourceType")
    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        if (context != null) {
            loading = new ProgressDialog(context, R.style.MyprogressDTheme);
            loading.setCancelable(false);
//        loading.setMessage(Constant.Message.AuthenticatingUser);
            loading.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            loading.show();
        }
    }


    @Override
    protected String doInBackground(String... strings) {

        Log.i("web service--Cart", "request url : " + strings[0]);
        int count = 0;
        boolean retry = false;
        StringBuilder responseStrBuilder = new StringBuilder();
        do {
            retry = false;
            try {
                NetworkUtil.doNetworkProcessGet(strings[0], responseStrBuilder);
                String response = responseStrBuilder.toString();
                Log.i("web service--Cart", "Response : " + response);
                ObjectMapper objectMapper = new ObjectMapper();
                //cardModel = objectMapper.readValue(response, ShoppingCardModel.class);
                Constant.liCardModel = objectMapper.readValue(response, new TypeReference<List<ShoppingCardModel>>() {
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
        /*if (Constant.liCardModel != null){
            MainActivity.onUpdateCartItem(Constant.liCardModel);
        }*/
        if (myTaskCardPaymentEvent != null && Constant.liCardModel != null) {
            myTaskCardPaymentEvent.onCardPaymentDataResult(Constant.liCardModel, s);
        }

        if (loading != null && loading.isShowing()) {
            loading.dismiss();
        }
    }
}
