package com.aspl.task;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.aspl.Utils.NetworkUtil;
import com.aspl.fragment.DeliveryOptionsFragment;
import com.aspl.mbs.R;
import com.aspl.mbsmodel.FedExServiceModel;
import com.aspl.mbsmodel.OrderSummary;
import com.aspl.mbsmodel.ShippingData;
import com.aspl.mbsmodel.ShippingInfo;
import com.aspl.mbsmodel.UPSServiceModel;
import com.aspl.mbsmodel.USPSServiceModel;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

public class TaskShippingServiceDetails extends AsyncTask<String, Void, String> {

    Context context;
    TaskShippingServiceDetailsEvent taskShippingServiceDetailsEvent;
    ProgressDialog loading = null;
//    ArrayList<UPSServiceModel> uPSServiceList = new ArrayList<>();
//    ArrayList<USPSServiceModel> uSPSServiceList = new ArrayList<>();
//    ArrayList<FedExServiceModel> fedExServiceList = new ArrayList<>();
    ShippingInfo shippingInfo = null;


    public interface TaskShippingServiceDetailsEvent {
        void onShippingServiceDetailsResult(ShippingInfo shippingInfo);
    }

    public TaskShippingServiceDetails(TaskShippingServiceDetailsEvent taskShippingServiceDetailsEvent,Context context) {
        this.taskShippingServiceDetailsEvent = taskShippingServiceDetailsEvent;
        this.context = context;
    }

    @SuppressLint("ResourceType")
    @Override
    protected void onPreExecute() {
        super.onPreExecute();

            if (context != null) {
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
                NetworkUtil.doNetworkProcessGet(strings[0], responseStrBuilder);
                String response = responseStrBuilder.toString();
                Log.i("web service", "Response : " + response);
                ObjectMapper objectMapper = new ObjectMapper();
                shippingInfo = objectMapper.readValue(response, ShippingInfo.class);
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
        if (taskShippingServiceDetailsEvent != null && taskShippingServiceDetailsEvent != null)
            taskShippingServiceDetailsEvent.onShippingServiceDetailsResult(shippingInfo);
        if (loading != null && loading.isShowing()) {
            loading.dismiss();
        }
    }
}
