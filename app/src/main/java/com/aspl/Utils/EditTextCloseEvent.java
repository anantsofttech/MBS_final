package com.aspl.Utils;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.text.InputFilter;
import android.util.AttributeSet;
import android.view.KeyEvent;

public class EditTextCloseEvent extends AppCompatEditText {

    private KeyImeChange keyImeChangeListener;;
    public interface KeyImeChange {
        public void onKeyIme(int keyCode, KeyEvent event);
    }

    public void setKeyImeChangeListener(KeyImeChange listener){
        keyImeChangeListener = listener;
    }

    public EditTextCloseEvent(Context context) {
        super(context);
    }

    public EditTextCloseEvent(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EditTextCloseEvent(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean onKeyPreIme(int keyCode, KeyEvent event) {
        if(keyImeChangeListener != null){
            keyImeChangeListener.onKeyIme(keyCode, event);
        }

        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            for (InputFilter filter : this.getFilters()) {
                if (filter instanceof InputFilterIntRange)
                    ((InputFilterIntRange) filter).onFocusChange(this, false);
            }
        }
        return super.dispatchKeyEvent(event);
    }

/*    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if(event.getKeyCode() == KeyEvent.KEYCODE_BACK){
            Log.d("Test","Back Pressed");
            return viewModel.backClicked();
        }
        return super.dispatchKeyEvent(event);
    }*/

    //public void onBackPressed() { view.handleBack(); }
}