package com.aspl.task;

import android.os.AsyncTask;
import android.speech.tts.Voice;
import android.util.Log;

import com.aspl.Utils.NetworkUtil;
import com.aspl.mbsmodel.PagesModel;
/*
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
*/

import org.json.JSONException;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mic on 11/29/2017.
 */

public class TaskPage extends AsyncTask<String,Void,String > {
    List<PagesModel> pagesModelList = new ArrayList<>();
    PagesModel pagesModel;

    @Override
    protected String doInBackground(String... strings) {
        int count = 0;
        boolean retry = false;
        StringBuilder responseStrBuilder = new StringBuilder();

        do {
          /*  retry = false;
            try {

                NetworkUtil.doNetworkProcessGet(strings[0], responseStrBuilder);
                String response = responseStrBuilder.toString();
                ObjectMapper objectMapper = new ObjectMapper();

                pagesModel = objectMapper.readValue(response, PagesModel.class);
                Log.d("Json", "doInBackground 1: " + pagesModel.getPageTitle());
                pagesModelList = objectMapper.readValue(response, new TypeReference<List<PagesModel>>() {
                });
                Log.d("Json", "doInBackground: " + pagesModelList.get(1).getPageTitle());
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

            count += 1;*/

        } while (count < 3 && retry);

        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }
}
