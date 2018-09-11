package com.hly.linkrecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * ~~~~~~文件描述:~~~~~~
 * ~~~~~~作者:huleiyang~~~~~~
 * ~~~~~~创建时间:2018/9/11~~~~~~
 * ~~~~~~更改时间:2018/9/11~~~~~~
 * ~~~~~~版本号:1~~~~~~
 */
public class AbsoluteRecycleView extends RecyclerView{
    public AbsoluteRecycleView(Context context) {
        super(context);
    }

    public AbsoluteRecycleView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
