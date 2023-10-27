package com.aspl.task;

import android.os.AsyncTask;
import android.util.Log;

import com.aspl.Utils.NetworkUtil;
import com.aspl.mbsmodel.WishList;
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

public class TaskDeleteWishList extends AsyncTask<String, Void, String> {

    WishList wishList;
    TaskDeleteCartItemEvent myTaskDeleteCartItemEvent;
    String string;


    public interface TaskDeleteCartItemEvent {
        void onWishListResult(WishList wishList, String string);
    }

    public TaskDeleteWishList(TaskDeleteCartItemEvent myTaskDeleteCartItemEvent, String s) {
        this.myTaskDeleteCartItemEvent = myTaskDeleteCartItemEvent;
        this.string = s;
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
                wishList = objectMapper.readValue(response, WishList.class);
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
        //MainActivity.getInstance().onShoppingCardResult(Constant.liCardModel);

        /*WishListFragment.getInstance().onShoppingCardResult(Constant.liCardModel);*/

        //MainActivity.onUpdateCartItem(CardFragment.liShoppingCard);
        if (myTaskDeleteCartItemEvent != null) {
            myTaskDeleteCartItemEvent.onWishListResult(wishList, string);
        }
    }
}
