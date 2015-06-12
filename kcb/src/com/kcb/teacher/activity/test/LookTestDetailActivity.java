package com.kcb.teacher.activity.test;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.kcb.common.base.BaseActivity;
import com.kcb.common.model.test.Test;
import com.kcb.common.view.test.QuestionView;
import com.kcb.library.view.buttonflat.ButtonFlat;
import com.kcbTeam.R;

public class LookTestDetailActivity extends BaseActivity {

    private ButtonFlat backButton;
    private TextView testNameNumTextView;

    private QuestionView questionView;

    private ButtonFlat lastButton;
    private ButtonFlat nextButton;

    public static Test sTest;
    private int mQuestionIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tch_activity_look_test_detail);

        initView();
        initData();
    }

    @Override
    protected void initView() {
        backButton = (ButtonFlat) findViewById(R.id.button_back);
        backButton.setOnClickListener(this);

        testNameNumTextView = (TextView) findViewById(R.id.textview_test_name_num);
        testNameNumTextView.setText(String.format(getString(R.string.tch_test_name_num),
                sTest.getName(), sTest.getQuestionNum()));

        questionView = (QuestionView) findViewById(R.id.questionview);

        lastButton = (ButtonFlat) findViewById(R.id.button_last);
        lastButton.setOnClickListener(this);
        nextButton = (ButtonFlat) findViewById(R.id.button_next);
        nextButton.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        showQuestion();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_back:
                finish();
                break;
            case R.id.button_last:
                lastQuestion();
                break;
            case R.id.button_next:
                nextQuestin();
                break;
            default:
                break;
        }
    }

    private void lastQuestion() {
        if (mQuestionIndex > 0) {
            mQuestionIndex--;
            showQuestion();
        }
    }

    private void nextQuestin() {
        if (mQuestionIndex != sTest.getQuestionNum() - 1) {
            mQuestionIndex++;
            showQuestion();
        }
    }

    private void showQuestion() {
        questionView.showQuestionIndex(String.format(getString(R.string.tch_question_index),
                mQuestionIndex + 1));
        questionView.showQuestion(sTest.getQuestion(mQuestionIndex));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        questionView.release();
        questionView = null;
        sTest = null;
    }

    /**
     * start
     */
    public static void start(Context context, Test test) {
        Intent intent = new Intent(context, LookTestDetailActivity.class);
        context.startActivity(intent);
        sTest = test;
    }
}
