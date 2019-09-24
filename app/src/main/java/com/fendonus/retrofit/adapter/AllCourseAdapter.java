package com.fendonus.retrofit.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.fendonus.retrofit.CourseDetailsActivity;
import com.fendonus.retrofit.MainActivity;
import com.fendonus.retrofit.R;
import com.fendonus.retrofit.model.Course;
import com.fendonus.retrofit.viewmodel.AllCourseViewModel;

import java.util.List;

public class AllCourseAdapter extends RecyclerView.Adapter<AllCourseAdapter.MyViewHolder> {

    Context context;
    List<Course> courseList;

    public AllCourseAdapter(Context context, List<Course> courseList) {
        this.context = context;
        this.courseList = courseList;
    }

    @NonNull
    @Override
    public AllCourseAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_row, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllCourseAdapter.MyViewHolder holder, final int position) {

        String imageLink = courseList.get(position).getIcon();
        String image[] = imageLink.split(" ");
        String URL[] = image[1].split("@@");
        //Log.e("course:", URL[1]);
        holder.courseTV.setText(courseList.get(position).getTitle());
        Glide.with(context).load(URL[1]).into(holder.iconIV);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, ""+courseList.get(position).getTitle(), Toast.LENGTH_SHORT).show();
                final Intent intent;
                intent = new Intent(context, CourseDetailsActivity.class);
                intent.putExtra("courseName", courseList.get(position).getTitle());
                context.startActivity(intent);

                //goToCourseDetailsActivity();
            }
        });
    }

    private void goToCourseDetailsActivity() {

    }

    @Override
    public int getItemCount() {
        return courseList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView iconIV;
        TextView courseTV;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            iconIV = itemView.findViewById(R.id.course_icon_iv_id);
            courseTV = itemView.findViewById(R.id.course_name_tv_id);
        }
    }
}