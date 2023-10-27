package com.aspl.task;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import com.aspl.Utils.NetworkUtil;
import com.aspl.fragment.ItemDescriptionsFragment;
import com.aspl.mbsmodel.ItemDescModel;
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

public class TaskRecommandedItems extends AsyncTask<String, Void, String> {

    TaskRecommandedInterface taskRecommandedInterface;
    private List<ItemDescModel> recommandedItemList;


    public TaskRecommandedItems(TaskRecommandedInterface taskRecommandedInterface) {
        this.taskRecommandedInterface = taskRecommandedInterface;
    }

    public interface TaskRecommandedInterface {
        void recommandedItemsResult(List<ItemDescModel> itemDescModel);
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


                TypeFactory typeFactory = objectMapper.getTypeFactory();
                CollectionType collectionType = typeFactory.constructCollectionType(List.class, ItemDescModel.class);

                recommandedItemList = objectMapper.readValue(response, collectionType);
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

        if (taskRecommandedInterface != null) {
            taskRecommandedInterface.recommandedItemsResult(recommandedItemList);
        }
    }
}
