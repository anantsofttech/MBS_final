package com.aspl.task;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.aspl.Utils.NetworkUtil;
import com.aspl.mbsmodel.ReOrderItemModel;
import com.aspl.mbsmodel.ReOrderModel;
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

/**
 * Created by mic on 12/5/2017.
 */

public class TaskReOrder extends AsyncTask<String, Void, String> {

    /*private List<ShoppingCardModel> liCardModel;*/

    TaskReOrderEvent taskReOrderEvent;
    ReOrderModel model;
    String orderid;
    private List<ReOrderItemModel> reorderList;
    Context context;
    String fromwhere;
    String requestdQty;

    public TaskReOrder(Context context, TaskReOrderEvent taskReOrderEvent, String fromwhere, String requestdQty) {
        this.context = context;
        this.taskReOrderEvent = taskReOrderEvent;
        this.fromwhere = fromwhere;
        this.requestdQty = requestdQty;
    }

    public TaskReOrder(TaskReOrderEvent taskReOrderEvent, String orderid) {
        this.taskReOrderEvent = taskReOrderEvent;
        this.orderid = orderid;
    }

    public interface TaskReOrderEvent {
        void onReOrderResult(ReOrderModel model, String fromwhere, String requestdQty);

        void onReorderListResult(List<ReOrderItemModel> reorderList,String orderid);
    }


    @Override
    protected String doInBackground(String... strings) {

        if(strings[0].contains("BuyItAgain")){

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
                    //cardModel = objectMapper.readValue(response, ShoppingCardModel.class);
                    model= objectMapper.readValue(response, new TypeReference<ReOrderModel>() {
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

        }else if(strings[0].contains("reorderdetails")){

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
                    //cardModel = objectMapper.readValue(response, ShoppingCardModel.class);

                    TypeFactory typeFactory = objectMapper.getTypeFactory();
                    CollectionType collectionType = typeFactory.constructCollectionType(
                            List.class, ReOrderItemModel.class);

                    reorderList = objectMapper.readValue(response, collectionType);

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

        }
//        else if(strings[0].contains("ReOrder")){
//
//            Log.i("web service--Cart", "request url : " + strings[0]);
//            int count = 0;
//            boolean retry = false;
//            StringBuilder responseStrBuilder = new StringBuilder();
//            do {
//                retry = false;
//                try {
//                    NetworkUtil.doNetworkProcessGet(strings[0], responseStrBuilder);
//                    String response = responseStrBuilder.toString();
//                    Log.i("web service--Cart", "Response : " + response);
//                    ObjectMapper objectMapper = new ObjectMapper();
//                    //cardModel = objectMapper.readValue(response, ShoppingCardModel.class);
//                    model= objectMapper.readValue(response, new TypeReference<ReOrderModel>() {
//                    });
//                    return response;
//
//                } catch (JsonParseException e) {
//                    e.printStackTrace();
//                } catch (JsonGenerationException e) {
//                    e.printStackTrace();
//                } catch (SocketTimeoutException e) {
//                    e.printStackTrace();
//                } catch (JsonMappingException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                retry = true;
//                count += 1;
//            } while (count < 3 && retry);
//
//        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        /*if (Constant.liCardModel != null){
            MainActivity.onUpdateCartItem(Constant.liCardModel);
        }*/
        if (taskReOrderEvent != null && model != null) {
            taskReOrderEvent.onReOrderResult(model,fromwhere,requestdQty);
        }

        if(taskReOrderEvent != null && reorderList != null){
            taskReOrderEvent.onReorderListResult(reorderList, orderid);
        }
    }
}
