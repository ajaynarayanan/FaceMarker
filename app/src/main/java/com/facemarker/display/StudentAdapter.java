package com.facemarker.display;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;
import com.facemarker.R;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {
    ArrayList<String> StudentList;

    public StudentAdapter(ArrayList<String> StudentList, Context context) {
        this.StudentList = StudentList;
    }

    @Override
    public StudentAdapter.StudentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.planet_row,parent,false);
        StudentViewHolder viewHolder=new StudentViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(StudentAdapter.StudentViewHolder holder, int position) {
        holder.text.setText(StudentList.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return StudentList.size();
    }

    public static class StudentViewHolder extends RecyclerView.ViewHolder{

        protected TextView text;

        public StudentViewHolder(View itemView) {
            super(itemView);
            text= (TextView) itemView.findViewById(R.id.text_id);
        }
    }
}
