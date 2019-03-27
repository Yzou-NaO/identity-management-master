package com.nao.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.nao.myapplication.R;
import com.nao.util.Utils;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ChangeActivity extends BaseActivity implements OnClickListener {
    @BindView(R.id.txt_title)
    TextView txt_title;

    @BindView(R.id.txt_left)
    TextView txt_left;

    @BindView(R.id.img_back)
    ImageView img_back;

    @BindView(R.id.et_change)
    EditText et_change;

    @BindView(R.id.img_right)
    ImageView img_right;

    @BindView(R.id.txt_right)
    TextView txt_right;

    @BindDrawable(R.drawable.btn_selector_green)
    Drawable btn_enable_green;

    @BindDrawable(R.drawable.btn_disable_green)
    Drawable btn_disable_green;

    private String origin;//原始数据

    ChangeActivity activity = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change);
        ButterKnife.bind(this);
        initViews();
        setOnListener();
        textListenerInit();
    }

    private void initViews() {
        origin = getIntent().getStringExtra("origin");
        txt_title.setVisibility(View.INVISIBLE);
        String type = getIntent().getStringExtra("type");
        txt_left.setText("更改" + type);
        et_change.setText(origin);
        txt_left.setVisibility(View.VISIBLE);
        img_back.setVisibility(View.VISIBLE);
        txt_right.setVisibility(View.VISIBLE);
        txt_right.setText("保存");
        img_right.setImageDrawable(btn_disable_green);
        img_right.setVisibility(View.VISIBLE);
    }

    private void setOnListener() {
        img_back.setOnClickListener(this);
    }

    private void setOnListener2(){
        img_right.setOnClickListener(this);
        img_right.setImageDrawable(btn_enable_green);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                Utils.finish(this);
                break;
            case R.id.img_right:
                Utils.finish(this);
                break;
            default:
                break;
        }
    }

    public void textListenerInit() {
        et_change.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                setOnListener2();
            }
        });
    }
}
