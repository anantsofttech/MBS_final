package com.aspl.task;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.aspl.Utils.NetworkUtil;
import com.aspl.mbs.MainActivity;
import com.aspl.mbs.R;
import com.aspl.mbsmodel.ItemDescModel;
import com.aspl.mbsmodel.MultiPackModel;
import com.aspl.mbsmodel.WishList;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;

public class TaskItemDescription extends AsyncTask<String, Void, String> {

    ItemDescModel itemDescModel;
    TaskItemDescInterface itemDescInterface;
//    public static ProgressDialog progressBar;
    Context mcontext;
    ProgressDialog loading = null;
//    ArrayList<MultiPackModel> multi_pack = new ArrayList<MultiPackModel>();


    public TaskItemDescription(TaskItemDescInterface itemDescInterface, Context context) {
        this.itemDescInterface = itemDescInterface;
        mcontext = context;
    }

    public interface TaskItemDescInterface{
        void onItemDescResult(ItemDescModel itemDescModel);
    }

    @SuppressLint("ResourceType")
    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        if(mcontext != null) {
            loading = new ProgressDialog(mcontext, R.style.MyprogressDTheme);
            loading.setCancelable(false);
//        loading.setMessage(Constant.Message.AuthenticatingUser);
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
                itemDescModel = objectMapper.readValue(response, ItemDescModel.class);
                objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
                /*//liCardModel = objectMapper.readValue(response, new TypeReference<List<ShoppingCardModel>>() {
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
//
//    protected void onPreExecute() {
//        // pre execute logic
//        super.onPreExecute();
//        progressBar = new ProgressDialog(mcontext);
//        progressBar.setCancelable(true);
//        progressBar.setMessage("Please wait...");
//
//        // start progressbar
//    }


    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        if(itemDescInterface != null){
            itemDescInterface.onItemDescResult(itemDescModel);
        }
//
        if(loading != null && loading.isShowing()) {
            loading.dismiss();
        }
//        progressBar.dismiss();
    }
}
