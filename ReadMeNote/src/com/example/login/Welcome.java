package com.example.login;

import com.example.readmenote.MainActivity;
import com.example.readmenote.R;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;

public class Welcome extends Activity{
    
	private int count;//记录使用的次数
	
	SharedPreferences preference;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		//获得该对象
		preference = getSharedPreferences("count", MODE_PRIVATE);
		//取出count的值，看是否是第一次使用
		count = preference.getInt("count", 0);
		//获得可以写入数据的editor
		Editor editor = preference.edit();
		//写入数据，然后commit
		editor.putInt("count", ++count).commit();
		//使用线程使activity延迟2秒
		new Handler().postDelayed(new Runnable(){

			@Override
			public void run() {
				//如果count == 1，即第一次使用
				if(count == 1)
				{//跳转到登入界面
				Intent intent = new Intent(Welcome.this,Login.class);
				startActivity(intent);
				Welcome.this.finish();
				}else{
					//获得上一次登入的用户的用户名，到时候可以传递，通过用户名打开数据库里面的笔记
					String name = preference.getString("name", null);
					System.out.println(name);
					//不是第一次登入，直接跳转到主界面
					Intent intent = new Intent(Welcome.this,MainActivity.class);
					startActivity(intent);
					Welcome.this.finish();
				}
			}
		}, 2000);//延迟2秒
	}
}
