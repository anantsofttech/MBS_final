package com.aspl.task;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;

import com.aspl.Utils.NetworkUtil;
import com.aspl.mbs.R;
import com.aspl.mbsmodel.EditShippingData;
import com.aspl.mbsmodel.ShippingData;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.List;

public class TaskUpdateShippingAddress extends AsyncTask<String, Void, String> {

    ShippingData shippingData;
    Context context;
    ProgressDialog loading = null;
    String isFrom;
    ShippingData shippingDataModelPrev;
    EditShippingData editShippingDataPrev;

    UpdateShippingAddressEvent updateShippingAddressEvent;

    public TaskUpdateShippingAddress(FragmentActivity activity, View.OnClickListener onClickListener, View.OnClickListener clickListener, String editShipping, Object shippingDataModelPrev, EditShippingData editShippingData) {
    }


    public interface UpdateShippingAddressEvent {
        void onUpdateShippingResult(ShippingData shippingData, String isFrom, ShippingData shippingDataModelPrev,EditShippingData editShippingData);
    }

    public TaskUpdateShippingAddress(Context context, UpdateShippingAddressEvent updateShippingAddressEvent, String isFrom, ShippingData shippingDataModelPrev, EditShippingData editShippingDataPrev) {
        this.updateShippingAddressEvent = updateShippingAddressEvent;
        this.context = context;
        this.isFrom = isFrom;
        this.shippingDataModelPrev = shippingDataModelPrev;
        this.editShippingDataPrev = editShippingDataPrev;
    }

    @SuppressLint("ResourceType")
    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        loading = new ProgressDialog(context, R.style.MyprogressDTheme);
        loading.setCancelable(false);
        loading.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        loading.show();
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
                shippingData = objectMapper.readValue(response, ShippingData.class);
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
        if (updateShippingAddressEvent != null)
            updateShippingAddressEvent.onUpdateShippingResult(shippingData,isFrom,shippingDataModelPrev,editShippingDataPrev);

        if(loading != null && loading.isShowing()){
            loading.dismiss();
        }
    }
}
