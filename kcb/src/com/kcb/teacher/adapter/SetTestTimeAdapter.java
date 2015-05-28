package com.kcb.teacher.adapter;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.kcb.teacher.model.test.Question;
import com.kcb.teacher.model.test.QuestionItem;
import com.kcb.teacher.model.test.Test;
import com.kcbTeam.R;

/**
 * 
 * @className: ListAdapterQuestions
 * @description:
 * @author: ZQJ
 * @date: 2015年5月11日 下午10:34:21
 */
public class SetTestTimeAdapter extends BaseAdapter {

    private final String numFormatString = "第%1$d题";

    private Context mContext;
    private Test mTest;

    public SetTestTimeAdapter(Context context, Test test) {
        mContext = context;
        mTest = test;
    }

    @Override
    public int getCount() {
        return mTest.getQuestionNum();
    }

    @Override
    public Question getItem(int position) {
        return mTest.getQuestion(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
//        Question temp = mList.get(position);
//        if (getItem(position).isAllString()) {
//            view = View.inflate(mContext, R.layout.tch_listview_questions_item_string, null);
//            TextView questionNum = (TextView) view.findViewById(R.id.textview_question_num);
//            questionNum.setText(String.format(numFormatString, 1 + position));
//
//            TextView content = (TextView) view.findViewById(R.id.textview_content);
//            content.setText(temp.contentString());
//
//            TextView correctOptions = (TextView) view.findViewById(R.id.textview_answer);
//            // wanghang TODO
//            // correctOptions.setText(temp.getCorrectOptionString());
//
//        } else {
//            view = View.inflate(mContext, R.layout.tch_listview_questions_item_common, null);
//
//            TextView questionNumHint = (TextView) view.findViewById(R.id.textview_question_num);
//            questionNumHint.setText(String.format(numFormatString, 1 + position));
//
//            EditText quesitionEditText = (EditText) view.findViewById(R.id.edittext_question);
//            showContent(quesitionEditText, temp.getTitle());
//
//            EditText optionAEditText = (EditText) view.findViewById(R.id.edittext_A);
//            showContent(optionAEditText, temp.getChoiceA());
//
//            EditText optionBEditText = (EditText) view.findViewById(R.id.edittext_B);
//            showContent(optionBEditText, temp.getChoiceB());
//
//            EditText optionCEditText = (EditText) view.findViewById(R.id.edittext_C);
//            showContent(optionCEditText, temp.getChoiceC());
//
//            EditText optionDEditText = (EditText) view.findViewById(R.id.edittext_D);
//            showContent(optionDEditText, temp.getChoiceD());
//
//            TextView correctOptions = (TextView) view.findViewById(R.id.textview_answer);
//            // wanghang TODO
//            // correctOptions.setText(mList.get(position).getCorrectOptionString());
//        }
        return view;
    }

    @SuppressWarnings("deprecation")
    @SuppressLint("NewApi")
    private void showContent(EditText view, QuestionItem content) {
        if (content.isText()) {
            view.setText(content.getText());
        } else {
            view.setBackground(new BitmapDrawable(content.getBitmap()));
        }
    }
}
