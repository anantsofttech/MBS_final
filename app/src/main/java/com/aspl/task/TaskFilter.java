package com.aspl.task;

import android.os.AsyncTask;
import android.util.Log;

import com.aspl.Utils.Constant;
import com.aspl.Utils.NetworkUtil;
import com.aspl.mbsmodel.Filter;
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

public class TaskFilter extends AsyncTask<String, Void, String> {

    private List<Filter> liFilter;

    TaskFilterEvent myTaskFilterEvent;

    public interface TaskFilterEvent {
        void onFilterResult(List<Filter> liFilter);
    }

    /*public TaskFilter(){}*/

    public TaskFilter(TaskFilterEvent myTaskFilterEvent) {
        this.myTaskFilterEvent = myTaskFilterEvent;
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
                //cardModel = objectMapper.readValue(response, ShoppingCardModel.class);
                liFilter = objectMapper.readValue(response, new TypeReference<List<Filter>>() {
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
        if (myTaskFilterEvent != null && liFilter != null) {
            myTaskFilterEvent.onFilterResult(liFilter);
        }
    }
}
