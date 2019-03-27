package com.nao.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nao.bean.User;
import com.nao.myapplication.R;
import com.nao.util.TotpUtil;
import com.nao.util.Utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OtpActivity extends BaseActivity implements OnClickListener {
    //初始化标志
    boolean first = true;
    boolean end = false;

    //时间戳
    long time;

    @BindView(R.id.otp_code)
    TextView otp_code;

    @BindView(R.id.otp_time)
    ProgressBar otp_time;

    @BindView(R.id.txt_title)
    TextView txt_title;

    @BindView(R.id.img_back)
    ImageView img_back;

    @BindView(R.id.txt_left)
    TextView txt_left;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        ButterKnife.bind(this);
        initViews();
        setOnListener();
        refresh();
        new Thread(mRunnable).start();
    }

    public void initViews() {
        txt_title.setVisibility(View.INVISIBLE);
        img_back.setVisibility(View.VISIBLE);
        txt_left.setText("动态口令");
        txt_left.setVisibility(View.VISIBLE);
    }

    private void refresh() {
        //获取user信息
        Intent intent = getIntent();
        User user = new User();
        user.setUsername(Utils.getValue(this, "username"));
        user.setOtp_sk(Utils.getValue(this, "otpsk"));
        //生成otp
        otp_code.setText(TotpUtil.generate(user.getOtp_sk()));
    }

    //调用定时刷新函数
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            refresh();
        }
    };

    //实现定时刷新
    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            while (!end) {
                try {
                    //初始化
                    if (first) {
                        long X = 30;//时间间隔30s
                        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        df.setTimeZone(TimeZone.getTimeZone("UTC"));
                        long currentTime = System.currentTimeMillis() / 1000L;
                        time = currentTime % X * 1000;
                        first = false;
                    } else {
                        time = 0;
                    }
                    otp_time.setProgress((int) time / 1000);
                    while (time < 30000 && !end) {
                        Thread.sleep(1000);
                        time += 1000;
                        otp_time.setProgress((int) time / 1000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (!end) {
                    mHandler.sendMessage(mHandler.obtainMessage());
                }
            }
        }
    };

    private void setOnListener() {
        img_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                end = true;
                Utils.finish(this);
                break;
            default:
                break;
        }
    }
}
