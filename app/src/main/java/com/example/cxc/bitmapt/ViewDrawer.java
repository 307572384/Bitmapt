package com.example.cxc.bitmapt;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by Kevein on 2019/3/23.18:38
 */

public class ViewDrawer extends View
{

	int  colorSwitch = 0;
	int  y = 300;
	public ViewDrawer(Context context) {
		super(context);
	}
	public void onDraw(Canvas canvas)
	{
		if(colorSwitch < 100)
		{
			colorSwitch++;
		}
		//绘图
		Paint mPaint = new Paint();
		switch (colorSwitch % 4)
		{
			case 0:
				mPaint.setColor(Color.BLUE);
				break;
			case 1:
				mPaint.setColor(Color.GREEN);
				break;
			case 2:
				mPaint.setColor(Color.RED);
				break;
			case 3:
				mPaint.setColor(Color.YELLOW);
				break;
			case 4:
				mPaint.setColor(Color.GREEN);
				break;
			case 5:
				mPaint.setColor(Color.CYAN);
				break;
				default:mPaint.setColor(Color.BLACK);
				break;

		}//矩形绘制
		canvas.drawRect((320-80)/2,y,(320-80)+80,y+40,mPaint);
	}
}
