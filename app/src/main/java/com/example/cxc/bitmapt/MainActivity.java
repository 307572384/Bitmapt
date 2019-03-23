package com.example.cxc.bitmapt;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;

//图片查看器
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
	String[]     imageArray    = null;
	AssetManager assetsManager = null;
	int          currentImgNo  = 0;
	private Button    next;
	private ImageView image;
	private Button    pstlayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();


	}

	private void initView() {
		next = (Button) findViewById(R.id.next);
		image = (ImageView) findViewById(R.id.image);

		next.setOnClickListener(this);
		pstlayout = (Button) findViewById(R.id.pstlayout);
		pstlayout.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.next:
				try {
					assetsManager = getAssets();
					//获取/assets/目录下所有文件
					imageArray = assetsManager.list("");
				} catch (IOException e) {
					e.printStackTrace();
				}
				//为bn按钮绑定事件监听器，该监听器将会查看下一张图片
				next.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View sources) {
						//如果发生数组越界
						if (currentImgNo >= imageArray.length) {
							currentImgNo = 0;
						}
						//找到下一个图片文件
						while (!imageArray[currentImgNo].endsWith(".png")
								&& !imageArray[currentImgNo].endsWith(".jpg")
								&& !imageArray[currentImgNo].endsWith(".gif")) {
							currentImgNo++;
							//如果已发生数组越界
							if (currentImgNo >= imageArray.length) {
								currentImgNo = 0;
							}
						}
						InputStream assetFile = null;
						try {
							//打开指定资源对应的输入流
							assetFile = assetsManager.open(imageArray[currentImgNo++]);
						} catch (IOException e) {
							e.printStackTrace();
						}
						BitmapDrawable bitmapDrawable = (BitmapDrawable) image
								.getDrawable();
						//如果图片还未回收，先强制回收该图片
						if (bitmapDrawable != null
								&& !bitmapDrawable.getBitmap().isRecycled())             //①
						{
							bitmapDrawable.getBitmap().recycle();
						}
						//改变ImageView显示的图片
						image.setImageBitmap(BitmapFactory.decodeStream(assetFile)); //②
					}
				});
				break;
			case R.id.pstlayout:
				Intent refintent = new Intent(MainActivity.this,RefreshActivity.class);
				startActivity(refintent);
				break;
		}
	}

}
