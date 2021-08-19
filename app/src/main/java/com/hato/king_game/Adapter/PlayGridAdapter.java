package com.hato.king_game.Adapter;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.hato.king_game.R;

import java.util.ArrayList;


public class PlayGridAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private ArrayList<Integer> mTappedList = new ArrayList<>();
    private int mNumber;

    public PlayGridAdapter(Context context, int num, ArrayList<Integer> tappedList) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        mNumber = num;
        mTappedList = tappedList;
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

    @RequiresApi(api = Build.VERSION_CODES.M)
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

        for (int i = 0; i < mTappedList.size(); i++) {
            if (mTappedList.get(i) == position) {
                holder.textView.setText(mContext.getString(R.string.button_selected));
                holder.textView.setBackgroundColor(mContext.getColor(R.color.background_gray));
                break;
            }
        }

        return convertView;
    }

    private static class ViewHolder {
        public TextView textView;
    }
}
