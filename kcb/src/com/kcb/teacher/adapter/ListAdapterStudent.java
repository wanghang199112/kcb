package com.kcb.teacher.adapter;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.kcb.library.view.buttonflat.ButtonFlat;
import com.kcb.teacher.activity.StuDetailsActivity;
import com.kcb.teacher.fragment.StuCentreFragment;
import com.kcb.teacher.model.StudentInfo;
import com.kcbTeam.R;

/**
 * 
 * @className: ListAdapterStudent
 * @description:
 * @author: ZQJ
 * @date: 2015年4月24日 下午3:24:01
 */
@SuppressLint("ViewHolder")
public class ListAdapterStudent extends BaseAdapter {


    private List<StudentInfo> mList;
    private Context mContext;

    private final String FORMAT_RATE = "%1$d%%";

    public ListAdapterStudent(Context context, List<StudentInfo> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.tch_listitem_stucentre, null);
            holder = new ViewHolder();
            holder.studentName = (TextView) convertView.findViewById(R.id.textview_studentname);
            holder.studentId = (TextView) convertView.findViewById(R.id.textview_studentid);
            holder.checkInRate = (TextView) convertView.findViewById(R.id.textview_checkinRate);
            holder.correctRate = (TextView) convertView.findViewById(R.id.textview_correctRate);
            holder.detailButton = (ButtonFlat) convertView.findViewById(R.id.button_details);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.studentName.setText(mList.get(position).getStudentName());
        holder.studentId.setText(mList.get(position).getStudentID());
        holder.checkInRate.setText(String.format(FORMAT_RATE, (int) (100 * mList.get(position)
                .getCheckInRate())));
        holder.correctRate.setText(String.format(FORMAT_RATE, (int) (100 * mList.get(position)
                .getCorrectRate())));
        holder.detailButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, StuDetailsActivity.class);
                intent.putExtra(StuCentreFragment.CURRENT_STU_KEY, mList.get(position));
                mContext.startActivity(intent);
            }
        });
        return convertView;
    }

    static class ViewHolder {
        TextView studentName;
        TextView studentId;
        TextView checkInRate;
        TextView correctRate;
        ButtonFlat detailButton;
    }

}
