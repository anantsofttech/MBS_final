package com.aspl.task;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.aspl.Utils.Constant;
import com.aspl.Utils.NetworkUtil;
import com.aspl.mbs.SplaceScreen;
import com.aspl.mbsmodel.DatabaseInfo;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;

import java.io.IOException;
import java.net.SocketTimeoutException;


public class TaskDataBaseDetail extends AsyncTask<String, Void, String> {

    Context context;
    public TaskDataBaseDetailEvent taskDataBaseDetailEvent;

    public TaskDataBaseDetail(Context context, TaskDataBaseDetailEvent taskDataBaseDetailEvent) {
        this.context=context;
        this.taskDataBaseDetailEvent = taskDataBaseDetailEvent;
    }


    public interface TaskDataBaseDetailEvent {
        void DatabaseInfoEventResult(DatabaseInfo databaseInfo);
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
                Log.e("log", "Response  " + response);
                ObjectMapper objectMapper = new ObjectMapper();
                Constant.DatabaseInfo = objectMapper.readValue(response, DatabaseInfo.class);
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

        if(taskDataBaseDetailEvent != null && Constant.DatabaseInfo != null){
            taskDataBaseDetailEvent.DatabaseInfoEventResult(Constant.DatabaseInfo);
        }
    }

}
