package com.kcb.student.adapter;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.kcb.library.view.buttonflat.ButtonFlat;
import com.kcbTeam.R;

public class StartCheckInAdapter
        extends RecyclerView.Adapter<StartCheckInAdapter.CheckinViewHolder> {

    private String[] mItems;
    public RecyclerItemClickListener mItemClickListener;

    public interface RecyclerItemClickListener {
        public void onItemClick(View view, int postion);
    }

    public StartCheckInAdapter() {
        super();
        mItems = new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "", "0", ""};
    }

    public void setRecyclerItemClickListener(RecyclerItemClickListener listener) {
        mItemClickListener = listener;
    }

    @Override
    public int getItemCount() {
        return mItems.length;
    }

    @SuppressLint("InflateParams")
    @Override
    public CheckinViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        View view =
                LayoutInflater.from(viewGroup.getContext()).inflate(
                        R.layout.stu_recycleritem_checkin, null);
        CheckinViewHolder viewHolder = new CheckinViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CheckinViewHolder viewHolder, int position) {
        viewHolder.setText(position, mItems[position]);
    }

    public class CheckinViewHolder extends RecyclerView.ViewHolder {
        private ButtonFlat numButton;
        private ImageView actionImageView;

        public CheckinViewHolder(View view) {
            super(view);
            numButton = (ButtonFlat) view.findViewById(R.id.button_num);
            numButton.setBackgroundResource(R.drawable.stu_checkin_textview);
            numButton.setTextSize(18);
            // black_700
            numButton.setTextColor(Color.parseColor("#616161"));
            numButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != mItemClickListener) {
                        mItemClickListener.onItemClick(v, getPosition());
                    }
                }
            });
            actionImageView = (ImageView) view.findViewById(R.id.imageview_action);
        }

        public void setText(int index, String num) {
            numButton.setText(num);
            if (index == 9) {
                actionImageView.setBackgroundResource(R.drawable.ic_highlight_remove_grey600_24dp);
                actionImageView.setVisibility(View.VISIBLE);
            } else if (index == 11) {
                actionImageView.setBackgroundResource(R.drawable.ic_label_outline_grey600_24dp);
                actionImageView.setVisibility(View.VISIBLE);
            } else {
                actionImageView.setVisibility(View.GONE);
            }
        }
    }
}
