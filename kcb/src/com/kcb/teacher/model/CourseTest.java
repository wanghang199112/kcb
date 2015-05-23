package com.kcb.teacher.model;

import java.io.Serializable;
import java.util.List;

import android.R.integer;

/**
 * 
 * @className: CourseTest
 * @description:
 * @author: ZQJ
 * @date: 2015年5月11日 下午8:17:45
 */
public class CourseTest implements Serializable {

    private static final long serialVersionUID = 4L;

    @SuppressWarnings("unused")
    private static final String TAG = "CourseTest";

    private List<ChoiceQuestion> mChoiceQuestionList;
    private String mTestName;
    private int mTestTime;
    private String mTestDate;


    public CourseTest(String testName, List<ChoiceQuestion> questionList) {
        mChoiceQuestionList = questionList;
        mTestName = testName;
    }

    public CourseTest(String testName, List<ChoiceQuestion> questionList, int time, String date) {
        mChoiceQuestionList = questionList;
        mTestName = testName;
        mTestTime = time;
        mTestDate = date;
    }

    public void setQuestionList(List<ChoiceQuestion> mChoiceQuestionList) {
        this.mChoiceQuestionList = mChoiceQuestionList;
    }

    public List<ChoiceQuestion> getQuestionList() {
        return this.mChoiceQuestionList;
    }

    public void setTestName(String mTestName) {
        this.mTestName = mTestName;
    }

    public String getTestName() {
        return this.mTestName;
    }

    public int getTestTime() {
        return this.mTestTime;
    }

    public void setTestTime(int mTestTime) {
        this.mTestTime = mTestTime;
    }

    public String getTestDate() {
        return this.mTestDate;
    }

    public void setTestDate(String mTestDate) {
        this.mTestDate = mTestDate;
    }


}
