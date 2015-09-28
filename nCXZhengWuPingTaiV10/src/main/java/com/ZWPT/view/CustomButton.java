package com.ZWPT.view;

import android.view.MotionEvent;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;

public class CustomButton extends Button {

	public CustomButton(Context context) {
		this(context, null);
	}

	public CustomButton(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public CustomButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		Drawable drawable = this.getBackground();
		if (this.getTag().toString().equals("H")) {
			int width = this.getWidth();
			int height = width * drawable.getIntrinsicHeight()
					/ drawable.getIntrinsicWidth();
			LayoutParams lp = (LayoutParams) this.getLayoutParams();
			lp.height = height;
			this.setLayoutParams(lp);
		}
		if (this.getTag().toString().toString().equals("V")) {
			int height = this.getHeight();
			int width = height * drawable.getIntrinsicWidth()
					/ drawable.getIntrinsicHeight();
			LayoutParams lp = (LayoutParams) this.getLayoutParams();
			lp.width = width;
			this.setLayoutParams(lp);
		}
	}
}
