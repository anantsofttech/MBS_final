package com.aspl.task;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.aspl.Utils.NetworkUtil;
import com.aspl.mbsmodel.AboutusModel;
import com.aspl.mbsmodel.SiteInfoModel;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;

import java.io.IOException;
import java.net.SocketTimeoutException;

public class TaskDomain extends AsyncTask<String, Void, String> {

    TaskDomainEvent taskDomainEvent;
    Context context;
    SiteInfoModel siteInfoModel;

    public interface TaskDomainEvent{
        void onDomainResult(SiteInfoModel siteInfoModel);
    }

    public TaskDomain(TaskDomainEvent taskDomainEvent, Context context) {
        this.taskDomainEvent = taskDomainEvent;
        this.context = context;
    }


    @Override
    protected String doInBackground(String... strings) {
        Log.i("web service--reward", "request url : " + strings[0]);
        int count = 0;
        boolean retry = false;
        StringBuilder responseStrBuilder = new StringBuilder();
        do {
            retry = false;
            try {
                NetworkUtil.doNetworkProcessGet(strings[0], responseStrBuilder);
                String response = responseStrBuilder.toString();
                Log.i("web service--reward", "Response : " + response);
                ObjectMapper objectMapper = new ObjectMapper();
                siteInfoModel = objectMapper.readValue(response, SiteInfoModel.class);

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

        if(taskDomainEvent != null){
            taskDomainEvent.onDomainResult(siteInfoModel);
        }

    }

}
