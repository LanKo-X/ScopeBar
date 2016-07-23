package com.lanko.scopebarsample;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Scrolling disable viewpager.
 * Created by Lex on 16/7/23.
 */
public class ViewPager extends android.support.v4.view.ViewPager {

    public ViewPager(Context context) {
        super(context);
    }

    public ViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ViewPager);

        setScrollable(a.getBoolean(R.styleable.ViewPager_scrollable, true));

        a.recycle();
    }

    private boolean isScrollable = true;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return this.isScrollable && super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        return this.isScrollable && super.onInterceptTouchEvent(event);
    }

    public void setScrollable(boolean isScrollable) {
        this.isScrollable = isScrollable;
    }
}
