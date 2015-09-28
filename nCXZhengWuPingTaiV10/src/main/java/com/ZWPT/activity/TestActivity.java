package com.ZWPT.activity;

import java.net.URI;

import android.app.Activity;
import android.content.UriMatcher;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

public class TestActivity extends Activity {
	ImageView iv;
	Uri uri;    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_activity);
		iv=(ImageView) findViewById(R.id.imageView1);
		
		
	}

	
	
	
	
}
