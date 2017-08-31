package com.gu.testonmeasure;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * @author developergu
 * @version v1.0.0
 */

public class CustomViewGroup extends ViewGroup {
    public static final String TAG = "tag";
    private LinearLayout v1, v2;


    public CustomViewGroup(Context context) {
        super(context);
    }

    public CustomViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.e(TAG, "--CustomViewGroup()-- ");

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        setBackgroundColor(Color.BLUE);
        Log.e(TAG, "--onFinishInflate-- " + getMeasuredWidth());
        addChildView(getContext());
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (changed) {
            Log.e(TAG, "--onLayout-- ");
            v1.layout(0, 0, v1.getMeasuredWidth(), v1.getMeasuredHeight());
            v2.layout(v1.getMeasuredWidth(), v1.getMeasuredHeight(), v1.getMeasuredWidth() + v2.getMeasuredWidth(), v1.getMeasuredHeight() + v2.getMeasuredHeight());
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        /**
         * measureChildren会调用measureChild，measureChild会使用getChildMeasureSpec使用layoutparmer定义宽高
         */
//        measureChildren(widthMeasureSpec, heightMeasureSpec);
        updateChildrenSize(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(widthMeasureSpec, widthMeasureSpec);
        Log.e(TAG, "--onMeasure-- " + getMeasuredWidth());
    }

    /**
     * 如果onMeasure中使用measureChildren大小就是100x100
     */
    private void addChildView(Context context) {
        v1 = new LinearLayout(context);
        v2 = new LinearLayout(context);
        v1.setLayoutParams(new LinearLayout.LayoutParams(100, 100));
        v2.setLayoutParams(new LinearLayout.LayoutParams(100, 100));
        v1.setBackgroundColor(Color.RED);
        v2.setBackgroundColor(Color.GREEN);
        addView(v1);
        addView(v2);
    }

    /**
     * 根据parent尺寸修改子view尺寸，在onMeasure中调用
     */
    private void updateChildrenSize(int parentWidthMeasureSpec, int parentHeightMeasureSpec) {
        int childrenWidth = MeasureSpec.getSize(parentWidthMeasureSpec) / 3;
        int spec = MeasureSpec.makeMeasureSpec(childrenWidth, MeasureSpec.EXACTLY);
        v1.measure(spec, spec);
        v2.measure(spec, spec);
    }
}
