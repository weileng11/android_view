package com.wl.view;

import android.content.Context;
import android.graphics.*;
import android.view.View;
import com.wl.R;

/**
 * @author: ${bruce}
 * @project: android_view
 * @package: com.wl.view
 * @description:
 * @date: 2019/1/24  
 * @time: 9:34
 */
public class MatrixSetPolyToPolyTest extends View
{
	
	private Bitmap mBitmap;             // 要绘制的图片
	private Matrix mPolyMatrix;         // 测试setPolyToPoly用的Matrix
	
	public MatrixSetPolyToPolyTest(Context context) {
		super(context);
		
		initBitmapAndMatrix();
	}
	
	private void initBitmapAndMatrix() {
		mBitmap = BitmapFactory.decodeResource(getResources(),
				R.drawable.ic_launcher);
		
		mPolyMatrix = new Matrix();
		
		
		float[] src = {0, 0,                                    // 左上
				mBitmap.getWidth(), 0,                          // 右上
				mBitmap.getWidth(), mBitmap.getHeight(),        // 右下
				0, mBitmap.getHeight()};                        // 左下
		
		float[] dst = {0, 0,                                    // 左上
				mBitmap.getWidth(), 400,                        // 右上
				mBitmap.getWidth(), mBitmap.getHeight() - 200,  // 右下
				0, mBitmap.getHeight()};                        // 左下
		
		// 核心要点
		mPolyMatrix.setPolyToPoly(src, 0, dst, 0, src.length >> 1); // src.length >> 1 为位移运算 相当于处以2
		
		// 此处为了更好的显示对图片进行了等比缩放和平移(图片本身有点大)
		mPolyMatrix.postScale(0.26f, 0.26f);
		mPolyMatrix.postTranslate(0,200);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		// 根据Matrix绘制一个变换后的图片
		canvas.drawBitmap(mBitmap, mPolyMatrix, null);
	}
}

