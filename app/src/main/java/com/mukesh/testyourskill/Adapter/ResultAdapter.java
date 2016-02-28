package com.mukesh.testyourskill.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mukesh.testyourskill.R;
import com.mukesh.testyourskill.database.Ques_SetGet;

import java.util.ArrayList;

/**
 * Created by Mukesh on 3/21/2015.
 */
public class ResultAdapter extends BaseAdapter {
    // int id;
    ArrayList<Ques_SetGet> result;
    Context context;


    // int[] bgcolor=new int[]{R.color.colorOne,R.color.colorsix,R.color.colorTwo,
    //  R.color.colorThree,R.color.colorfour,R.color.colorfive};
    public ResultAdapter(Context context, ArrayList<Ques_SetGet> result) {
        this.context = context;
        this.result = result;
    }


    @Override
    public int getCount() {
        return result.size();
    }

    @Override
    public Object getItem(int position) {
        return result.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return super.getViewTypeCount();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View roView = convertView;
        ViewHolder holder;
        if (roView == null) {

            holder = new ViewHolder();
            LayoutInflater inflator = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            roView = inflator.inflate(R.layout.results_row, parent, false);
            holder.tvquestion = (TextView) roView.findViewById(R.id.tvQuestion1);
            holder.useranswer = (TextView) roView.findViewById(R.id.uans);
            holder.correctanswer = (TextView) roView.findViewById(R.id.cans);

            roView.setTag(holder);
        } else {
            holder = (ViewHolder) roView.getTag();
        }
        holder.correctanswer.setTag(new Integer(position));
        holder.tvquestion.setText((position + 1) + "." + result.get(position).getQues());
        holder.correctanswer.setText(result.get(position).getAnswer());
      if (!(result.get(position).getUserAnswer().equals(result.get(position).getAnswer())))
                holder.useranswer.setTextColor(Color.RED);
        else
          holder.useranswer.setTextColor(Color.BLACK);
        holder.useranswer.setText(result.get(position).getUserAnswer());
        return roView;
    }

    private static class ViewHolder {
        TextView tvquestion, useranswer, correctanswer;


    }

}


