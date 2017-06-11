package com.example.jadechan.testanchor;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * Created by dianping on 2017/6/8.
 */

public class TestAnchorFragments extends Fragment implements MyScrollview.OnScrollListener,TestAnchorActivity.onActivityWindowFocusChanged,View.OnClickListener {
    private static final String TAG = "TestAnchorFragments";
    private MyScrollview scrollView;
    private View rootview;
    private View topview,topview_1,topview_2;
    private TextView text[];
    private int scrollY;
    private TestAnchorActivity activity;
    private View childview[];
    private int child1dis_top;
    private int child2dis_top;
    private int child3dis_top;
    private int child4dis_top;
    private int lastpos= Integer.MIN_VALUE;
    private int childcount;

    private int topviewHeight;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

}

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        activity = (TestAnchorActivity) getActivity();
        activity.setFocusChangedListener(this);
        //初始全透明，不可见
        topview_2.setAlpha(0);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.fragment_layout,container,false);
        scrollView = (MyScrollview) rootview.findViewById(R.id.myscrollview);
        scrollView.setListener(this);
        topview = rootview.findViewById(R.id.top_ll);
        topview_1 = rootview.findViewById(R.id.top_ll1);
        topview_2 = rootview.findViewById(R.id.top_ll2);
        text = new TextView[3];
        text[0] = (TextView) rootview.findViewById(R.id.text_1);
        text[1] = (TextView) rootview.findViewById(R.id.text_2);
        text[2] = (TextView) rootview.findViewById(R.id.text_3);
        for(int i=0;i<3;i++){
            text[i].setOnClickListener(this);
        }
        LinearLayout scroll_content= (LinearLayout) rootview.findViewById(R.id.scroll_content);
        childcount = scroll_content.getChildCount();
        Log.i(TAG,"scrollview's grandson count = "+childcount);
        childview = new View[childcount];
        for(int i=0;i<childcount;i++){
            childview[i] = scroll_content.getChildAt(i);
        }

        return rootview;
    }

    @Override
    public void onScroll(int scrollY) {
         this.scrollY = scrollY;
        Log.d(TAG,"scrollY = "+scrollY);
        int dic = Math.abs(scrollY + topviewHeight);
        Log.i(TAG,"dic = "+dic);
        if(dic<=child2dis_top){
            topview_2.setAlpha(scrollY/(float)child2dis_top);
        }else if(dic<=child3dis_top){
            updateTopview(0);
        }else if(dic<=child4dis_top){
            updateTopview(1);
        }else updateTopview(2);
    }

    private void updateTopview(int pos) {
        topview_2.setAlpha(1);
        if(lastpos == pos)return;
        lastpos = pos;
        for(int i=0;i<3;i++){
            if(i==pos){
                text[i].setTextColor(getResources().getColor(R.color.colorAccent));
            }else  text[i].setTextColor(getResources().getColor(R.color.colorBlack));
        }
    }


    @Override
    public void setFocus(boolean focus) {
       if(focus){
           topviewHeight = topview.getHeight();
           child1dis_top=childview[0].getTop();
           child2dis_top=childview[1].getTop();
           child3dis_top=childview[2].getTop();
           child4dis_top=childview[3].getTop();
           Log.d(TAG,"child1dis_top = "+child1dis_top);
           Log.d(TAG,"child2dis_top = "+child2dis_top);
           Log.d(TAG,"child3dis_top = "+child3dis_top);
           Log.d(TAG,"topviewHeight = "+topviewHeight);
           Log.d(TAG,"topview1_Height = "+topview_1.getHeight());
           Log.d(TAG,"topview2_Height = "+topview_2.getHeight());
       }
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.text_1){
            scrollView.scrollTo(0,child2dis_top-topviewHeight);
            updateTopview(0);
        }else if(v.getId() == R.id.text_2){
            scrollView.scrollTo(0,child3dis_top-topviewHeight);
            updateTopview(1);
        }else if(v.getId() == R.id.text_3){
            scrollView.scrollTo(0,child4dis_top-topviewHeight);
            updateTopview(2);
        }
    }
}
