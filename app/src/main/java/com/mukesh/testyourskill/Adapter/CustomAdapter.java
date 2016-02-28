package com.mukesh.testyourskill.adapter;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


import com.mukesh.testyourskill.R;
import com.mukesh.testyourskill.database.Ques_SetGet;

public class CustomAdapter extends BaseAdapter {
    // int id;
    ArrayList<Ques_SetGet> result;
    Context context;
    int positn = 0;
    boolean[] RadiobuttonState;
    String[] userAnswer;

    // int[] bgcolor=new int[]{R.color.colorOne,R.color.colorsix,R.color.colorTwo,
    //  R.color.colorThree,R.color.colorfour,R.color.colorfive};
    public CustomAdapter(Context context, ArrayList<Ques_SetGet> result) {
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
            roView = inflator.inflate(R.layout.customrow, parent, false);

            holder.tvquestion = (TextView) roView.findViewById(R.id.tvQuestion);
            holder.a = (RadioButton) roView.findViewById(R.id.a);
            holder.b = (RadioButton) roView.findViewById(R.id.b);
            holder.c = (RadioButton) roView.findViewById(R.id.c);
            holder.d = (RadioButton) roView.findViewById(R.id.d);
            holder.rg = (RadioGroup) roView.findViewById(R.id.rgQues);
            holder.rg
                    .setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

                        public void onCheckedChanged(RadioGroup group,
                                                     int checkedId) {
                            Integer pos = (Integer) group.getTag();
                            // To identify the Model object i get from the RadioGroup with getTag()
                            //  an integer representing the actual position

                            Ques_SetGet element = result.get(pos);

                            switch (checkedId) { //set the Model to hold the answer the user picked
                                case R.id.a:
                                    element.current = Ques_SetGet.ANSWER_ONE_SELECTED;
                                    // element.setUserAnswer(((RadioButton)group.getChildAt(element.current)).getText().toString());
                                    break;
                                case R.id.b:
                                    element.current = Ques_SetGet.ANSWER_TWO_SELECTED;
                                    // element.setUserAnswer(((RadioButton)group.getChildAt(element.current)).getText().toString());
                                    break;
                                case R.id.c:
                                    element.current = Ques_SetGet.ANSWER_THREE_SELECTED;
                                    // element.setUserAnswer(((RadioButton)group.getChildAt(element.current)).getText().toString());
                                    break;
                                case R.id.d:
                                    element.current = Ques_SetGet.ANSWER_FOUR_SELECTED;
                                    // element.setUserAnswer(((RadioButton)group.getChildAt(element.current)).getText().toString());
                                    break;
                                default:
                                    element.current = Ques_SetGet.NONE; // Something was wrong set to the default
                            }
                            Log.d("Test", result.get(pos).current + "   " + pos);
                           /* if (result.get(pos).current != Ques_SetGet.NONE)
                            result.get(pos).setUserAnswer(((RadioButton) group.getChildAt(result.get(pos).current)).getText().toString());*/
                            int childCount = group.getChildCount();
                            for (int x = 0; x < childCount; x++) {
                                RadioButton d1 = (RadioButton) group.getChildAt(x);

                                if (d1.isChecked()) {
                                    Ques_SetGet q = result.get(pos);

                                    if (d1.getText().toString().equals(result.get(pos).getAnswer())) {

                                      //  Toast.makeText(context, "Correct: " + (d1.getText().toString()), Toast.LENGTH_SHORT).show();
                                    }


                                }

                            }

                        }
                    });

            roView.setTag(holder);
        } else {
            holder = (ViewHolder) roView.getTag();
        }
        holder.rg.setTag(new Integer(position));
        // I passed the current position as a tag
        if (result.get(position).current != Ques_SetGet.NONE) {
            RadioButton r = (RadioButton) holder.rg.getChildAt(result.get(position).current);
            //result.get(position).setUserAnswer(r.getText().toString());
            r.setChecked(true);

        } else {
            holder.rg.clearCheck();
            // This is required because although the Model could have the current
            // position to NONE you could be dealing with a previous row where the user already picked an answer.
        }


        holder.tvquestion.setText((position + 1) + "." + result.get(position).getQues());
        holder.a.setText(result.get(position).getA());
        holder.b.setText(result.get(position).getB());
        holder.c.setText(result.get(position).getC());
        holder.d.setText(result.get(position).getD());

        return roView;
    }

    private static class ViewHolder {
        TextView tvquestion;
        RadioButton a, b, c, d;
        RadioGroup rg;


    }

}


