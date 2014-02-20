package com.mustafaferhan;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

public class ExpandableScrollView extends ScrollView {

    private boolean enableScrolling = true;

    public boolean isEnableScrolling() {
        return enableScrolling;
    }

    public void setEnableScrolling(boolean enableScrolling) {
        this.enableScrolling = enableScrolling;
    }

    public ExpandableScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public ExpandableScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ExpandableScrollView(Context context) {
        super(context);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        if (isEnableScrolling()) {
            return super.onInterceptTouchEvent(ev);
        } else {
            return false;
        }
    }
   
    
 
}