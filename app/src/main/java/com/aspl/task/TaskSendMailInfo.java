package com.aspl.task;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.aspl.Utils.NetworkUtil;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import org.json.JSONException;

import java.io.IOException;
import java.net.SocketTimeoutException;

public class TaskSendMailInfo extends AsyncTask<String, Void, String> {

    TaskSendMailInfoEvent taskSendMailInfoEvent;
    String successval;
    Context context;


    public interface TaskSendMailInfoEvent{
        void taskSendMailInfoResult(String successvalue);
    }

    public TaskSendMailInfo(TaskSendMailInfoEvent taskSendMailInfoEvent, Context context) {
        this.taskSendMailInfoEvent = taskSendMailInfoEvent;
        this.context = context;

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
                successval = response;
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

        if(taskSendMailInfoEvent != null){
            taskSendMailInfoEvent.taskSendMailInfoResult(successval);
        }
    }
}
