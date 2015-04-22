package com.kcb.teacher.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.kcb.common.base.BaseActivity;
import com.kcb.common.base.BaseFragmentActivity;
import com.kcb.teacher.fragment.SignInFragment;
import com.kcb.teacher.fragment.TestFragment;
import com.kcbTeam.R;

/**
 * 
 * @className: HomePageActivity
 * @description: home page
 * @author: ZQJ
 * @date: 2015��4��22�� ����9:39:56
 */
public class HomePageActivity extends BaseFragmentActivity {

    private Button exitButton;
    private Button courseSignInButton;
    private Button courseTestButton;
    private Button studentCenterButton;

    private SignInFragment mSignInFragment;
    private TestFragment mTestFragment;
    
    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_homepage);
    }

    @Override
    protected void initView() {

        exitButton = (Button) findViewById(R.id.button_exit);
        courseSignInButton = (Button) findViewById(R.id.button_course_signin);
        courseTestButton = (Button) findViewById(R.id.button_course_test);
        studentCenterButton = (Button) findViewById(R.id.button_student_center);

        exitButton.setOnClickListener(this);
        courseSignInButton.setOnClickListener(this);
        courseTestButton.setOnClickListener(this);
        studentCenterButton.setOnClickListener(this);
        
        setDefaultFragment();
    }

    @Override
    protected void initData() {}

    @Override
    public void onClick(View v) {
        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        switch (v.getId()) {
            case R.id.button_exit:
                
                break;
            case R.id.button_course_signin:
                if(null == mSignInFragment){
                    mSignInFragment = new SignInFragment();
                }
                mFragmentTransaction.replace(R.id.fragment_content, mSignInFragment);
                break;
            case R.id.button_course_test:
                if(null == mTestFragment){
                    mTestFragment = new TestFragment();
                }
                mFragmentTransaction.replace(R.id.fragment_content, mTestFragment);
            case R.id.button_student_center:
                break;
            default:
                break;
        }
        mFragmentTransaction.commit();
    }

    private void setDefaultFragment() {
        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mSignInFragment = new SignInFragment();
        mFragmentTransaction.replace(R.id.fragment_content, mSignInFragment);
        mFragmentTransaction.commit();
    }
}
