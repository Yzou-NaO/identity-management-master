package com.nao.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.nao.myapplication.R;
import com.nao.presenter.LoginPresenter;
import com.nao.util.Utils;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends BaseActivity {
    @BindView(R.id.txt_title)
    TextView txt_title;

    @BindView(R.id.et_username)
    EditText userText;

    @BindView(R.id.et_password)
    EditText passwordText;

    @BindView(R.id.btn_login)
    Button login;

    @BindView(R.id.img_right)
    ImageView img_right;

    @BindDrawable(R.drawable.btn_selector_green)
    Drawable btn_enable_green;

    @BindDrawable(R.drawable.btn_disable_green)
    Drawable btn_disable_green;

    private LoginPresenter loginPresenter = new LoginPresenter(this);

    private int usernameListener = 0;

    private int passwordListener = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        //img_right.setVisibility(View.VISIBLE);
        isLogin();
        textListenerInit();
        txt_title.setText("欢迎登陆系统");
        txt_title.setVisibility(View.VISIBLE);
        login.setOnClickListener(v -> loginPresenter.login());
        img_right.setOnClickListener(v -> new IntentIntegrator(this).setCaptureActivity(ScanQRCodeActivity.class).initiateScan());
    }

    public void textListenerInit() {
        userText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    usernameListener = 1;
                } else {
                    usernameListener = 0;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (usernameListener == 1 && passwordListener == 1) {
                    login.setBackground(btn_enable_green);
                    login.setEnabled(true);
                } else {
                    login.setBackground(btn_disable_green);
                    login.setEnabled(false);
                }
            }
        });

        passwordText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    passwordListener = 1;
                } else {
                    passwordListener = 0;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (usernameListener == 1 && passwordListener == 1) {
                    login.setBackground(btn_enable_green);
                    login.setEnabled(true);
                } else {
                    login.setBackground(btn_disable_green);
                    login.setEnabled(false);
                }
            }
        });
    }

    public void isLogin() {
        if (Utils.hasValue(this, "username")) {
            toMainActivty();
        }
    }

    public String getUserName() {
        return userText.getText().toString();
    }

    public String getPassWord() {
        return passwordText.getText().toString();
    }

    public void toMainActivty() {
        Utils.start_Activity(this, MainActivity.class);
        finish();
    }

    public void showFailedError() {
        Toast.makeText(this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Log.d(getClass().getName(), "Cancelled");
                Toast.makeText(this, "扫描结果为空", Toast.LENGTH_LONG).show();
            } else {
                String s = result.getContents();
                String username = s.substring(15, s.indexOf('?'));
                String secretBase32 = s.substring(s.indexOf('=') + 1);
                userText.setText(username);
                passwordText.setText(secretBase32);
                loginPresenter.login();
            }
        }
    }
}
