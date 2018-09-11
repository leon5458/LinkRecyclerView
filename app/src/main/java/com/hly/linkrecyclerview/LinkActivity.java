package com.hly.linkrecyclerview;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
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
public class LinkActivity extends AppCompatActivity {

    private RecyclerView left;
    private RecyclerView right;

    private RecyclerViewAdapter leftAdapter;
    private RecyclerViewAdapter rightAdapter;

    List<String> leftList = new ArrayList<>();

    List<String> rightList = new ArrayList<>();

    List<String> detailsList = new ArrayList<>();

    int currentPosition = 0;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.link_activity_layout);
        initData();
//        leftList = new ArrayList<>();
//        rightList = new ArrayList<>();
//        detailsList = new ArrayList<>();
        left = findViewById(R.id.left_recycle);
        right = findViewById(R.id.right_recycle);

        left.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        right.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        leftAdapter = new RecyclerViewAdapter(this, R.layout.left_item, leftList);
        left.setAdapter(leftAdapter);

        rightAdapter = new RecyclerViewAdapter(this, R.layout.right_item, rightList);
        right.setAdapter(rightAdapter);

        leftAdapter.setCallBack(new RecyclerViewAdapter.CallBack() {
            @Override
            public <T> void convert(IViewHolder holder, T bean, int position) {
                LinearLayout layout = (LinearLayout) holder.getView(R.id.item_main_left_layout);
                TextView type = (TextView) holder.getView(R.id.item_main_left_type);
                type.setText((String) bean);
                if (position == currentPosition) {
                    layout.setBackgroundColor(0xffffffff);
                    type.setTextColor(getResources().getColor(R.color.colorAccent));
                } else {
                    layout.setBackgroundColor(0xffeeeeee);
                    type.setTextColor(0xff444444);
                }
            }
        });

        leftAdapter.setOnItemClickListner(new RecyclerViewAdapter.OnItemClickListner() {
            @Override
            public void onItemClickListner(View v, int position) {
                Log.i("leftAdapter", "scrollToPositionWithOffset-->" + position);
                LinearLayoutManager llm = ((LinearLayoutManager) right.getLayoutManager());
                /**
                 * 这种方式是定位到指定项如果该项可以置顶就将其置顶显示。比如:微信联系人的字母索引定位就是采用这种方式实现。
                 */
                llm.scrollToPositionWithOffset(position, 0);

            }
        });

        rightAdapter.setCallBack(new RecyclerViewAdapter.CallBack() {
            @Override
            public <T> void convert(IViewHolder holder, T bean, int position) {
                holder.setText(R.id.item_main_right_type, (String) bean);
                RecyclerView detailsRecycle = (RecyclerView) holder.getView(R.id.item_main_right_recycle);
                updateDetailsRecycle(detailsRecycle);
            }
        });

        right.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int i, int i1, int i2, int i3) {
                LinearLayoutManager rightManager = (LinearLayoutManager) right.getLayoutManager();

                LinearLayoutManager leftManager = ((LinearLayoutManager) left.getLayoutManager());
                /**
                 * 获取第一个item为第几个position
                 */
                currentPosition = rightManager.findFirstVisibleItemPosition();
                /**
                 * 这地方需要进行判断，如果右边的Recycle在移动的时候，左边的RecycleView也是需要进行移动的
                 * 左边的recycleview有可能会不可见，这时候，我们必须去判断一下，左边最后的一个item是不是
                 * 小于右边滑动的位置，或左边第一个item是不是大于右边滑动的位置
                 */
                if (leftManager.findFirstVisibleItemPosition() > currentPosition) {
                    leftManager.scrollToPositionWithOffset(currentPosition, 0);
                } else if (leftManager.findLastVisibleItemPosition() < currentPosition) {
                    leftManager.scrollToPositionWithOffset(currentPosition, 0);
                }

                /**
                 * 判断右边是否滑动到最后一个item，是的话，也将左边移动到最后一个item
                 * canScrollVertically(1)表示是否能向上滚动，false表示已经滚动到底部
                 */
                if (!right.canScrollVertically(1)) {
                    currentPosition = rightList.size() - 1;

                    Log.i("tag", currentPosition + "-------");
                }


                leftAdapter.notifyDataSetChanged();
            }
        });

    }



    private void updateDetailsRecycle(RecyclerView detailsRecycle) {
        RecyclerViewAdapter detailsAdapter = new RecyclerViewAdapter(this,R.layout.item_details,detailsList);
        detailsRecycle.setLayoutManager(new GridLayoutManager(this,3));
        detailsRecycle.setAdapter(detailsAdapter);
    }

    private void initData() {
        for (int i = 0; i < 20; i++) {
            leftList.add("" + i);
            rightList.add("" + i);
        }
        for (int i = 0; i < 9; i++) {
            detailsList.add("");
        }
    }
}
