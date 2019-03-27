package com.nao.fragment;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nao.activity.OtpActivity;
import com.nao.activity.PersonalActivity;
import com.nao.activity.SettingActivity;
import com.nao.myapplication.R;
import com.nao.util.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;

//我
public class Fragment_Profile extends Fragment implements OnClickListener {
    @BindView(R.id.tvname)
    TextView tvName;

    @BindView(R.id.view_user)
    View view_user;

    @BindView(R.id.txt_otp)
    View txt_otp;

    @BindView(R.id.txt_setting)
    View txt_setting;

    private Activity ctx;
    private View layout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (layout == null) {
            ctx = this.getActivity();
            layout = ctx.getLayoutInflater().inflate(R.layout.fragment_profile,
                    null);
            ButterKnife.bind(this,layout);
            initViews();
            setOnListener();
        } else {
            ViewGroup parent = (ViewGroup) layout.getParent();
            if (parent != null) {
                parent.removeView(layout);
            }
        }
        return layout;
    }

    private void initViews() {
        tvName.setText(Utils.getValue(ctx,"username"));
    }

    private void setOnListener() {
        view_user.setOnClickListener(this);
        txt_otp.setOnClickListener(this);
        txt_setting.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.view_user:
                Utils.start_Activity(ctx,PersonalActivity.class);
                break;
            case R.id.txt_otp:// 动态令牌
                Utils.start_Activity(ctx,OtpActivity.class);
                break;
            case R.id.txt_setting:// 设置
                Utils.start_Activity(ctx,SettingActivity.class);
                break;
            default:
                break;
        }
    }
}