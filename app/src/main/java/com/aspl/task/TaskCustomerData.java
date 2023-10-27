package com.aspl.task;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.aspl.Utils.NetworkUtil;
import com.aspl.mbs.R;
import com.aspl.mbsmodel.ShippingData;
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

public class TaskCustomerData extends AsyncTask<String, Void, String> {

    //List<ShippingData> liShippingData;
    ShippingData getShippingData;
    ProgressDialog loading = null;
    Context context;

    TaskCustomerEvent myTaskCustomerEvent;
    boolean isShowLoader = false;
    boolean isFromfavouriteStore = false;

    public TaskCustomerData(Context context, TaskCustomerEvent myTaskCustomerEvent, Boolean isShowLoader) {
        this.myTaskCustomerEvent = myTaskCustomerEvent;
        this.context = context;
        this.isShowLoader = isShowLoader;
    }

    public TaskCustomerData(Context context, TaskCustomerEvent myTaskCustomerEvent, boolean isShowLoader, boolean isFromfavouriteStore) {
        this.myTaskCustomerEvent = myTaskCustomerEvent;
        this.context = context;
        this.isShowLoader = isShowLoader;
        this.isFromfavouriteStore = isFromfavouriteStore;
    }

    public interface TaskCustomerEvent{
        void onTaskCustomerResult(/*List<*/ShippingData/*>*/ liShippingData, boolean isFromfavouriteStore);
    }

    public TaskCustomerData(Context context, TaskCustomerEvent myTaskCustomerEvent){
        this.myTaskCustomerEvent = myTaskCustomerEvent;
        this.context = context;
    }

    @SuppressLint("ResourceType")
    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        if(isShowLoader) {
            if(context != null) {
                loading = new ProgressDialog(context, R.style.MyprogressDTheme);
                loading.setCancelable(false);
//        loading.setMessage(Constant.Message.AuthenticatingUser);
                loading.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                loading.show();
            }
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
/*
                JSONArray jarr=new JSONArray(response);
                jarr.getJSONObject(0);
*/
                Log.i("web service", "Response : " + response);
                ObjectMapper objectMapper = new ObjectMapper();
                getShippingData = objectMapper.readValue(response, ShippingData.class);
                /*liShippingData = objectMapper.readValue(response, new TypeReference<List<ShippingData>>() {
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
        if (myTaskCustomerEvent!=null && getShippingData != null)
            myTaskCustomerEvent.onTaskCustomerResult(getShippingData,isFromfavouriteStore);

        if(loading != null && loading.isShowing()){
            loading.dismiss();
        }

    }
}
