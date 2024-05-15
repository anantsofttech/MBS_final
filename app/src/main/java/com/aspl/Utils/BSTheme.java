package com.aspl.Utils;

import android.content.res.ColorStateList;
import androidx.core.widget.CompoundButtonCompat;
import android.widget.CheckBox;
import android.widget.RadioButton;

/**
 * Created by new on 11/03/2017.
 */
public class BSTheme {


    public static  void setCheckBoxColor(CheckBox checkBox, int checkedColor, int uncheckedColor) {
        int states[][] = {{android.R.attr.state_checked}, {}};
        int colors[] = {checkedColor, uncheckedColor};
        CompoundButtonCompat.setButtonTintList(checkBox, new
                ColorStateList(states, colors));
    }
    public static  void setRaqdioButtonColor(RadioButton radioButton, int checkedColor, int uncheckedColor) {
        int states[][] = {{android.R.attr.state_checked}, {}};
        int colors[] = {checkedColor, uncheckedColor};
        CompoundButtonCompat.setButtonTintList(radioButton, new
                ColorStateList(states, colors));
    }
}
