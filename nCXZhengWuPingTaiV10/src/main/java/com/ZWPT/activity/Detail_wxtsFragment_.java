//
// DO NOT EDIT THIS FILE, IT HAS BEEN GENERATED USING AndroidAnnotations.
//


package com.ZWPT.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import com.ZWPT.activity.R.layout;
import com.ZWPT.data.DataHelper_;
import com.ZWPT.data.InfoFile_;

public final class Detail_wxtsFragment_
    extends Detail_wxtsFragment
{

    private View contentView_;

    private void init_(Bundle savedInstanceState) {
        infoFile_ = new InfoFile_(getActivity());
        dataHelper = DataHelper_.getInstance_(getActivity());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        init_(savedInstanceState);
        super.onCreate(savedInstanceState);
    }

    private void afterSetContentView_() {
        tv_name = ((TextView) findViewById(com.ZWPT.activity.R.id.tv_name));
        tvInfo = ((TextView) findViewById(com.ZWPT.activity.R.id.tvInfo));
        tv_2 = ((TextView) findViewById(com.ZWPT.activity.R.id.tv_2));
        lv_bltj_fuhe = ((ListView) findViewById(com.ZWPT.activity.R.id.lv_bltj_fuhe));
        tv_3 = ((TextView) findViewById(com.ZWPT.activity.R.id.tv_3));
        lv_tjcl_fuhe = ((ListView) findViewById(com.ZWPT.activity.R.id.lv_tjcl_fuhe));
        tv_1 = ((TextView) findViewById(com.ZWPT.activity.R.id.tv_1));
        ((DataHelper_) dataHelper).afterSetContentView_();
        initView();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        contentView_ = super.onCreateView(inflater, container, savedInstanceState);
        if (contentView_ == null) {
            contentView_ = inflater.inflate(layout.fragment_detail_wxts, container, false);
        }
        return contentView_;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        afterSetContentView_();
    }

    public View findViewById(int id) {
        if (contentView_ == null) {
            return null;
        }
        return contentView_.findViewById(id);
    }

    public static Detail_wxtsFragment_.FragmentBuilder_ builder() {
        return new Detail_wxtsFragment_.FragmentBuilder_();
    }

    public static class FragmentBuilder_ {

        private Bundle args_;

        private FragmentBuilder_() {
            args_ = new Bundle();
        }

        public Detail_wxtsFragment build() {
            Detail_wxtsFragment_ fragment_ = new Detail_wxtsFragment_();
            fragment_.setArguments(args_);
            return fragment_;
        }

    }

}