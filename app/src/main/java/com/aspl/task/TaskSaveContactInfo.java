package com.aspl.task;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.aspl.Utils.Constant;
import com.aspl.Utils.NetworkUtil;
import com.aspl.fragment.ContactUsFragment;
import com.aspl.mbs.MainActivity;
import com.aspl.mbs.R;
import com.aspl.mbsmodel.ContatInfo;
import com.aspl.mbsmodel.UpdateCartQuantity;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;

import java.io.IOException;
import java.net.SocketTimeoutException;

public class TaskSaveContactInfo extends AsyncTask<String, Void, String> {

    TaskSaveContactInfoEvent taskSaveContactInfoEvent;
    String successval;
    Context context;
    ProgressDialog loading = null;

    public interface TaskSaveContactInfoEvent{
        void taskSaveContactInfoResult(String successvalue);
    }

    @SuppressLint("ResourceType")
    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        loading = new ProgressDialog(context, R.style.MyprogressDTheme);
        loading.setCancelable(false);
//        loading.setMessage(Constant.Message.AuthenticatingUser);
        loading.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        loading.show();
    }


    public TaskSaveContactInfo(TaskSaveContactInfoEvent taskSaveContactInfoEvent, Context context) {
        this.taskSaveContactInfoEvent = taskSaveContactInfoEvent;
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

        if(taskSaveContactInfoEvent != null){
            taskSaveContactInfoEvent.taskSaveContactInfoResult(successval);
        }
        if(loading != null && loading.isShowing()) {
            loading.dismiss();
        }
    }
}
