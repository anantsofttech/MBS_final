package com.aspl.task;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.aspl.Utils.NetworkUtil;
import com.aspl.fragment.GiftCardFragment;
import com.aspl.mbs.R;
import com.aspl.mbsmodel.AboutusModel;
import com.aspl.mbsmodel.CheckGiftCardBalanceModel;
import com.aspl.mbsmodel.GiftcardModel;
import com.aspl.mbsmodel.HomeItemModel;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;

import org.json.JSONException;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.List;

public class TaskGiftcard extends AsyncTask<String, Void, String> {

    private List<GiftcardModel> GiftCardList;
    CheckGiftCardBalanceModel checkGiftCardBalanceModel;

    TaskGiftcardEvent taskGiftcardEvent;
    Context context;
    ProgressDialog loading = null;

    public TaskGiftcard(TaskGiftcardEvent taskGiftcardEvent, Context context) {
        this.taskGiftcardEvent = taskGiftcardEvent;
        this.context = context;
    }

    @SuppressLint("ResourceType")
    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        if(context != null) {
            loading = new ProgressDialog(context, R.style.MyprogressDTheme);
            loading.setCancelable(false);
//        loading.setMessage(Constant.Message.AuthenticatingUser);
            loading.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            loading.show();
        }
    }

    public interface TaskGiftcardEvent {
        void onGetGiftcardResult(List<GiftcardModel> GiftCardList);

        void onGetGiftcardCheckBalResult(CheckGiftCardBalanceModel checkGiftCardBalanceModel);
    }

    @Override
    protected String doInBackground(String... strings) {
        Log.i("web service--Cart", "request url: " + strings[0]);
        int count = 0;
        boolean retry = false;
        StringBuilder responseStrBuilder = new StringBuilder();
        do {
            retry = false;
            try {

                if(strings[0].contains("CheckGiftCardBalance")){

                    NetworkUtil.doNetworkProcessGet(strings[0], responseStrBuilder);
                    String response = responseStrBuilder.toString();
                    Log.i("web service--reward", "Response : " + response);
                    ObjectMapper objectMapper = new ObjectMapper();
                    checkGiftCardBalanceModel = objectMapper.readValue(response, CheckGiftCardBalanceModel.class);

                    return response;

                }else{
                    NetworkUtil.doNetworkProcessGet(strings[0], responseStrBuilder);
                    String response = responseStrBuilder.toString();
                    Log.i("web service--Cart", "Response : " + response);
                    ObjectMapper objectMapper = new ObjectMapper();
                    TypeFactory typeFactory = objectMapper.getTypeFactory();
                    CollectionType collectionType = typeFactory.constructCollectionType(
                            List.class, GiftcardModel.class);

                    GiftCardList = objectMapper.readValue(response, collectionType);

                    return response;
                }


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

        if (taskGiftcardEvent != null) {
            taskGiftcardEvent.onGetGiftcardResult(GiftCardList);
        }

        if (taskGiftcardEvent != null && checkGiftCardBalanceModel != null) {
            taskGiftcardEvent.onGetGiftcardCheckBalResult(checkGiftCardBalanceModel);
        }

        if(loading != null && loading.isShowing()){
            loading.dismiss();
        }
    }
}
