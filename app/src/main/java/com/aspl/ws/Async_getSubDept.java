package com.aspl.ws;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.aspl.Utils.Constant;
import com.aspl.Utils.NetworkUtil;
import com.aspl.mbs.R;
import com.aspl.mbsmodel.ContatInfo;
import com.aspl.mbsmodel.CustomerCard;
import com.aspl.mbsmodel.HomeItemModel;
import com.aspl.mbsmodel.SubDepartmentModel;
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

public class Async_getSubDept extends AsyncTask<String, Void, String> {

    public ProgressDialog progressBar;
    String strURL;
    SubDepartmentModel subDepartmentModel;
    public Context context;
    ProgressDialog loading = null;
//    List<Async_getSubDept> subDeptList;

    public Async_getSubDept(Context context, String url) {
        this.context = context;
        strURL = url;
    }

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
                Log.e("log", "Response  " + response);
                ObjectMapper objectMapper = new ObjectMapper();

                TypeFactory typeFactory = objectMapper.getTypeFactory();
                CollectionType collectionType = typeFactory.constructCollectionType(
                        List.class, HomeItemModel.class);

                Constant.subDeptList=objectMapper.readValue(response, collectionType);

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
        if(loading != null && loading.isShowing()) {
            loading.dismiss();
        }
    }
}
