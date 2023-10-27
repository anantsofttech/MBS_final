package com.aspl.task;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.aspl.Utils.Constant;
import com.aspl.Utils.NetworkUtil;
import com.aspl.fragment.CardFragment;
import com.aspl.mbs.MainActivity;
import com.aspl.mbsmodel.ShoppingCardModel;
import com.aspl.mbsmodel.TwentyOneYear;
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

public class TaskTwentyOneYear extends AsyncTask<String, Void, String> {

    //private List<ShoppingCardModel> liCardModel;

   /* private TwentyOneYear twentyOneYear;*/

    private TaskTwentyOneYearEvent myTaskTwentyOneYearEvent;

    public interface TaskTwentyOneYearEvent {
        void onTwentyOneYearResult(TwentyOneYear twentyOneYear);
    }

    public TaskTwentyOneYear(TaskTwentyOneYearEvent myTaskTwentyOneYearEvent) {
        this.myTaskTwentyOneYearEvent = myTaskTwentyOneYearEvent;
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
                Log.i("web service", "GL Response : " + response);
                ObjectMapper objectMapper = new ObjectMapper();
                //cardModel = objectMapper.readValue(response, ShoppingCardModel.class);
                Constant.twentyOneYear = objectMapper.readValue(response, TwentyOneYear.class);
                //twentyOneYear = objectMapper.readValue(response, TwentyOneYear.class);
                /*liCardModel = objectMapper.readValue(response, new TypeReference<List<ShoppingCardModel>>() {
                });*/
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
        if (myTaskTwentyOneYearEvent != null) {
            myTaskTwentyOneYearEvent.onTwentyOneYearResult(Constant.twentyOneYear);
        }/*else {
            MainActivity.onTwentyOneYearResult(twentyOneYear);
        }*/

/*        if (twentyOneYear.getCustAgeValidOption() ==1){
            Toast.makeText(MainActivity.getInstance(), "Dialog", Toast.LENGTH_SHORT).show();
        }else if (twentyOneYear.getCustAgeValidOption() == 2){
            new CardFragment(twentyOneYear);
            MainActivity.onShoppingCardResults(twentyOneYear);
            //CardFragment.llCheckBox.setVisibility(View.VISIBLE);
            //CardFragment.vCheckBox.setVisibility(View.VISIBLE);
        }else{
            CardFragment.llCheckBox.setVisibility(View.GONE);
            CardFragment.vCheckBox.setVisibility(View.GONE);
        }*/
    }
}
