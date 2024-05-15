package com.aspl.mbs;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by admin on 6/29/2018.
 */

public class BaseActivity extends AppCompatActivity {

    public static BaseActivity baseActivity;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        baseActivity = this;

//        //Edited by Janvi 3rd Dec ******
          Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            public void uncaughtException(Thread paramThread, Throwable paramThrowable) {
//                Log.e("Error"+Thread.currentThread().getStackTrace()[2],paramThrowable.getLocalizedMessage());
            }
        });
////        //end ***********
    }

    public static BaseActivity getInstance() {
        return baseActivity;
    }
}
