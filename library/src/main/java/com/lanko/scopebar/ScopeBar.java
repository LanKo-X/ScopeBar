package com.lanko.scopebar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.support.annotation.ColorInt;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Scope Bar
 * Created by Lex on 16/7/23.
 */
public class ScopeBar extends LinearLayout {

    private int currentPosition;
    private List<TextView> scopes = new ArrayList<>();
    private OnTabChangeListener onTabChangeListener;

    @ColorInt
    private int scopeColor;
    private float scopeRadius;

    public void setOnTabChangeListener(OnTabChangeListener onTabChangeListener) {
        this.onTabChangeListener = onTabChangeListener;
    }

    public interface OnTabChangeListener {
        void onTabSelected(int position);
    }

    public ScopeBar(Context context) {
        super(context);
        init(null);
    }

    public ScopeBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public ScopeBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        setDefaultValue();

        if (attrs != null)
            readAttrs(attrs);

        setBackgroundDrawable(Background.getBackgroundRect(scopeRadius, toPixels(1), scopeColor));
        // setBackground(Background.getBackgroundRect(scopeRadius, toPixels(1), scopeColor));
    }

    private void setDefaultValue() {
        scopeColor = Color.parseColor("#FF007AFF");
        scopeRadius = toPixels(4);
    }

    private void readAttrs(AttributeSet attrs) {

        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.ScopeBar);

        scopeColor = a.getColor(R.styleable.ScopeBar_scopeColor, Color.parseColor("#FF007AFF"));
        scopeRadius = a.getDimension(R.styleable.ScopeBar_scopeRadius, toPixels(4));

        a.recycle();
    }

    public void setCurrentPosition(int position) {
        if (position < scopes.size())
            // scopes.get(position).callOnClick();
            scopes.get(position).performClick();
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public ScopeBar addScope(String scopeName) {
        TextView newScope = new TextView(getContext());
        setParamsForTextView(newScope);
        newScope.setText(scopeName);
        newScope.setTextColor(scopeColor);
        newScope.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = scopes.indexOf(view);

                if (onTabChangeListener != null && currentPosition != position) {
                    onTabChangeListener.onTabSelected(position);
                }

                setCurrent(position);
            }
        });

        if (getChildCount() > 0)
            addBorder();

        LayoutParams params = new LayoutParams(0, LayoutParams.WRAP_CONTENT, 1);
        addView(newScope, params);

        scopes.add(newScope);
        if (scopes.size() == 1)
            setCurrent(0);

        return this;
    }

    private void addBorder() {
        View border = new View(getContext());
        border.setBackgroundColor(scopeColor);

        LayoutParams borderParams = new LayoutParams(toPixels(1), LayoutParams.MATCH_PARENT);
        addView(border, borderParams);
    }

    /**
     * 高亮参数所指定的 Scope, 取消上一个高亮的 Scope 的高亮
     * 并设置当前位置
     *
     * @param position 指定的 Scope 的位置
     */
    private void setCurrent(int position) {
        if (currentPosition != position) {
            TextView preScope = scopes.get(currentPosition);
            preScope.setBackgroundColor(Color.TRANSPARENT);
            preScope.setTextColor(scopeColor);
        }

        TextView nowScope = scopes.get(position);
        nowScope.setTextColor(Color.WHITE);
        if (position == 0)
            nowScope.setBackgroundDrawable(getFirstScopeBackground());
        else if (position == scopes.size() - 1)
            nowScope.setBackgroundDrawable(getLastScopeBackground());
        else
            nowScope.setBackgroundDrawable(getMiddleScopeBackground());

        currentPosition = position;
    }

    private void setParamsForTextView(TextView textView) {
        textView.setGravity(Gravity.CENTER);
        textView.setSingleLine();
        textView.setPadding(toPixels(12), toPixels(4), toPixels(12), toPixels(4));
    }

    private int toPixels(float dpValue) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public ShapeDrawable getFirstScopeBackground() {
        return Background.getLeftRoundRect(scopeRadius, scopeColor);
    }

    public ShapeDrawable getLastScopeBackground() {
        return Background.getRightRoundRect(scopeRadius, scopeColor);
    }

    public ShapeDrawable getMiddleScopeBackground() {
        return Background.getRectangle(scopeColor);
    }
}
