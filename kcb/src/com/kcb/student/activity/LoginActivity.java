package com.kcb.student.activity;

import org.json.JSONObject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request.Method;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.kcb.common.base.BaseActivity;
import com.kcb.common.server.RequestUtil;
import com.kcb.common.server.UrlUtil;
import com.kcb.student.util.AnimationUtil;
import com.kcbTeam.R;

/**
 * 
 * @className: LoginActivity
 * @description: Login interface,one Textview,one ImageView,two editView,one Button
 * @author: Tao Li
 * @date: 2015-4-24 下午9:17:01
 */

public class LoginActivity extends BaseActivity {

    private EditText idEditText;
    private EditText passwordEditText;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stu_activity_login);
        initView();
    }

    @Override
    protected void initView() {
        idEditText = (EditText) findViewById(R.id.edittext_stuid);
        passwordEditText = (EditText) findViewById(R.id.edittext_password);
        loginButton = (Button) findViewById(R.id.button_login);
        loginButton.setOnClickListener(this);
    }

    @Override
    protected void initData() {}

    @Override
    public void onClick(View v) {

        final String id = idEditText.getText().toString().trim();
        final String password = passwordEditText.getText().toString();
        if (TextUtils.isEmpty(id)) {
            AnimationUtil.smallShake(idEditText);
        } else if (TextUtils.isEmpty(password)) {
            AnimationUtil.smallShake(passwordEditText);
        } else {
            JsonObjectRequest request =
                    new JsonObjectRequest(Method.POST, UrlUtil.getStuLoginUrl(id, password), "",
                            new Listener<JSONObject>() {

                                @Override
                                public void onResponse(JSONObject response) {
                                    // TODO
                                    SharedPreferences sharedPref =
                                            getSharedPreferences("AppLoginData", MODE_PRIVATE);
                                    Editor mEditor = sharedPref.edit();
                                    mEditor.putString("id", id);
                                    mEditor.putString("password", password);
                                    mEditor.commit();
                                    Intent intent =
                                            new Intent(LoginActivity.this, HomeActivity.class);
                                    startActivity(intent);
                                }
                            }, new ErrorListener() {

                                @Override
                                public void onErrorResponse(VolleyError error) {}
                            });
            RequestUtil.getInstance().addToRequestQueue(request);
        }
    }
}
