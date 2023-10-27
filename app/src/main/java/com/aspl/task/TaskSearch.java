package com.aspl.task;

import android.os.AsyncTask;
import android.util.Log;

import com.aspl.Utils.Constant;
import com.aspl.Utils.NetworkUtil;
import com.aspl.mbsmodel.GetSearchData;
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

public class TaskSearch extends AsyncTask<String, Void, String> {

    private List<GetSearchData> liSearchData;

    TaskSearchEvent myTaskSearchEvent;

    public interface TaskSearchEvent {
        void onSearchResult(List<GetSearchData> liSearchData);
    }

    //public TaskSearch(){}

    public TaskSearch(TaskSearchEvent myTaskSearchEvent) {
        this.myTaskSearchEvent = myTaskSearchEvent;
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
                Log.e("log", "Search Result  " + response);
                ObjectMapper objectMapper = new ObjectMapper();
                //cardModel = objectMapper.readValue(response, ShoppingCardModel.class);
                liSearchData = objectMapper.readValue(response, new TypeReference<List<GetSearchData>>() {
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
        if (myTaskSearchEvent != null && liSearchData != null) {
            myTaskSearchEvent.onSearchResult(liSearchData);
        }
    }
}
