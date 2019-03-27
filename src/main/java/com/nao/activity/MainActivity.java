package com.nao.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nao.fragment.Fragment_Blank;
import com.nao.fragment.Fragment_Profile;
import com.nao.myapplication.R;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;

public class MainActivity extends FragmentActivity {
    @BindView(R.id.txt_title)
    TextView txt_title;

    @BindViews({R.id.ib_unnamed1, R.id.ib_unnamed2, R.id.ib_unnamed3, R.id.ib_profile})
    ImageView[] imageButtons;

    @BindViews({R.id.tv_unnamed1, R.id.tv_unnamed2, R.id.tv_unnamed3, R.id.tv_profile})
    TextView[] textViews;

    Fragment_Blank blankfragment1;
    Fragment_Blank blankfragment2;
    Fragment_Blank blankfragment3;
    Fragment_Profile profilefragment;
    private Fragment[] fragments;
    private int index;
    private int currentTabIndex;// 当前fragment的index

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        App application = (App)getApplication();
        application.addActivity_(this);
        ButterKnife.bind(this);
        initTabView();
    }

    private void initTabView() {
        blankfragment1 = new Fragment_Blank();
        blankfragment2 = new Fragment_Blank();
        blankfragment3 = new Fragment_Blank();
        profilefragment = new Fragment_Profile();
        fragments = new Fragment[]{blankfragment1, blankfragment2, blankfragment3, profilefragment};
        imageButtons[0].setSelected(true);
        // 添加显示第一个fragment
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, blankfragment1)
                .add(R.id.fragment_container, blankfragment2)
                .add(R.id.fragment_container, blankfragment3)
                .add(R.id.fragment_container, profilefragment)
                .hide(blankfragment2).hide(blankfragment3)
                .hide(profilefragment).show(blankfragment1).commit();
        txt_title.setText("unused");
    }

    public void onTabClicked(View view) {
        switch (view.getId()) {
            case R.id.re_unnamed1:
                index = 0;
                txt_title.setText("unused");
                break;
            case R.id.re_unnamed2:
                index = 1;
                txt_title.setText("unused");
                break;
            case R.id.re_unnamed3:
                index = 2;
                txt_title.setText("unused");
                break;
            case R.id.re_profile:
                index = 3;
                txt_title.setText(R.string.me);
                break;
        }
        if (currentTabIndex != index) {
            FragmentTransaction trx = getSupportFragmentManager()
                    .beginTransaction();
            trx.hide(fragments[currentTabIndex]);
            if (!fragments[index].isAdded()) {
                trx.add(R.id.fragment_container, fragments[index]);
            }
            trx.show(fragments[index]).commit();
        }
        imageButtons[currentTabIndex].setSelected(false);
        // 把当前tab设为选中状态
        imageButtons[index].setSelected(true);
        textViews[currentTabIndex].setTextColor(0xFF999999);
        textViews[index].setTextColor(0xFF45C01A);
        currentTabIndex = index;
    }
}
