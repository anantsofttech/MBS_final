package com.aspl.task;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.aspl.Utils.Constant;
import com.aspl.Utils.NetworkUtil;
import com.aspl.mbs.R;
import com.aspl.mbsmodel.StoreLocationModel;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.List;

public class TaskStoreLocationInfo extends AsyncTask<String, Void, String> {

    public TaskStoreLocationEvent taskStoreLocationEvent;
    List<StoreLocationModel> storeLocationList;
    public Context context;
    ProgressDialog loading = null;
    Boolean isSearchLocation = false;

    public TaskStoreLocationInfo(TaskStoreLocationEvent taskStoreLocationEvent, Context context, boolean isSearchLocation) {
        this.context = context;
        this.taskStoreLocationEvent = taskStoreLocationEvent;
        this.isSearchLocation = isSearchLocation;
    }

    @SuppressLint("ResourceType")
    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        if (!Constant.isFromChangeLocDialog) {
            loading = new ProgressDialog(context, R.style.MyprogressDTheme);
            loading.setCancelable(false);
            loading.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            loading.show();
        }
    }

    public interface TaskStoreLocationEvent {
        void storeLocationInfoResult(List<StoreLocationModel> storeLocationList, Boolean isSearchLocation);
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
                Log.e("log", "Response  " + response);
                ObjectMapper objectMapper = new ObjectMapper();
                storeLocationList = objectMapper.readValue(response, new TypeReference<List<StoreLocationModel>>() {
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

        if (!Constant.isFromChangeLocDialog) {
            if (loading != null && loading.isShowing()) {
                loading.dismiss();
            }
        }

        if(taskStoreLocationEvent != null){
            taskStoreLocationEvent.storeLocationInfoResult(storeLocationList,isSearchLocation);
        }
    }
}
