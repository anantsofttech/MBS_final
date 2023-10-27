package com.aspl.Utils;

import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.widget.EditText;


public class InputFilterIntRange implements InputFilter, View.OnFocusChangeListener {

    //private final int min, max;
    private final float min, max;
    private float tempMin = 0;

    public InputFilterIntRange(float min, float max) {
        if (min > max) {
            // Input sanitation for the filter itself
            float mid = max;
            max = min;
            min = mid;
        }
        this.min = min;
        this.max = max;
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {

        //tempMin = Float.parseFloat(source.toString());

        // Determine the final string that will result from the attempted input
        String destString = dest.toString();
        //String inputString = destString.substring(0, dstart) + source.toString() + destString.substring(dstart);

        // Remove the string out of destination that is to be replaced
        String inputString = dest.toString().substring(0, dstart) + dest.toString().substring(dend, dest.toString().length());
        // Add the new string in
        inputString = inputString.substring(0, dstart) + source.toString() + inputString.substring(dstart, inputString.length());

        // Don't prevent - sign from being entered first if min is negative
        if (inputString.equalsIgnoreCase("-") && min < 0) return null;

        try {
            float input = Integer.parseInt(inputString);
            if (mightBeInRange(input))
                return null;
        } catch (NumberFormatException nfe) {

        }

        return "";
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {

        // Since we can't actively filter all values
        // (ex: range 25 -> 350, input "15" - could be working on typing "150"),
        // lock values to range after text loses focus
        if (!hasFocus) {
            if (v instanceof EditText) sanitizeValues((EditText) v);
        }
    }

    private boolean mightBeInRange(float value) {
        // Quick "fail"
        if (value >= 0 && value > max) return false;
        if (value >= 0 && value >= min) return true;
        if (value < 0 && value < min) return false;
        if (value < 0 && value <= max) return true;


        boolean negativeInput = value < 0;

        // If min and max have the same number of digits, we can actively filter
        if (numberOfDigits(min) == numberOfDigits(max)) {
            if (!negativeInput) {
                if (numberOfDigits(value) >= numberOfDigits(min) && value < min) return false;
            } else {
                if (numberOfDigits(value) >= numberOfDigits(max) && value > max) return false;
            }
        }

        return true;
    }

    private float numberOfDigits(float n) {
        return String.valueOf(n).replace("-", "").length();
    }

    private void sanitizeValues(EditText valueText) {
        try {
            float value = Integer.parseInt(valueText.getText().toString());
            // If value is outside the range, bring it up/down to the endpoint
            if (value < min) {
                value = min;
                valueText.setText(String.valueOf(value));
            } else if (value > max) {
                value = max;
                valueText.setText(String.valueOf(value));
            }
        } catch (NumberFormatException nfe) {
            valueText.setText("");
        }
    }

}