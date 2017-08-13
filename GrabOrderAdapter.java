package com.example.licky.scolldeletebutton;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Licky on 2017/8/11.
 */

public class GrabOrderAdapter extends BaseAdapter {
    private LayoutInflater myInflater;
    private Context mContext;

    String[] comments;
    String[] time;
    String[] locations;

    public GrabOrderAdapter(Context c, String[] locations, String[] time, String[] comments) {
        //myInflater = LayoutInflater.from(c);
        this.mContext = c;
        this.locations = locations;
        this.time = time;
        this.comments = comments;
    }

    @Override
    public int getCount() {
        return locations.length;
    }

    @Override
    public Object getItem(int position) {
            return locations[position];
    }

    public long getItemId(int position) {
            return position;
    }
    @Override
    public View getView(int postition, View convertView, ViewGroup parent) {
        ViewHolder viewholder = null;
        if(convertView == null){
            convertView = View.inflate(mContext,R.layout.item, null);
            //取得XML內容

            viewholder = new ViewHolder();
            viewholder.tvLocations = (TextView)convertView.findViewById(R.id.location_X);
            viewholder.tvTime = (TextView)convertView.findViewById(R.id.time);
            viewholder.tvComments = (TextView)convertView.findViewById(R.id.comment_content);

            convertView.setTag(viewholder);
        }
        else{
            viewholder = (ViewHolder)convertView.getTag();
        }
        Log.i("test", locations[postition]);
        viewholder.tvLocations.setText(locations[postition]);
        viewholder.tvTime.setText(time[postition]);
        viewholder.tvComments.setText(comments[postition]);
        return convertView;
    }
    public static class ViewHolder{
        TextView tvLocations;
        TextView tvTime;
        TextView tvComments;
    }
}
