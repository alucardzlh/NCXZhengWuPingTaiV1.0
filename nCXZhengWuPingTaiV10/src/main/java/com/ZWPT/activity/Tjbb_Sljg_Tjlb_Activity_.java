//
// DO NOT EDIT THIS FILE, IT HAS BEEN GENERATED USING AndroidAnnotations.
//


package com.ZWPT.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.ZWPT.activity.R.id;
import com.ZWPT.activity.R.layout;
import com.ZWPT.data.InfoFile_;
import com.googlecode.androidannotations.api.SdkVersionHelper;

public final class Tjbb_Sljg_Tjlb_Activity_
    extends Tjbb_Sljg_Tjlb_Activity
{


    @Override
    public void onCreate(Bundle savedInstanceState) {
        init_(savedInstanceState);
        super.onCreate(savedInstanceState);
        setContentView(layout.layout_tjbb_sljgtj);
    }

    private void init_(Bundle savedInstanceState) {
        infoFile_ = new InfoFile_(this);
    }

    private void afterSetContentView_() {
        yifafang3 = ((TextView) findViewById(id.yifafang3));
        buyushouli1 = ((TextView) findViewById(id.buyushouli1));
        tjlb_tjqk_ib3 = ((LinearLayout) findViewById(id.tjlb_tjqk_ib3));
        tjbb_tjqk = ((LinearLayout) findViewById(id.tjbb_tjqk));
        weifafang3 = ((TextView) findViewById(id.weifafang3));
        banjielv2 = ((TextView) findViewById(id.banjielv2));
        search_rll_fenlei = ((LinearLayout) findViewById(id.search_rll_fenlei));
        rl_title = ((RelativeLayout) findViewById(id.rl_title));
        buyushouli2 = ((TextView) findViewById(id.buyushouli2));
        yewuliang3 = ((TextView) findViewById(id.yewuliang3));
        chaoshi1 = ((TextView) findViewById(id.chaoshi1));
        yifafang1 = ((TextView) findViewById(id.yifafang1));
        banlishibai1 = ((TextView) findViewById(id.banlishibai1));
        search_rll_shenqingriqi_to = ((LinearLayout) findViewById(id.search_rll_shenqingriqi_to));
        chaoshi2 = ((TextView) findViewById(id.chaoshi2));
        weibanjie3 = ((TextView) findViewById(id.weibanjie3));
        title_search = ((ImageButton) findViewById(id.title_search));
        weifafang1 = ((TextView) findViewById(id.weifafang1));
        tv_search_3 = ((TextView) findViewById(id.tv_search_3));
        banlichenggong3 = ((TextView) findViewById(id.banlichenggong3));
        banjielv1 = ((TextView) findViewById(id.banjielv1));
        ll_tjbb_tjqk_3 = ((LinearLayout) findViewById(id.ll_tjbb_tjqk_3));
        buyushouli3 = ((TextView) findViewById(id.buyushouli3));
        tv_search_2 = ((TextView) findViewById(id.tv_search_2));
        banlichenggong1 = ((TextView) findViewById(id.banlichenggong1));
        weibanjie2 = ((TextView) findViewById(id.weibanjie2));
        banlishibai3 = ((TextView) findViewById(id.banlishibai3));
        ll_tjbb_tjqk_2 = ((LinearLayout) findViewById(id.ll_tjbb_tjqk_2));
        weifafang2 = ((TextView) findViewById(id.weifafang2));
        search_rll_all = ((LinearLayout) findViewById(id.search_rll_all));
        tjlb_tjqk_ib2 = ((LinearLayout) findViewById(id.tjlb_tjqk_ib2));
        chaoshi3 = ((TextView) findViewById(id.chaoshi3));
        btn_search = ((Button) findViewById(id.btn_search));
        banjielv3 = ((TextView) findViewById(id.banjielv3));
        search_rll_shenqingriqi = ((LinearLayout) findViewById(id.search_rll_shenqingriqi));
        tv_search_1 = ((TextView) findViewById(id.tv_search_1));
        yifafang2 = ((TextView) findViewById(id.yifafang2));
        weibanjie1 = ((TextView) findViewById(id.weibanjie1));
        banlishibai2 = ((TextView) findViewById(id.banlishibai2));
        ll_tjbb_tjqk_1 = ((LinearLayout) findViewById(id.ll_tjbb_tjqk_1));
        yewuliang1 = ((TextView) findViewById(id.yewuliang1));
        banlichenggong2 = ((TextView) findViewById(id.banlichenggong2));
        yewuliang2 = ((TextView) findViewById(id.yewuliang2));
        tjlb_tjqk_ib1 = ((LinearLayout) findViewById(id.tjlb_tjqk_ib1));
        initView();
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        afterSetContentView_();
    }

    @Override
    public void setContentView(View view, LayoutParams params) {
        super.setContentView(view, params);
        afterSetContentView_();
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        afterSetContentView_();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (((SdkVersionHelper.getSdkInt()< 5)&&(keyCode == KeyEvent.KEYCODE_BACK))&&(event.getRepeatCount() == 0)) {
            onBackPressed();
        }
        return super.onKeyDown(keyCode, event);
    }

    public static Tjbb_Sljg_Tjlb_Activity_.IntentBuilder_ intent(Context context) {
        return new Tjbb_Sljg_Tjlb_Activity_.IntentBuilder_(context);
    }

    public static class IntentBuilder_ {

        private Context context_;
        private final Intent intent_;

        public IntentBuilder_(Context context) {
            context_ = context;
            intent_ = new Intent(context, Tjbb_Sljg_Tjlb_Activity_.class);
        }

        public Intent get() {
            return intent_;
        }

        public Tjbb_Sljg_Tjlb_Activity_.IntentBuilder_ flags(int flags) {
            intent_.setFlags(flags);
            return this;
        }

        public void start() {
            context_.startActivity(intent_);
        }

        public void startForResult(int requestCode) {
            if (context_ instanceof Activity) {
                ((Activity) context_).startActivityForResult(intent_, requestCode);
            } else {
                context_.startActivity(intent_);
            }
        }

    }

}