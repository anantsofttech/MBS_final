package com.aspl.task;

import android.os.AsyncTask;
import android.util.Log;

import com.aspl.Utils.Constant;
import com.aspl.Utils.NetworkUtil;
import com.aspl.mbsmodel.SessionToCard;
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

/**
 * Created by mic on 12/5/2017.
 */

public class TaskSessionToCart extends AsyncTask<String, Void, String> {

    /*private List<ShoppingCardModel> liCardModel;*/
    SessionToCard sessionToCard;
    //TaskSeesionToCardEvent myTaskSeesionToCardEvent;

    /*public interface TaskSeesionToCardEvent {
        void onSessionToCartResult(SessionToCard sessionToCard);
    }*/

    public TaskSessionToCart(){}

    /*public TaskSessionToCart(TaskSeesionToCardEvent myTaskSeesionToCardEvent) {
        Log.i("web service", "Response : " + response);
        this.myTaskSeesionToCardEvent = myTaskSeesionToCardEvent;
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
                Log.i("web service", "Response cart: " + response);
//                ObjectMapper objectMapper = new ObjectMapper();
//
//                sessionToCard = objectMapper.readValue(response, new TypeReference<List<ShoppingCardModel>>() {
//                });
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
        /*if (myTaskSeesionToCardEvent != null) {
            myTaskSeesionToCardEvent.onSessionToCartResult(sessionToCard);
        }*/
    }
}
