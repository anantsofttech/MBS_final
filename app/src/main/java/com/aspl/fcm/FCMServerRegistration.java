package com.aspl.fcm;

import android.app.Activity;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;

import com.aspl.Utils.Constant;
import com.aspl.Utils.DeviceInfo;
import com.aspl.mbs.MainActivity;
import com.aspl.mbs.SplaceScreen;
import com.aspl.mbsmodel.UserModel;
import com.aspl.task.TaskFCMTokenRegister;
import com.google.firebase.iid.FirebaseInstanceId;

public class FCMServerRegistration {


    public static void onFCMTokenRegistration(Activity activity, String customerId) {
        Log.d("fcm", "fcm token : " + FirebaseInstanceId.getInstance().getToken());
        String fcmToken = "";
        fcmToken = FirebaseInstanceId.getInstance().getToken();

        if (fcmToken != null && !fcmToken.isEmpty()) {
            //http://192.168.172.211:888/Home/InsertDeviceDetails?storeno=707&DeviceID=etre&&TokenNo=0007&customerid=123
            String url = "";
            if (customerId == null || customerId.isEmpty()) {
                url = Constant.URL + Constant.INSERT_FCM_TOKEN + Constant.STOREID
                        + "&DeviceID=" + DeviceInfo.getDeviceId(activity)
                        + "&TokenNo=" + fcmToken;
            } else {
                url = Constant.URL + Constant.INSERT_FCM_TOKEN + Constant.STOREID
                        + "&DeviceID=" + DeviceInfo.getDeviceId(activity)
                        + "&TokenNo=" + fcmToken
                        + "&customerid=" + UserModel.Cust_mst_ID;
            }

            TaskFCMTokenRegister tokenRegister;
            if (activity == MainActivity.getInstance()){
                tokenRegister = new TaskFCMTokenRegister(MainActivity.getInstance());
            }else if (activity == SplaceScreen.getInstance()){
                tokenRegister = new TaskFCMTokenRegister(SplaceScreen.getInstance());
            }else{
                tokenRegister = new TaskFCMTokenRegister();
            }
            tokenRegister.execute(url);
        }
    }
}
