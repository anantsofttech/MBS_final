package com.aspl.task;

import android.os.AsyncTask;
import android.util.Log;

import com.aspl.Utils.Constant;
import com.aspl.Utils.NetworkUtil;
import com.aspl.mbs.MainActivity;
import com.aspl.mbsmodel.ActiveStatusModel;
import com.aspl.mbsmodel.DataFrontModel;
import com.aspl.mbsmodel.DeleteAccountModel;
import com.aspl.mbsmodel.HomeItemModel;
import com.aspl.mbsmodel.ShoppingCardModel;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;

import org.json.JSONException;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.List;

public class TaskDeleteAccount extends AsyncTask<String, Void, String> {

    DeleteAccountModel deleteAccountModel;
    TaskDeleteAccountEvent taskDeleteAccountEvent;

    public TaskDeleteAccount(TaskDeleteAccountEvent TaskDeleteAccountEvent) {
        this.taskDeleteAccountEvent = TaskDeleteAccountEvent;
    }

    public interface TaskDeleteAccountEvent {
        void onGetDeleteAccountResult(DeleteAccountModel deleteAccountModel);

    }


    @Override
    protected String doInBackground(String... strings) {

        Log.i("web service--Delete", "request url : " + strings[0]);
        int count = 0;
        boolean retry = false;
        StringBuilder responseStrBuilder = new StringBuilder();
        do {
            retry = false;
            try {
                NetworkUtil.doNetworkProcessGet(strings[0], responseStrBuilder);
                String response = responseStrBuilder.toString();
                Log.i("web service Delete", "Response : " + response);
                ObjectMapper objectMapper = new ObjectMapper();
                deleteAccountModel = objectMapper.readValue(response, DeleteAccountModel.class);
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

        if (taskDeleteAccountEvent != null) {
            taskDeleteAccountEvent.onGetDeleteAccountResult(deleteAccountModel);
        }
    }

}
