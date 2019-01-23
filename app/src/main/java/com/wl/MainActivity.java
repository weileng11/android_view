package com.wl;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import com.wl.ui.ActView1;
import com.wl.ui.ActViewPath2;
import com.wl.ui.ActViewPathRxxx;

public class MainActivity extends AppCompatActivity
{
	private Button view1;
	private Button view2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		findViewById(R.id.view1).setOnClickListener(new viewTest());
		findViewById(R.id.view2).setOnClickListener(new viewTest());
		findViewById(R.id.view3).setOnClickListener(new viewTest());
	}
	
	public class viewTest implements View.OnClickListener
	{
		@Override
		public void onClick(View v){
			switch(v.getId()){
			case R.id.view1:
				showActivity(MainActivity.this,ActView1.class);
				break;
			case R.id.view2:
				showActivity(MainActivity.this,ActViewPath2.class);
				break;
			case R.id.view3:
				showActivity(MainActivity.this,ActViewPathRxxx.class);
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
