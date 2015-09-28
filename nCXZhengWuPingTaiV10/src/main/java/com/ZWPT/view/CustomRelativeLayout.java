package com.ZWPT.view;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;

public class CustomRelativeLayout extends RelativeLayout {
	public int screenWidth;

	public CustomRelativeLayout(Context context) {
		this(context, null);
	}

	public CustomRelativeLayout(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public CustomRelativeLayout(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		screenWidth = wm.getDefaultDisplay().getWidth();// 屏幕宽度
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// TODO Auto-generated method stub
		super.onLayout(changed, l, t, r, b);
		int w = Integer.parseInt(this.getTag().toString().split("WH")[0]);
		int h = Integer.parseInt(this.getTag().toString().split("WH")[1]);

		int width = this.getWidth();
		int height = width * h / w;
		android.view.ViewGroup.LayoutParams lp = (android.view.ViewGroup.LayoutParams) this
				.getLayoutParams();
		lp.height = height;
		this.setPadding((int) (2.0 * screenWidth / 480.0),
				(int) (2.0 * screenWidth / 480.0),
				(int) (2.0 * screenWidth / 480.0),
				(int) (2.0 * screenWidth / 480.0));

		this.setLayoutParams(lp);
	}
}
