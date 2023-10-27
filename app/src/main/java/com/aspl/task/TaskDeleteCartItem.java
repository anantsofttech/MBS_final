package com.aspl.task;

import android.os.AsyncTask;
import android.util.Log;

import com.aspl.Utils.Constant;
import com.aspl.Utils.NetworkUtil;
import com.aspl.mbs.MainActivity;
import com.aspl.mbs.MainActivityDup;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;

import java.io.IOException;
import java.net.SocketTimeoutException;

/**
 * Created by mic on 12/5/2017.
 */

public class TaskDeleteCartItem extends AsyncTask<String, Void, String> {


    /*TaskDeleteCartItemEvent myTaskDeleteCartItemEvent;
    public interface TaskDeleteCartItemEvent {
        void onShoppingCardResult(List<ShoppingCardModel> liShoppingCard);
    }*/

    public TaskDeleteCartItem(){}

    /*public TaskDeleteCartItem(TaskDeleteCartItemEvent myTaskDeleteCartItemEvent) {
        Log.i("web service", "Response : " + response);
        this.myTaskDeleteCartItemEvent = myTaskDeleteCartItemEvent;
    }*/

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

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        if(Constant.SCREEN_LAYOUT==1){
            MainActivity.getInstance().onShoppingCardResult(Constant.liCardModel, s);
        }else if(Constant.SCREEN_LAYOUT==2) {
            MainActivityDup.getInstance().onShoppingCardResult(Constant.liCardModel, s);
        }
        //CardFragment.getInstance().onShoppingCardResult(Constant.liCardModel);
        //MainActivity.onUpdateCartItem(CardFragment.liShoppingCard);
        /*if (myTaskDeleteCartItemEvent != null) {
            myTaskDeleteCartItemEvent.onShoppingCardResult(liCardModel);
        }*/
    }
}
