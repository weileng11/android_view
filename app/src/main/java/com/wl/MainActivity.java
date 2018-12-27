package com.wl;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.wl.ui.ActView1;

public class MainActivity extends AppCompatActivity
{
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		findViewById(R.id.view1).setOnClickListener(new viewTest());
	}
	
	public class viewTest implements View.OnClickListener
	{
		@Override
		public void onClick(View v){
			switch(v.getId()){
			case R.id.view1:
				showActivity(MainActivity.this,ActView1.class);
				break;
			}
		}
	}
	
	
	/**
	 Activity跳转
	 */
	public void showActivity(Activity aty, Class<?> cls){
		Intent intent=new Intent();
		intent.setClass(aty, cls);
		aty.startActivity(intent);
	}
}
