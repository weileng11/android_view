package com.wl.view;

import android.content.Context;
import android.graphics.*;
import android.os.Build;
import android.support.annotation.RequiresApi;
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
public class PathTestViewRxxx extends View
{
	private int mWidth=0;
	private int mHeight=0;
	// 创建画笔
	Paint mPaint = new Paint();
	
	public PathTestViewRxxx(Context context, AttributeSet attrs)
	{
		this(context, attrs, 0);
	}
	
	public PathTestViewRxxx(Context context)
	{
		this(context, null);
	}
	
	//获得自定义属性
	public PathTestViewRxxx(Context context, AttributeSet attrs, int defStyle){
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
	
	@RequiresApi(api=Build.VERSION_CODES.KITKAT)
	@Override
	protected void onDraw(Canvas canvas){
		super.onDraw(canvas);
		//线
		//1. lineTo
		// 移动坐标系到屏幕中心(宽高数据在onSizeChanged中获取)
		//canvas.translate(mWidth/2,mHeight/2);
		Path path = new Path() ;
		
		//直线
		//path.moveTo(100,100);
		//path.lineTo(100,200);
		
		//斜线
		//path.moveTo(100,100);
		//path.rLineTo(100,200);
		
		//mPaint.setStyle(Paint.Style.FILL);                   // 设置画布模式为填充
		////path.setFillType(Path.FillType.EVEN_ODD);                   // 设置Path填充模式为 奇偶规则
		//path.setFillType(Path.FillType.INVERSE_EVEN_ODD);            // 反奇偶规则
		//path.addRect(-200,-200,200,200, Path.Direction.CW);         // 给Path中添加一个矩形
		
		
		//// 添加小正方形 (通过这两行代码来控制小正方形边的方向,从而演示不同的效果)
		//// path.addRect(-200, -200, 200, 200, Path.Direction.CW);
		//path.addRect(-200, -200, 200, 200, Path.Direction.CCW);
		//// 添加大正方形
		//path.addRect(-400, -400, 400, 400, Path.Direction.CCW);
		//path.setFillType(Path.FillType.WINDING);                    // 设置Path填充模式为非零环绕规则
		
		//布尔操作
		//mPaint.setStyle(Paint.Style.FILL);                   // 设置画布模式为填充
		//Path path1 = new Path();
		//Path path2 = new Path();
		//Path path3 = new Path();
		//Path path4 = new Path();
		//path1.addCircle(0, 0, 200, Path.Direction.CW);
		//path2.addRect(0, -200, 200, 200, Path.Direction.CW);
		//path3.addCircle(0, -100, 100, Path.Direction.CW);
		//path4.addCircle(0, 100, 100, Path.Direction.CCW);
		//path1.op(path2, Path.Op.DIFFERENCE);
		//path1.op(path3, Path.Op.UNION);
		//path1.op(path4, Path.Op.DIFFERENCE);
		//canvas.drawPath(path1,mPaint);
		
		
		////布尔操作2
		//showPath2(canvas);
		//布尔操作3
		showPath3(canvas);
		//canvas.drawPath(path,mPaint);
	}
	
	@RequiresApi(api=Build.VERSION_CODES.KITKAT)
	public void showPath2(Canvas canvas){
		int x = 80;
		int r = 100;
		
		canvas.translate(250,0);
		
		Path path1 = new Path();
		Path path2 = new Path();
		Path pathOpResult = new Path();
		
		path1.addCircle(-x, 0, r, Path.Direction.CW);
		path2.addCircle(x, 0, r, Path.Direction.CW);
		
		pathOpResult.op(path1,path2, Path.Op.DIFFERENCE);
		canvas.translate(0, 200);
		canvas.drawText("DIFFERENCE", 240,0,mPaint);
		canvas.drawPath(pathOpResult,mPaint);
		
		pathOpResult.op(path1,path2, Path.Op.REVERSE_DIFFERENCE);
		canvas.translate(0, 300);
		canvas.drawText("REVERSE_DIFFERENCE", 240,0,mPaint);
		canvas.drawPath(pathOpResult,mPaint);
		
		pathOpResult.op(path1,path2, Path.Op.INTERSECT);
		canvas.translate(0, 300);
		canvas.drawText("INTERSECT", 240,0,mPaint);
		canvas.drawPath(pathOpResult,mPaint);
		
		pathOpResult.op(path1,path2, Path.Op.UNION);
		canvas.translate(0, 300);
		canvas.drawText("UNION", 240,0,mPaint);
		canvas.drawPath(pathOpResult,mPaint);
		
		pathOpResult.op(path1,path2, Path.Op.XOR);
		canvas.translate(0, 300);
		canvas.drawText("XOR", 240,0,mPaint);
		canvas.drawPath(pathOpResult,mPaint);
	}
	
	@RequiresApi(api=Build.VERSION_CODES.KITKAT)
	public void showPath3(Canvas canvas){
		// 移动canvas,mViewWidth与mViewHeight在 onSizeChanged 方法中获得
		canvas.translate(mWidth/2,mHeight/2);
		
		RectF rect1 = new RectF();              // 存放测量结果的矩形
		
		Path path = new Path();                 // 创建Path并添加一些内容
		path.lineTo(100,-50);
		path.lineTo(100,50);
		path.close();
		path.addCircle(-100,0,100, Path.Direction.CW);
		
		path.computeBounds(rect1,true);         // 测量Path
		
		canvas.drawPath(path,mPaint);    // 绘制Path
		
		mPaint.setStyle(Paint.Style.STROKE);
		mPaint.setColor(Color.RED);
		canvas.drawRect(rect1,mPaint);   // 绘制边界
	}
}
