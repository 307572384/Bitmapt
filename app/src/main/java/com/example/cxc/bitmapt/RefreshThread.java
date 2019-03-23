package com.example.cxc.bitmapt;

import android.app.Activity;
import android.os.Message;
/**
 * Created by Kevein on 2019/3/23.19:37
 */
//刷新线程
 class RefreshThread implements Runnable {
	private final RefreshActivity mRefreshActivity;

	RefreshThread(RefreshActivity refreshActivity)
	{
		mRefreshActivity = refreshActivity;
	}
	@Override
	public void run() {
		while (!Thread.currentThread().isInterrupted())
		{
			Message message = new Message();
			message.what = RefreshActivity.handrlerMsg;

			mRefreshActivity.myHandler.sendMessage(message);
			try
			{
				Thread.sleep(100);
			}
			catch (InterruptedException e)
			{
				Thread.currentThread().interrupt();
			}
		}
	}
}
