package com.wl.view;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author: ${bruce}
 * @project: android_view
 * @package: com.wl.view
 * @description:
 * @date: 2019/1/17  
 * @time: 17:16
 */
public class PathTestView extends View
{
	private int mWidth=0;
	private int mHeight=0;
	// 创建画笔
	Paint mPaint = new Paint();
	
	public PathTestView(Context context, AttributeSet attrs)
	{
		this(context, attrs, 0);
	}
	
	public PathTestView(Context context)
	{
		this(context, null);
	}
	
	//获得自定义属性
	public PathTestView(Context context, AttributeSet attrs, int defStyle){
		super(context, attrs, defStyle);
		
		mPaint.setColor(Color.BLACK);           // 画笔颜色 - 黑色
		mPaint.setStyle(Paint.Style.STROKE);    // 填充模式 - 描边
		mPaint.setStrokeWidth(10);              // 边框宽度 - 10
	}
	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh){
		super.onSizeChanged(w, h, oldw, oldh);
		mWidth=w;
		mHeight=h;
	}
	
	@Override
	protected void onDraw(Canvas canvas){
		super.onDraw(canvas);
		//线
		//1. lineTo
		// 移动坐标系到屏幕中心(宽高数据在onSizeChanged中获取)
		canvas.translate(mWidth/2,mHeight/2);
		Path path = new Path() ;
		
		////lineTo两点确定一条直线
		//path.lineTo(200, 200);
		//path.lineTo(200,0);
		//// 绘制Path
		
		//2. lineTo+moveTo
		//path.lineTo(200, 200);
		//path.moveTo(200,100);//moveTo只改变下次操作的起点,起点为200,100,中间有空隙
		//path.lineTo(200,0);
		
		//3.lineTo+setLastPoint
		//path.lineTo(200, 200);
		//path.setLastPoint(200,100); //setLastPoint是重置上一次操作的最后一个点,200,100
		//path.lineTo(200,0);
		
		//4.lineTo+close
		//path.lineTo(200, 200);
		//path.lineTo(200,0);
		//path.close(); //close方法用于连接当前最后一个点和最初的一个点(如果两个点不重合的话)，最终形成一个封闭的图形。
		
		// 圆形
		//public void addCircle (float x, float y, float radius, Path.Direction dir)
		//// 椭圆
		//public void addOval (RectF oval, Path.Direction dir)
		//// 矩形
		//public void addRect (float left, float top, float right, float bottom, Path.Direction dir)
		//public void addRect (RectF rect, Path.Direction dir)
		//// 圆角矩形
		//public void addRoundRect (RectF rect, float[] radii, Path.Direction dir)
		//public void addRoundRect (RectF rect, float rx, float ry, Path.Direction dir)
		
		//5.矩形
		//path.addRect(-200,-200,200,200, Path.Direction.CW); //	顺时针
		//path.setLastPoint(-100,100);                // <-- 重置最后一个点的位置 (必须小于矩形绘制的区域大小，否则不显示)
		
		//path.addRect(-200,-200,200,200, Path.Direction.CCW);  //	逆时针
		//path.setLastPoint(-100,100);                // <-- 重置最后一个点的位置(必须小于矩形绘制的区域大小，否则不显示)
		
		// 第二类(Path)
		// path
		//public void addPath (Path src)
		//public void addPath (Path src, float dx, float dy)
		//public void addPath (Path src, Matrix matrix)
		
		//6.addPath 矩形+圆
		//canvas.scale(1,-1);                         // <-- 注意 翻转y坐标轴
		//Path src = new Path();
		//path.addRect(-200,-200,200,200, Path.Direction.CW);
		//src.addCircle(0,0,100, Path.Direction.CW);
		//path.addPath(src,0,200);
		//mPaint.setColor(Color.BLACK);           // 绘制合并后的路径
		
		// 第三类(addArc与arcTo)
		// addArc
		//public void addArc (RectF oval, float startAngle, float sweepAngle)
		// arcTo
		//public void arcTo (RectF oval, float startAngle, float sweepAngle)
		//public void arcTo (RectF oval, float startAngle, float sweepAngle, boolean forceMoveTo)
		//oval 圆弧的外切矩形。 sweepAngle扫过角度(-360 <= sweepAngle <360)
		//startAngle 开始角度   forceMoveTo是否强制使用MoveTo
		
		//7.圆弧
		canvas.scale(1,-1);                         // <-- 注意 翻转y坐标轴
		path.lineTo(100,100);
		RectF oval = new RectF(0,0,300,300);
		path.addArc(oval,0,270);
		// path.arcTo(oval,0,270,true);             // <-- 和上面一句作用等价
		
		canvas.drawPath(path, mPaint);
	}
}
