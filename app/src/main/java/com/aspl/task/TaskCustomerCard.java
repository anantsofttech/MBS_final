package com.aspl.task;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.aspl.Utils.Constant;
import com.aspl.Utils.NetworkUtil;
import com.aspl.fragment.CardOnFileFragment;
import com.aspl.fragment.PaymentFragment;
import com.aspl.mbs.R;
import com.aspl.mbsmodel.CustomerCard;
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
 * Created by admin on 1/10/2018.
 */

public class TaskCustomerCard extends AsyncTask<String, Void, String> {

    List<CustomerCard> liCustomerCard;
    //ShippingData getShippingData;
    String response = "";
    ProgressDialog loading = null;
    Context context;
    boolean isFromCardOnFile = false;

    TaskCustomerCardEvent myTaskCustomerCardEvent;
    public interface TaskCustomerCardEvent{
        void customerCardResult(List<CustomerCard> liCustomerCard);
    }

    public TaskCustomerCard(Context context, TaskCustomerCardEvent myTaskCustomerCardEvent, boolean isFromCardOnFile){
        this.myTaskCustomerCardEvent = myTaskCustomerCardEvent;
        this.context = context;
        this.isFromCardOnFile = isFromCardOnFile;
    }

    @SuppressLint("ResourceType")
    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        if(isFromCardOnFile){
            loading = new ProgressDialog(context, R.style.MyprogressDTheme);
            loading.setCancelable(false);
//        loading.setMessage(Constant.Message.AuthenticatingUser);
            loading.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            loading.show();
        }else{

        }

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
                liCustomerCard = objectMapper.readValue(response, new TypeReference<List<CustomerCard>>() {
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
        if (liCustomerCard != null){
            if (myTaskCustomerCardEvent!=null) {

                // for USAePay
                if(Constant.isUSAePAY){
                    if(liCustomerCard.get(0).getCardNumber().trim().equalsIgnoreCase("null")||liCustomerCard.get(0).getCardNumber().trim().isEmpty()) {
                        if(isFromCardOnFile){
                            CardOnFileFragment.getInstance().showCardText();
                        }else{
                            PaymentFragment.getInstance().showCardResponseDialog();
                        }
                    }else{
                        myTaskCustomerCardEvent.customerCardResult(liCustomerCard);
                    }

                }else{
                    if(liCustomerCard.get(0).getRESPONSETEXT().trim().equalsIgnoreCase("null")||liCustomerCard.get(0).getRESPONSETEXT().trim().isEmpty()) {
                        if(isFromCardOnFile){
                            CardOnFileFragment.getInstance().showCardText();
                        }else{
                            PaymentFragment.getInstance().showCardResponseDialog();
                        }
                    }else{
                        myTaskCustomerCardEvent.customerCardResult(liCustomerCard);
                    }
                }
            }
        }else{
            if(isFromCardOnFile) {
                CardOnFileFragment.getInstance().showCardText();
            }else{
                PaymentFragment.getInstance().showCardResponseDialog();
            }
            // Toast.makeText(MainActivity.getInstance(), "It seems to network is not available.", Toast.LENGTH_SHORT).show();
        }

        if(isFromCardOnFile){
            if(loading != null){
                loading.dismiss();
            }
        }

    }
}
