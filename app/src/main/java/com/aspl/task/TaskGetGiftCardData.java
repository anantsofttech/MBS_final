package com.aspl.task;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import com.aspl.Utils.NetworkUtil;
import com.aspl.fragment.GiftCardFragment2;
import com.aspl.mbs.R;
import com.aspl.mbsmodel.GiftCardSettingsModel;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;

import java.io.IOException;
import java.net.SocketTimeoutException;

public class TaskGetGiftCardData extends AsyncTask<String, Void, GiftCardSettingsModel> {

    private Context context;
    private GiftCardSettingsModel giftCardSettingsModel;
    private ProgressDialog loading = null;
    private TaskGetGiftCardDataEvent taskGetGiftCardDataEvent;
    private static final int MAX_RETRIES = 3;
    View view;

    public TaskGetGiftCardData(Context context, GiftCardFragment2 giftCardFragment2, View view) {
        this.context = context;
        this.taskGetGiftCardDataEvent = giftCardFragment2;
        this.view = view;
    }

    public interface TaskGetGiftCardDataEvent {
        void onGiftCardDataReceived(GiftCardSettingsModel giftCardSettingsModel, View View );
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if(context != null) {
            loading = new ProgressDialog(context, R.style.MyprogressDTheme);
            loading.setCancelable(false);
            loading.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            loading.show();
        }
    }

    @SuppressLint("LongLogTag")
    @Override
    protected GiftCardSettingsModel doInBackground(String... strings) {
        Log.i("web service--Gift Card Data", "request url: " + strings[0]);
        StringBuilder responseStrBuilder = new StringBuilder();
        int count = 0;
        boolean success = false;

        while (count < MAX_RETRIES && !success) {
            try {
                NetworkUtil.doNetworkProcessGet(strings[0], responseStrBuilder);
                String response = responseStrBuilder.toString();
                Log.i("web service--Gift Card Data", "Response: " + response);

                ObjectMapper objectMapper = new ObjectMapper();
                giftCardSettingsModel = objectMapper.readValue(response, GiftCardSettingsModel.class);
                success = true;
            } catch (SocketTimeoutException e) {
                count++;
                Log.w("web service--Gift Card Data", "Timeout occurred, retrying... (" + count + "/" + MAX_RETRIES + ")");
            } catch (JSONException | IOException e) {
                Log.e("web service--Gift Card Data", "Error parsing response", e);
                return null;
            }
        }

        return giftCardSettingsModel;
    }

    @Override
    protected void onPostExecute(GiftCardSettingsModel result) {
        super.onPostExecute(result);
        if (loading != null && loading.isShowing()) {
            loading.dismiss();
        }

        if (taskGetGiftCardDataEvent != null) {
            taskGetGiftCardDataEvent.onGiftCardDataReceived(result,view);
        }
    }
}
