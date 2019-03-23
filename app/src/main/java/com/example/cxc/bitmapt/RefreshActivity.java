package com.example.cxc.bitmapt;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.MotionEvent;

/**
 * Created by Kevein on 2019/3/23.19:38
 * 自定义自动刷新view
 */

public class RefreshActivity extends Activity {
	static final int handrlerMsg = 0x001;
	private ViewDrawer myView = null;
	private Context mContext = this;
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//实例化对象
		this.myView = new ViewDrawer(this);
		//设置显示我们自定义的View
		setContentView(myView);
		//开启线程
		new Thread(new RefreshThread(this)).start();

	}
	/**
	 * 监听Back键按下事件,方法1:
	 * 注意:
	 * super.onBackPressed()会自动调用finish()方法,关闭
	 * 当前Activity.
	 * 若要屏蔽Back键盘,注释该行代码即可
	 */
/*	@Override
	public void onBackPressed() {
		super.onBackPressed();
		System.out.println("按下了back键   onBackPressed()");
	}*/


	// 等待消息并处理
	Handler myHandler = new Handler()
	{
		public void handleMessage(Message msg)
		{
			switch (msg.what)
			{
				case RefreshActivity.handrlerMsg:
					myView.invalidate();
					break;
			}
			super.handleMessage(msg);
		}
	};
	/**
	 * 可以将GameThread类这样写 同样可以更新界面，并且不在需要 Handler在接受消息 class GameThread
	 * implements Runnable { public void run() { while
	 * (!Thread.currentThread().isInterrupted()) { try { Thread.sleep(100); }
	 * catch (InterruptedException e) { Thread.currentThread().interrupt(); }
	 * //使用postInvalidate可以直接在线程中更新界面 mGameView.postInvalidate(); } } }
	 */
	public boolean onTouchEvent(MotionEvent event)
	{
		return true;
	}

	// 按键按下事件
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		if ((keyCode == KeyEvent.KEYCODE_BACK)) {
			System.out.println("按下了back键   onKeyDown()");
			return false;
		}else {
			return super.onKeyDown(keyCode, event);
		}
	}

	// 按键弹起事件
	public boolean onKeyUp(int keyCode, KeyEvent event)
	{
		switch (keyCode)
		{
			// 上方向键
			case KeyEvent.KEYCODE_DPAD_UP:
				myView.y -= 3;
				break;
			// 下方向键
			case KeyEvent.KEYCODE_DPAD_DOWN:
				myView.y += 3;
				break;
		}
		return false;
	}

	public boolean onKeyMultiple(int keyCode, int repeatCount, KeyEvent event)
	{
		return true;
	}
}
