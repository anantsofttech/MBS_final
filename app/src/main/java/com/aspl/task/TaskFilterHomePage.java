package com.aspl.task;

import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.aspl.Utils.NetworkUtil;
import com.aspl.fragment.ViewAllFragment;
import com.aspl.mbsmodel.Filter;
import com.aspl.mbsmodel.FilterHomePage;
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

public class TaskFilterHomePage extends AsyncTask<String, Void, String> {

    private List<FilterHomePage> liFilterHomePages;

    TaskFilterHomePageEvent myTaskFilterHomePageEvent;

    public interface TaskFilterHomePageEvent {
        void onFilterHomePageResult(List<FilterHomePage> liFilterHomePages);
    }

    /*public TaskFilter(){}*/

    public TaskFilterHomePage(TaskFilterHomePageEvent myTaskFilterHomePageEvent) {
        this.myTaskFilterHomePageEvent = myTaskFilterHomePageEvent;
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

                liFilterHomePages = objectMapper.readValue(response, new TypeReference<List<FilterHomePage>>() {
                });

                //uncomment below if condition one line only to filter onclick - spare
                if(liFilterHomePages != null && liFilterHomePages.size() > 0){
                    if (!liFilterHomePages.get(0).getBlockDisplaytext().contains("All Items")){
                        FilterHomePage fhp=new FilterHomePage();
                        fhp.setBlockDisplaytext("All Items");
                        fhp.setBlockActualtext("allItem");
                        liFilterHomePages.add(0,fhp);
                    }
                }

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
        if (myTaskFilterHomePageEvent != null && liFilterHomePages != null) {
            myTaskFilterHomePageEvent.onFilterHomePageResult(liFilterHomePages);
            /*FilterHomePage fhp = new FilterHomePage();
            fhp.setBlockActualtext("All Item");
            liFilterHomePages.add(0, fhp);*/
        }
    }
}
