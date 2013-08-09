package com.example.login;

import com.example.readmenote.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends Activity{
	Button register;
	EditText key_ed,name_ed,key_again_ed;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register_activity);
		key_ed = (EditText)findViewById(R.id.key_register);
		key_again_ed = (EditText)findViewById(R.id.key_again_register);
		name_ed = (EditText)findViewById(R.id.name_register);
		register = (Button)findViewById(R.id.register_register);
		
		register.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String  name = name_ed.getText().toString();
				String key = key_ed.getText().toString();
				String key_again = key_again_ed.getText().toString();
				if(key.equals(key_again)){
					//数据库里面插入多一条数据
					Toast.makeText(Register.this, "注册成功", Toast.LENGTH_SHORT).show();
				}else{
					Toast.makeText(Register.this, "两次输入密码不相同,请重新输入", Toast.LENGTH_SHORT).show();
				}
				
			}
		});
		
		
	}

}
