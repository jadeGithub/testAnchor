package com.example.jadechan.testanchor;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

/**
 * Created by dianping on 2017/6/8.
 */

public class TestAnchorActivity extends FragmentActivity {
    private onActivityWindowFocusChanged focusChangedListener;


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(focusChangedListener!=null){
            focusChangedListener.setFocus(hasFocus);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);

    }

    public void setFocusChangedListener(onActivityWindowFocusChanged focusChangedListener){
        this.focusChangedListener = focusChangedListener;
    }
    public interface onActivityWindowFocusChanged{
       public void setFocus(boolean focus);
    }
}
