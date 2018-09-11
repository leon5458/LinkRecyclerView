package com.hly.linkrecyclerview;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * ~~~~~~文件描述:~~~~~~
 * ~~~~~~作者:huleiyang~~~~~~
 * ~~~~~~创建时间:2018/9/11~~~~~~
 * ~~~~~~更改时间:2018/9/11~~~~~~
 * ~~~~~~版本号:1~~~~~~
 */
public class LinktwoActivity extends AppCompatActivity {

    List<String> leftList = new ArrayList<>();
    List<String> rightList = new ArrayList<>();

    int Current = 0;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linktwo_activity_layout);
//        setContentView(R.layout.test);

        for (int i = 0; i < 20; i++) {
            leftList.add("");
            rightList.add("");
        }

        RecyclerView recycle_left = findViewById(R.id.recycleleft);
        final RecyclerView recycle_right = findViewById(R.id.recycleright);

        recycle_left.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recycle_right.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        RecyclerViewAdapter leftAdapter = new RecyclerViewAdapter(this, R.layout.left2_item, leftList);
        RecyclerViewAdapter rightAdapter = new RecyclerViewAdapter(this, R.layout.right2_item, rightList);

        recycle_left.setAdapter(leftAdapter);
        recycle_right.setAdapter(rightAdapter);

        leftAdapter.setCallBack(new RecyclerViewAdapter.CallBack() {
            @Override
            public <T> void convert(IViewHolder holder, T bean, int position) {
                TextView tv = (TextView) holder.getView(R.id.title);
                if (position == Current) {
                    tv.setTextColor(Color.parseColor("#FF9900"));
                } else {
                    tv.setTextColor(Color.parseColor("#3a3a3a"));
                }
            }
        });

        leftAdapter.setOnItemClickListner(new RecyclerViewAdapter.OnItemClickListner() {
            @Override
            public void onItemClickListner(View v, int position) {

            }
        });


    }
}
