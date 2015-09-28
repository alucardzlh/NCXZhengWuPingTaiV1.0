package com.ZWPT.view;

import com.ZWPT.activity.BaseActivity;
import com.ZWPT.activity.R;
import com.ZWPT.data.Constants;
import com.ZWPT.sliding.SlidingFragmentActivity;
import com.ZWPT.sliding.SlidingMenu;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class CustomTitle extends RelativeLayout {

	private Context context;

	public CustomTitle(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		init();
	}

	private void init() {
		LayoutInflater inflater = LayoutInflater.from(context);
		inflater.inflate(R.layout.custom_title, this);
		LinearLayout ll = (LinearLayout) findViewById(R.id.ll_menu);
		ImageButton IB = (ImageButton) findViewById(R.id.title_menu);
		ll.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// SlidingMenu sm = ((BaseActivity) context).getSlidingMenu();
				// sm.showMenu();
				((BaseActivity) context).toggle();
			}
		});
		IB.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// SlidingMenu sm = ((BaseActivity) context).getSlidingMenu();
				// sm.showMenu();
				((BaseActivity) context).toggle();
			}
		});
		// ImageButton ib_search = (ImageButton)findViewById(R.id.title_search);
		// if(Constants.SHOWSEARCH_BUTTON){
		// ib_search.setVisibility(View.GONE);
		// }else{
		// ib_search.setVisibility(View.VISIBLE);
		// }
	}

}
