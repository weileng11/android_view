package com.viewstudy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.viewstudy.ui.NumBerAct;

public class MainActivity extends AppCompatActivity
{
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		findViewById(R.id.number).setOnClickListener(new viewTest());
	}
	
	public class viewTest implements View.OnClickListener
	{
		@Override
		public void onClick(View v){
			switch(v.getId()){
			case R.id.number:
				showActivity(MainActivity.this,NumBerAct.class);
				break;
			//case R.id.view2:
			//	showActivity(MainActivity.this,ActViewPath2.class);
			//	break;
			//case R.id.view3:
			//	showActivity(MainActivity.this,ActViewPathRxxx.class);
			//	break;
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
