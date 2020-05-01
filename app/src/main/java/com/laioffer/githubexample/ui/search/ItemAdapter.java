package com.laioffer.githubexample.ui.search;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.laioffer.githubexample.R;

import java.util.List;

public class ItemAdapter extends ArrayAdapter {

    private int layoutId;

    public ItemAdapter(Context context, int layoutId, List<String> list){
        super(context,layoutId,list);
        this.layoutId = layoutId;
    };

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        String item = (String) getItem(position);
        @SuppressLint("ViewHolder") View view = LayoutInflater.from(getContext()).inflate(layoutId,parent,false);
        TextView textView = (TextView) view.findViewById(R.id.text);
        textView.setText(item);
        return view;
    }
}
