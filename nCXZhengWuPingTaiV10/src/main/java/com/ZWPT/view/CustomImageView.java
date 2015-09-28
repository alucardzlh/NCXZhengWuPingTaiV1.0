package com.ZWPT.view;

import android.view.MotionEvent;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;

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

public class CustomImageView extends ImageView {

	public CustomImageView(Context context) {
		this(context, null);
	}

	public CustomImageView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public CustomImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		Drawable drawable = this.getBackground();
		if (drawable == null)
			return;
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
		if (this.getTag().toString().toString().equals("P")) {
			int width = drawable.getIntrinsicWidth(); // �?drawable 的长�?
			int height = drawable.getIntrinsicHeight();
			Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
					: Bitmap.Config.RGB_565; // �?drawable 的颜色格�?
			Bitmap bitmap = Bitmap.createBitmap(width, getHeight(), config); // 建立对应
																				// bitmap
			Canvas canvass = new Canvas(bitmap); // 建立对应 bitmap 的画�?
			drawable.setBounds(0, 0, width, getHeight());
			drawable.draw(canvass);
			for (int i = 0; i < (getWidth() / width + 1); i++)
				canvas.drawBitmap(bitmap, new Rect(0, 0, width, getHeight()),
						new Rect(width * i, 0, width * (i + 1), getHeight()),
						null);

		}
	}
}
