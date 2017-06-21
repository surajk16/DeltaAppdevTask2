package com.example.suraj.deltaappdevtask2;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by suraj on 17-06-2017.
 */

public class customadapter extends BaseAdapter {

    private Context mContext;
    private List<ListModel> mListModel;

    //Constructor


    public customadapter(Context mContext, List<ListModel> mListModel) {
        this.mContext = mContext;
        this.mListModel = mListModel;
    }



    @Override
    public int getCount() {
        return mListModel.size();
    }

    @Override
    public Object getItem(int position) {
        return mListModel.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(mContext,R.layout.list,null);
        ImageView i = (ImageView) v.findViewById(R.id.limg);
        TextView t = (TextView) v.findViewById(R.id.ltv);

        //if (mListModel.get(position).getFlag()==0)
            i.setImageBitmap(mListModel.get(position).getListimg());
        //else i.setImageURI(mListModel.get(position).getUri());
        t.setText(mListModel.get(position).getListcaption());

        return v;
    }


}