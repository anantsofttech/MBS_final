package com.aspl.task;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.aspl.Utils.NetworkUtil;
import com.aspl.mbs.R;
import com.aspl.mbsmodel.HomeItemModel;
import com.aspl.mbsmodel.ReOrderItemModel;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;

import org.json.JSONException;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

public class TaskOrderHistory extends AsyncTask<String, Void, String> {

    private List<ReOrderItemModel> orderHistoryList;
    TaskOrderHistoryEvent taskOrderHistoryEvent;
    Context context;
    String type;
    boolean showProgressloader;
    ProgressDialog loading = null;

    public interface TaskOrderHistoryEvent {
        void onGetOrderHistoryResult(List<ReOrderItemModel> orderHistoryList, String type);
    }

    public TaskOrderHistory(TaskOrderHistoryEvent taskOrderHistoryEvent, Context context, String type, boolean showProgressloader) {
        this.taskOrderHistoryEvent = taskOrderHistoryEvent;
        this.context = context;
        this.type = type;
        this.showProgressloader = showProgressloader;
    }

    @SuppressLint("ResourceType")
    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        if(showProgressloader){

            loading = new ProgressDialog(context, R.style.MyprogressDTheme);
            loading.setCancelable(false);
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

                TypeFactory typeFactory = objectMapper.getTypeFactory();
                CollectionType collectionType = typeFactory.constructCollectionType(
                        List.class, ReOrderItemModel.class);

                orderHistoryList = objectMapper.readValue(response, collectionType);

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
        taskOrderHistoryEvent.onGetOrderHistoryResult(orderHistoryList,type);

        if(loading != null && loading.isShowing()){
            loading.dismiss();
        }
    }
}
