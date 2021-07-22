package com.example.king_game.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.king_game.R;



public class PlayGridAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private int mNumber;

    public PlayGridAdapter(Context context, int num) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        mNumber = num;
    }

    @Override
    public int getCount() {
        return mNumber;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.grid_item, null);
            holder = new ViewHolder();
            holder.textView = (TextView) convertView.findViewById(R.id.text_select);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.textView.setText(mContext.getString(R.string.button_king_or_citizen));

        return convertView;
    }

    private static class ViewHolder {
        public TextView textView;
    }
}
