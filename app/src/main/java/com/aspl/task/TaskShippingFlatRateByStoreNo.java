package com.aspl.task;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.aspl.Utils.NetworkUtil;
import com.aspl.fragment.DeliveryOptionsFragment;
import com.aspl.mbs.R;
import com.aspl.mbsmodel.CalculateShippingModel;
import com.aspl.mbsmodel.ShippingRatesModel;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;

import java.io.IOException;
import java.net.SocketTimeoutException;

public class TaskShippingFlatRateByStoreNo extends AsyncTask<String, Void, String> {

    TaskShippingFlatRateByStoreNoEvent taskShippingFlatRateByStoreNoEvent;
    public Context context;
    ProgressDialog loading = null;
    String method;
    ShippingRatesModel shippingRatesModel;

    public interface TaskShippingFlatRateByStoreNoEvent {
        void onShippingFlatRateResult(ShippingRatesModel shippingRatesModel);
    }

    public TaskShippingFlatRateByStoreNo(DeliveryOptionsFragment taskShippingFlatRateByStoreNoEvent, Context context, String method) {
        this.context = context;
        this.taskShippingFlatRateByStoreNoEvent = (TaskShippingFlatRateByStoreNoEvent) taskShippingFlatRateByStoreNoEvent;
        this.method = method;
    }

    @SuppressLint("ResourceType")
    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        loading = new ProgressDialog(context, R.style.MyprogressDTheme);
        loading.setCancelable(false);
        loading.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        loading.show();
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
                shippingRatesModel = objectMapper.readValue(response, ShippingRatesModel.class);
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

        if(loading != null && loading.isShowing()){
            loading.dismiss();
        }

        if(taskShippingFlatRateByStoreNoEvent != null){
            taskShippingFlatRateByStoreNoEvent.onShippingFlatRateResult(shippingRatesModel);
        }
    }
}
