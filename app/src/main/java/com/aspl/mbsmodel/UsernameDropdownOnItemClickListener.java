package com.aspl.mbsmodel;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.aspl.fragment.Login;
import com.aspl.mbs.MainActivity;

public class UsernameDropdownOnItemClickListener implements AdapterView.OnItemClickListener {

    String TAG = "UsernameDropdownOnItemClickListener.java";

    @Override
    public void onItemClick(AdapterView<?> arg0, View v, int arg2, long arg3) {

        // get the context and main activity to access variables
//        Context mContext = v.getContext();
//        MainActivity mainActivity = ((MainActivity) mContext);
//
//        // add some animation when a list item was clicked
//        Animation fadeInAnimation = AnimationUtils.loadAnimation(v.getContext(), android.R.anim.fade_in);
//        fadeInAnimation.setDuration(10);
//        v.startAnimation(fadeInAnimation);
//
//        // dismiss the pop up
////        mainActivity.popupWindow.dismiss();
//
//        // get the text and set it as the button text
//        String selectedItemText = ((TextView) v).getText().toString();
//        Login.sign_edtEmailID.setText(selectedItemText);

        // get the id
//        String selectedItemTag = ((TextView) v).getTag().toString();
//        Toast.makeText(mContext, "username is: " + selectedItemTag, Toast.LENGTH_SHORT).show();

    }

}
