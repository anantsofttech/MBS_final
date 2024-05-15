package com.aspl.task;

import android.content.Context;
import android.os.AsyncTask;
import androidx.fragment.app.FragmentActivity;
import android.util.Log;

import com.aspl.Utils.NetworkUtil;
import com.aspl.fragment.PaymentFragment;
import com.aspl.mbsmodel.SignupLoyaltyInfo;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;

import java.io.IOException;
import java.net.SocketTimeoutException;

public class TaskSign_Up_For_Loyalty extends AsyncTask<String, Void, String> {
    Context context;
    SignupLoyaltyInfo signupLoyaltyInfo;
   TaskSign_Up_For_Loyalty_Event taskSign_up_for_loyalty_event;

    public interface TaskSign_Up_For_Loyalty_Event{
        void on_Sign_up_Loyalty_Info_Result(SignupLoyaltyInfo signupLoyaltyInfo);
    }

    public TaskSign_Up_For_Loyalty(FragmentActivity activity, PaymentFragment paymentFragment) {
        this.taskSign_up_for_loyalty_event = paymentFragment;
        this.context = activity;
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
                signupLoyaltyInfo = objectMapper.readValue(response, SignupLoyaltyInfo.class);

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
        if (signupLoyaltyInfo != null){
            if (taskSign_up_for_loyalty_event!=null)
                taskSign_up_for_loyalty_event.on_Sign_up_Loyalty_Info_Result(signupLoyaltyInfo);
        }else{
//            Toast.makeText(context, /*R.string.str_network_message */"It seems there is an issue with network connectivity. Please try again later.", Toast.LENGTH_SHORT).show();
        }

    }

}
