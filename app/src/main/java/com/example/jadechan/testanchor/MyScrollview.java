package com.example.jadechan.testanchor;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * Created by dianping on 2017/6/9.
 */

public class MyScrollview extends ScrollView {
    private static final String TAG = "MyScrollview";

    private OnScrollListener listener;
    private int lastScrollY;
    public MyScrollview(Context context) {
        super(context);
    }

    public MyScrollview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScrollview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 监听scrollview的滑动位子
     * @param ev
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if(listener != null){
            listener.onScroll(lastScrollY = this.getScrollY());
        }
//        switch(ev.getAction()){
//            case MotionEvent.ACTION_UP:
//                handler.sendMessageDelayed(handler.obtainMessage(), 5);
//                break;
//        }
        return super.onTouchEvent(ev);
    }


    public void setListener(OnScrollListener listener){
        this.listener = listener;
    }

    /**
     * 手指离开后scrollview还会继续滑动，保证scrollY是最后停止时的y

    private Handler handler = new Handler(){
        public void handleMassage(){
            int scrollY = MyScrollview.this.getScrollY();

            if(lastScrollY != scrollY){
                lastScrollY = scrollY;
                handler.sendMessageDelayed(handler.obtainMessage(),5);
            }
            if(listener!=null){
                listener.onScroll(scrollY);
            }
        }

    };
     */

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        Log.d(TAG,"fling................");
        listener.onScroll(this.getScrollY());
    }


    /**
     * scrollview 滑动的位置的回调接口
     */
    public interface OnScrollListener{

        public  void onScroll(int scrollY);
    }
}
