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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ZWPT.activity.R.id;
import com.ZWPT.activity.R.layout;
import com.ZWPT.data.DataHelper_;
import com.ZWPT.data.InfoFile_;
import com.googlecode.androidannotations.api.SdkVersionHelper;

public final class Wssq_Yslyw_Deny_Activity_
    extends Wssq_Yslyw_Deny_Activity
{


    @Override
    public void onCreate(Bundle savedInstanceState) {
        init_(savedInstanceState);
        super.onCreate(savedInstanceState);
        setContentView(layout.layout_yslyw_deny);
    }

    private void init_(Bundle savedInstanceState) {
        infoFile_ = new InfoFile_(this);
        dataHelper = DataHelper_.getInstance_(this);
    }

    private void afterSetContentView_() {
        tv_list_item_1 = ((TextView) findViewById(id.tv_list_item_1));
        tv_list_item_5 = ((TextView) findViewById(id.tv_list_item_5));
        ll_detail_btn_1 = ((LinearLayout) findViewById(id.ll_detail_btn_1));
        tv_list_item_4 = ((TextView) findViewById(id.tv_list_item_4));
        tv_list_item_3 = ((TextView) findViewById(id.tv_list_item_3));
        edt_search_1 = ((EditText) findViewById(id.edt_search_1));
        tv_list_item_2 = ((TextView) findViewById(id.tv_list_item_2));
        tvTitle_two_Name = ((TextView) findViewById(id.tvTitle_two_Name));
        btn_FanHui = ((Button) findViewById(id.btn_FanHui));
        btn_BuShouLi = ((Button) findViewById(id.btn_BuShouLi));
        btn_ShouLi = ((Button) findViewById(id.btn_ShouLi));
        ((DataHelper_) dataHelper).afterSetContentView_();
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

    public static Wssq_Yslyw_Deny_Activity_.IntentBuilder_ intent(Context context) {
        return new Wssq_Yslyw_Deny_Activity_.IntentBuilder_(context);
    }

    public static class IntentBuilder_ {

        private Context context_;
        private final Intent intent_;

        public IntentBuilder_(Context context) {
            context_ = context;
            intent_ = new Intent(context, Wssq_Yslyw_Deny_Activity_.class);
        }

        public Intent get() {
            return intent_;
        }

        public Wssq_Yslyw_Deny_Activity_.IntentBuilder_ flags(int flags) {
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
