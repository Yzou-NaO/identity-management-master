package com.nao.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.nao.myapplication.R;
import com.nao.util.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingActivity extends BaseActivity implements OnClickListener {
    @BindView(R.id.txt_title)
    TextView txt_title;

    @BindView(R.id.img_back)
    ImageView img_back;

    @BindView(R.id.btnexit)
    Button btnexit;

    @BindView(R.id.txt_left)
    TextView txt_left;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        initViews();
        setOnListener();
    }


    public void initViews() {
        txt_title.setVisibility(View.INVISIBLE);
        img_back.setVisibility(View.VISIBLE);
        txt_left.setText("设置");
        txt_left.setVisibility(View.VISIBLE);
    }

    private void setOnListener() {
        img_back.setOnClickListener(this);
        btnexit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                Utils.finish(this);
                break;
            case R.id.btnexit:
                Utils.RemoveValue(this, "username");
                Utils.RemoveValue(this, "phonenumber");
                Utils.RemoveValue(this, "email");
                Utils.RemoveValue(this, "department");
                Utils.RemoveValue(this, "otpsk");
                application.removeALLActivity_();
                Utils.start_Activity(this,LoginActivity.class);
                break;
            default:
                break;
        }
    }
}
