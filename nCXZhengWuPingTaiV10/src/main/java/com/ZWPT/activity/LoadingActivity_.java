//
// DO NOT EDIT THIS FILE, IT HAS BEEN GENERATED USING AndroidAnnotations.
//


package com.ZWPT.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import com.ZWPT.activity.R.layout;
import com.ZWPT.data.DataHelper_;
import com.ZWPT.data.InfoFile_;

public final class LoadingActivity_
    extends LoadingActivity
{


    @Override
    public void onCreate(Bundle savedInstanceState) {
        init_(savedInstanceState);
        super.onCreate(savedInstanceState);
        setContentView(layout.layout_loading);
    }

    private void init_(Bundle savedInstanceState) {
        infoFile = new InfoFile_(this);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        dataHelper = DataHelper_.getInstance_(this);
        init();
    }

    private void afterSetContentView_() {
        ((DataHelper_) dataHelper).afterSetContentView_();
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

    public static LoadingActivity_.IntentBuilder_ intent(Context context) {
        return new LoadingActivity_.IntentBuilder_(context);
    }

    public static class IntentBuilder_ {

        private Context context_;
        private final Intent intent_;

        public IntentBuilder_(Context context) {
            context_ = context;
            intent_ = new Intent(context, LoadingActivity_.class);
        }

        public Intent get() {
            return intent_;
        }

        public LoadingActivity_.IntentBuilder_ flags(int flags) {
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
