package com.viewstudy.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.*;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import com.viewstudy.R;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * @author: ${bruce}
 * @project: android_view
 * @package: com.viewstudy.view （1）
 * @description:  https://blog.csdn.net/lmj623565791/article/details/24252901
 * @date: 2019/2/23  
 * @time: 17:57
 */
public class NumberView extends View
{
	/**
	 文本
	 */
	private String mTitleText;
	/**
	 文本的颜色
	 */
	private int mTitleTextColor;
	/**
	 文本的大小
	 */
	private int mTitleTextSize;
	/**
	 绘制时控制文本绘制的范围
	 */
	private Rect mBound;
	//画笔
	private Paint mPaint;
	private Paint mPaint2;
	
	public NumberView(Context context, AttributeSet attrs){
		this(context, attrs, 0);
	}
	
	public NumberView(Context context){
		this(context, null);
	}
	
	//获得自定义属性
	public NumberView(Context context, AttributeSet attrs, int defStyle){
		super(context, attrs, defStyle);
		TypedArray a=context.getTheme()
				.obtainStyledAttributes(attrs, R.styleable.CustomTitleView, defStyle, 0);
		int n=a.getIndexCount();
		for(int i=0; i<n; i++){
			int attr=a.getIndex(i);
			switch(attr){
			case R.styleable.CustomTitleView_titleText:
				mTitleText=a.getString(attr);
				break;
			case R.styleable.CustomTitleView_titleTextColor:
				// 默认颜色设置为黑色
				mTitleTextColor=a.getColor(attr, Color.BLACK);
				break;
			case R.styleable.CustomTitleView_titleTextSize:
				// 默认设置为16sp，TypeValue也可以把sp转化为px
				mTitleTextSize=a.getDimensionPixelSize(attr,
						(int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16,
								getResources().getDisplayMetrics()));
				break;
			}
		}
		a.recycle();
		//画笔设置颜色
		mPaint=new Paint();
		mPaint.setTextSize(mTitleTextSize);
		mPaint2=new Paint();
		mPaint2.setTextSize(mTitleTextSize);
		// mPaint.setColor(mTitleTextColor);
		//设置文本宽度
		mBound=new Rect();
		mPaint.getTextBounds(mTitleText, 0, mTitleText.length(), mBound);
		this.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v){
				mTitleText=randomText();
				postInvalidate();
			}
		});
	}
	
	private String randomText(){
		Random random=new Random();
		Set<Integer> set=new HashSet<Integer>();
		while(set.size()<4){
			int randomInt=random.nextInt(10);
			set.add(randomInt);
		}
		StringBuffer sb=new StringBuffer();
		for(Integer i : set){
			sb.append(""+i);
		}
		return sb.toString();
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
		//super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		int width=0;
		int height=0;
		/**
		 * 设置宽度
		 */
		int specMode=MeasureSpec.getMode(widthMeasureSpec);
		int specSize=MeasureSpec.getSize(widthMeasureSpec);
		switch(specMode){
		case MeasureSpec.EXACTLY:// 明确指定了
			width=getPaddingLeft()+getPaddingRight()+specSize;
			break;
		case MeasureSpec.AT_MOST:// 一般为WARP_CONTENT
			width=getPaddingLeft()+getPaddingRight()+mBound.width();
			break;
		}
		/**
		 * 设置高度
		 */
		specMode=MeasureSpec.getMode(heightMeasureSpec);
		specSize=MeasureSpec.getSize(heightMeasureSpec);
		switch(specMode){
		case MeasureSpec.EXACTLY:// 明确指定了
			height=getPaddingTop()+getPaddingBottom()+specSize;
			break;
		case MeasureSpec.AT_MOST:// 一般为WARP_CONTENT
			height=getPaddingTop()+getPaddingBottom()+mBound.height();
			break;
		}
		setMeasuredDimension(width, height);
	}
	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh){
		super.onSizeChanged(w, h, oldw, oldh);
	}
	
	@Override
	protected void onDraw(Canvas canvas){
		//绘制背景颜色
		mPaint.setColor(Color.YELLOW);
		mPaint2.setColor(Color.BLUE);
		////矩形
		canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);
		//圆
		//float width = getWidth();
		//float height = getHeight();
		////float radius = Math.min(width,height)/2;
		//float radius = 100;
		//canvas.drawCircle(width/2,height/2,radius,mPaint);
		//绘制扇形
		//RectF rect = new RectF(0f,0f,600f,600f);
		//canvas.drawArc(rect,0,60,true,mPaint);
		//canvas.drawArc(rect,60,30,true,mPaint2);
		//////绘制图标
		//Bitmap bitmap=BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
		//float width=(getWidth()-bitmap.getWidth())/2;
		//float height=(getHeight()-bitmap.getHeight())/2;
		//canvas.drawBitmap(bitmap, width, height, mPaint);
		mPaint.setColor(mTitleTextColor);
		Log.i("info", "宽度xxx"+(mBound.width()/2)+"高度"+mBound.height()/2);
		Log.i("info", "宽度"+getWidth()+"---"+(getWidth()/2-mBound.width()/2)+"高度"+getHeight());
		//具体位置  161-131
		canvas.drawText(mTitleText, getWidth()/2-mBound.width()/2, getHeight()/2+mBound.height()/2,
				mPaint);
	}
}