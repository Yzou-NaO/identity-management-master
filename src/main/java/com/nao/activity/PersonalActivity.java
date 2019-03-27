package com.nao.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.nao.myapplication.R;
import com.nao.util.Utils;

import org.apache.http.message.BasicNameValuePair;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PersonalActivity extends BaseActivity implements OnClickListener {
    @BindView(R.id.txt_title)
    TextView txt_title;

    @BindView(R.id.txt_left)
    TextView txt_left;

    @BindView(R.id.img_back)
    ImageView img_back;

    @BindView(R.id.txt_username)
    TextView txt_username;

    @BindView(R.id.detail_username)
    TextView detail_username;

    @BindView(R.id.txt_phonenumber)
    TextView txt_phonenumber;

    @BindView(R.id.detail_phonenumber)
    TextView detail_phonenumber;

    @BindView(R.id.txt_email)
    TextView txt_email;

    @BindView(R.id.detail_email)
    TextView detail_email;

    @BindView(R.id.txt_department)
    TextView txt_department;

    @BindView(R.id.detail_department)
    TextView detail_department;

    @BindView(R.id.txt_changepassword)
    TextView txt_changepassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
        ButterKnife.bind(this);
        initViews();
        setOnListener();
    }

    private void initViews(){
        txt_title.setVisibility(View.INVISIBLE);
        txt_left.setText("个人信息");
        txt_left.setVisibility(View.VISIBLE);
        img_back.setVisibility(View.VISIBLE);
        detail_username.setText(Utils.getValue(this,"username"));
        detail_phonenumber.setText(Utils.getValue(this,"phonenumber"));
        detail_email.setText(Utils.getValue(this,"email"));
        detail_department.setText(Utils.getValue(this,"department"));
    }

    private void setOnListener() {
        img_back.setOnClickListener(this);
        txt_username.setOnClickListener(this);
        txt_phonenumber.setOnClickListener(this);
        txt_email.setOnClickListener(this);
        txt_department.setOnClickListener(this);
        txt_changepassword.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()) {
            case R.id.img_back:
                Utils.finish(this);
                break;
            case R.id.txt_username:
                Utils.start_Activity(this,ChangeActivity.class,new BasicNameValuePair("type","用户名"),new BasicNameValuePair("origin",detail_username.getText().toString()));
                break;
            case R.id.txt_phonenumber:
                Utils.start_Activity(this,ChangeActivity.class,new BasicNameValuePair("type","手机"),new BasicNameValuePair("origin",detail_phonenumber.getText().toString()));
                break;
            case R.id.txt_email:
                Utils.start_Activity(this,ChangeActivity.class,new BasicNameValuePair("type","邮箱"),new BasicNameValuePair("origin",detail_email.getText().toString()));
                break;
            case R.id.txt_department:
                Utils.start_Activity(this,ChangeActivity.class,new BasicNameValuePair("type","部门"),new BasicNameValuePair("origin",detail_department.getText().toString()));
                break;
            case R.id.txt_changepassword:
                Utils.start_Activity(this,ChangeActivity.class,new BasicNameValuePair("type","密码"));
                break;
            default:
                break;
        }
    }
}
