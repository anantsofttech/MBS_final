package com.aspl.task;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;

import com.aspl.Utils.Constant;
import com.aspl.Utils.NetworkUtil;
import com.aspl.mbsmodel.DataFrontModel;
import com.aspl.mbsmodel.DataFrontModel;
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

/**
 * Created by mic on 12/5/2017.
 */

public class TaskBlockDataFront extends AsyncTask<String, Void, String> {

    private List<DataFrontModel> BlockDataFrontList;

    TaskBlockDataFrontEvent homeItemEvent;
    //Zipmodel model;
    public TaskBlockDataFront(TaskBlockDataFrontEvent homeItemEvent) {
        this.homeItemEvent = homeItemEvent;
    }

    public interface TaskBlockDataFrontEvent {
        void onGetBlockDataFrontResult(List<DataFrontModel> BlockDataFrontList);
    }

    @Override
    protected String doInBackground(String... strings) {

        Log.i("web service--Cart", "request url : " + strings[0]);
        int count = 0;
        boolean retry = false;
        StringBuilder responseStrBuilder = new StringBuilder();
        do {
            retry = false;
            try {
                NetworkUtil.doNetworkProcessGet(strings[0], responseStrBuilder);
                String response = responseStrBuilder.toString();
                Log.i("web service--Cart", "Response : " + response);
                ObjectMapper objectMapper = new ObjectMapper();
                TypeFactory typeFactory = objectMapper.getTypeFactory();
                CollectionType collectionType = typeFactory.constructCollectionType(
                        List.class, DataFrontModel.class);

                BlockDataFrontList=objectMapper.readValue(response, collectionType);

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
        /*if (Constant.liCardModel != null){
            MainActivity.onUpdateCartItem(Constant.liCardModel);
        }*/
        Constant.BLOCKDATAFRONTLIST.clear();
        Constant.BLOCKDATAFRONTLIST=BlockDataFrontList;
        /*if (homeItemEvent != null) {
            homeItemEvent.onGetBlockDataFrontResult(BlockDataFrontList);
        }*/
    }
}
