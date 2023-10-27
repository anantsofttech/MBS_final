package com.aspl.task;

import android.os.AsyncTask;
import android.util.Log;

import com.aspl.Utils.Constant;
import com.aspl.Utils.NetworkUtil;
import com.aspl.mbsmodel.DataHomePageBlockModel;
import com.aspl.ws.Async_getCommonService;
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

public class TaskHomePageBlockData extends AsyncTask<String , String , String > {

    private List<DataHomePageBlockModel> dataHomePageBlockModels;

    TaskHomePageBlockDataEvent taskHomePageBlockDataEvent ;


    public TaskHomePageBlockData(Async_getCommonService async_getCommonService) {

        this.taskHomePageBlockDataEvent= async_getCommonService;
    }

    public interface TaskHomePageBlockDataEvent {
        void onGetHomePageBlockDataResult(List<DataHomePageBlockModel> BlockDataFrontList);
    }

    @Override
    protected String doInBackground(String... strings) {
        Log.i("web service--Cart", "request HomePageData : " + strings[0]);
        int count = 0;
        boolean retry = false;
        StringBuilder responseStrBuilder = new StringBuilder();
        do {
            retry = false;
            try {
                NetworkUtil.doNetworkProcessGet(strings[0], responseStrBuilder);
                String response = responseStrBuilder.toString();
                Log.i("web service--Cart", "Response HomePageData : " + response);
                ObjectMapper objectMapper = new ObjectMapper();
                //cardModel = objectMapper.readValue(response, ShoppingCardModel.class);
                /*ZipList= objectMapper.readValue(response, new TypeReference<Zipmodel>() {
                });
*/
                TypeFactory typeFactory = objectMapper.getTypeFactory();
                CollectionType collectionType = typeFactory.constructCollectionType(
                        List.class, DataHomePageBlockModel.class);

                dataHomePageBlockModels=objectMapper.readValue(response, collectionType);

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

        Constant.DATAHOMEPAGEBLOCK.clear();
        Constant.DATAHOMEPAGEBLOCK=dataHomePageBlockModels;

    }
}
