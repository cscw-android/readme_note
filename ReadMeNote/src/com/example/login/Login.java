package com.example.login;

import com.example.readmenote.MainActivity;
import com.example.readmenote.R;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Login extends Activity{
	
	private Button log,register;
	private EditText name_ed,key_ed;
	private String name,key;
	
	SharedPreferences preferences;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		//初始化
		intView();
		log.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				name = name_ed.getText().toString();
				key = key_ed.getText().toString();
				
				preferences = getSharedPreferences("count", MODE_PRIVATE);
				Editor editor = preferences.edit();
				//把最近一次使用的用户的用户名放进去
				editor.putString("name", name).commit();
				editor.putString("key", key).commit();
				
				//然后就是数据库那边的了，验证正确的话就跳转
				//这里先写直接就能跳转的
				Intent intent = new Intent(Login.this,MainActivity.class);
				startActivity(intent);
			}
		});
		register.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//跳到注册界面，到时候是否要从注册页面获得数据然后直接把刚注册的用户的用户名和密码填在edittext里面？？
				Intent intent = new Intent(Login.this,Register.class);
				startActivity(intent);
				
			}
		});
	}

	private void intView() {
		// TODO Auto-generated method stub
		log = (Button)findViewById(R.id.but_log);
		register = (Button)findViewById(R.id.but_register);
		name_ed = (EditText)findViewById(R.id.name_ed);
		key_ed = (EditText)findViewById(R.id.key_ed);
		
	}
	

}
