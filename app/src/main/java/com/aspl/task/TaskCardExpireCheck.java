package com.aspl.task;

import android.os.AsyncTask;
import android.util.Log;

import com.aspl.Utils.NetworkUtil;
import com.aspl.fragment.PaymentFragment;
import com.aspl.mbsmodel.CreditCardExpiryModel;
import com.aspl.mbsmodel.CustomerCard;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.List;

/**
 * Created by admin on 1/10/2018.
 */

public class TaskCardExpireCheck extends AsyncTask<String, Void, String> {

    List<CreditCardExpiryModel> LiExpireCard;
    //ShippingData getShippingData;
    String response = "";

    TaskCardExpiryCheckEvent myTaskCardExpiryCheckEvent;
    public interface TaskCardExpiryCheckEvent{
        void customerCardExpireResult(List<CreditCardExpiryModel> LiExpireCard);
    }

    public TaskCardExpireCheck(TaskCardExpiryCheckEvent myTaskCardExpiryCheckEvent){
        this.myTaskCardExpiryCheckEvent = myTaskCardExpiryCheckEvent;
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
                NetworkUtil.doNetworkProcessGet_WithTimeout(strings[0], responseStrBuilder);
                response = responseStrBuilder.toString();
                Log.e("Log","TimeOut Resp="+response);
                /** This delayed here because if payware not respond with 5 second so stop this task and show the dialog **/
               /* Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (response == null ||  response.isEmpty() ){
                            //PaymentFragment.getInstance().customerCard.cancel(true);

                        }
                    }
                }, 1000);*/

                Log.i("web service", "response : " + response);
                ObjectMapper objectMapper = new ObjectMapper();
                //getShippingData = objectMapper.readValue(response, ShippingData.class);
                LiExpireCard = objectMapper.readValue(response, new TypeReference<List<CreditCardExpiryModel>>() {
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
            } catch (FileNotFoundException e){
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
        if (LiExpireCard != null){
            if (myTaskCardExpiryCheckEvent!=null) {
                /*if(LiExpireCard.get(0).getRESPONSETEXT().trim().equalsIgnoreCase("null")||LiExpireCard.get(0).getRESPONSETEXT().trim().isEmpty()) {
                    PaymentFragment.getInstance().showCardResponseDialog();
                }else{
                    myTaskCardExpiryCheckEvent.customerCardExpireResult(LiExpireCard);

                }*/
                myTaskCardExpiryCheckEvent.customerCardExpireResult(LiExpireCard);
            }
        }else{
            PaymentFragment.getInstance().showCardResponseDialog();
            // Toast.makeText(MainActivity.getInstance(), "It seems to network is not available.", Toast.LENGTH_SHORT).show();
        }
    }
}
