package com.hly.linkrecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * ~~~~~~文件描述:~~~~~~
 * ~~~~~~作者:huleiyang~~~~~~
 * ~~~~~~创建时间:2018/9/11~~~~~~
 * ~~~~~~更改时间:2018/9/11~~~~~~
 * ~~~~~~版本号:1~~~~~~
 */
public class IViewHolder extends RecyclerView.ViewHolder {
    View convertView;
    Context context;

    public IViewHolder(View itemView, Context context) {
        super(itemView);
        this.convertView = itemView;
        this.context = context;
    }

    public View getItemView() {
        return convertView;
    }

    public void setText(int id, String text) {
        TextView tx = convertView.findViewById(id);
        tx.setText(text);
    }

    public void setText(int id, String text, final OnClickListener onClickListener) {
        TextView tx = convertView.findViewById(id);
        tx.setText(text);
        tx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.onClickListner(v);
            }
        });
    }

    public View getView(int id) {
        return convertView.findViewById(id);
    }

    public interface OnClickListener {
        void onClickListner(View v);
    }
}
