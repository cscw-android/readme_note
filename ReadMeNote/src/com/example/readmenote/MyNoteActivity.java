package com.example.readmenote;

import android.opengl.Visibility;
import android.os.Build;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.support.v4.widget.CursorAdapter;
import android.util.Log;
import android.view.Menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.database.Constants;
import com.example.database.NoteDBManger;
import com.example.model.Note;
import com.example.tools.BitMapTools;
import com.example.tools.MyAdapter;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.SearchView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class MyNoteActivity extends Activity {
	GridView gridView;
	Button but_upload;//现在暂时把这个上传得按钮变成删除的
	private SearchView sv;
	private final String TAG = "MyNoteActivity";
	private NoteDBManger noteDBManger;
	Activity context = this;
	public MyAdapter myAdapter;
	private List<Note> list = new ArrayList<Note>();
	private String user_name;
	private SharedPreferences preferences;
	private boolean isCheckBox = false;
	private List<Integer> listItem_ID = new ArrayList<Integer>();//判断有多少个被选中
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_note);
		gridView = (GridView) findViewById(R.id.gridView1);
		but_upload = (Button) findViewById(R.id.but_delete);
		noteDBManger = new NoteDBManger(this);
		preferences = getSharedPreferences("count", MODE_PRIVATE);
	    user_name = preferences.getString("name", null);
	    Log.i(TAG, user_name);
		
	   
		getData();
		gridView.setAdapter(myAdapter);
		 if(list.size() == 0){
		    	Toast.makeText(MyNoteActivity.this, "你没有任何笔记哦~",Toast.LENGTH_SHORT).show();
		    }
		gridView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub

				Toast.makeText(MyNoteActivity.this, "你点击了" + arg2 + "个",
						Toast.LENGTH_SHORT).show();
				//Intent intent = new Intent(MyNoteActivity.this, MyNoteDetailActivity.class);
				//startActivity(intent);
			}
		});
		gridView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				but_upload.setVisibility(View.VISIBLE);
				isCheckBox = true;
				myAdapter = new MyAdapter(context,list,isCheckBox,position);
				gridView.setAdapter(myAdapter);
			
				return true;
			}
		});
		but_upload.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				listItem_ID.clear();
				for(int i=0 ;i<myAdapter.mChecked.size();i++){
					if(myAdapter.mChecked.get(i)){
						listItem_ID.add(i);
					}
				}
				if(listItem_ID.size() != 0){
					StringBuilder sb = new StringBuilder();
					sb.append("将");
					for(int i=0;i<listItem_ID.size();i++){
						sb.append(listItem_ID.get(i)+".");
					}
					sb.append("笔记删除？");
					AlertDialog builder = new AlertDialog.Builder(MyNoteActivity.this).setIcon(R.drawable.ic_launcher)
							.setTitle(sb.toString()).
							setPositiveButton("确定", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// 跳转到授权界面,这里到时候还要传数据哦！！！！！
							//Intent intent = new Intent(MainActivity.this,ShareActivity.class);
							//startActivity(intent);
							
						}
					}).setNegativeButton("取消", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							
						}
					}).create();
					builder.show();
				}else{
				Toast.makeText(MyNoteActivity.this, "未选中笔记", Toast.LENGTH_SHORT).show();
				}
			}
		});
		 
		

		
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if(keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0){
			if(isCheckBox == true){
				getData();
				gridView.setAdapter(myAdapter);
				//System.out.println("jijiyy");
				return true;
			}else{
				finish();
			}
		}
		return super.onKeyDown(keyCode, event);
	}
	
	
	private void getData() {
		list.clear();
		isCheckBox = false;
		but_upload.setVisibility(View.GONE);
		try {	
			noteDBManger.open();
		    list = noteDBManger.getdiaries(user_name);
			//startManagingCursor(cursor);
			noteDBManger.close();
			myAdapter = new MyAdapter(context,list,isCheckBox);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		getData();
		gridView.setAdapter(myAdapter);
	}
}
