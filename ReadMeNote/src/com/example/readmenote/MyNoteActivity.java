package com.example.readmenote;

import android.os.Build;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
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
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.SearchView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class MyNoteActivity extends Activity {
	GridView gridView;
	private SearchView sv;
	private final String TAG = "MyNoteActivity";
	private NoteDBManger noteDBManger;
	Activity context = this;
	MyAdapter myAdapter;
	List<Note> list = new ArrayList<Note>();
	String user_name;
	SharedPreferences preferences;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_note);
		gridView = (GridView) findViewById(R.id.gridView1);
		noteDBManger = new NoteDBManger(this);
		preferences = getSharedPreferences("count", MODE_PRIVATE);
	    user_name = preferences.getString("name", null);
	    Log.i(TAG, user_name);
		
		getData();
		
		gridView.setAdapter(myAdapter);
		gridView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub

				Note note = (Note)myAdapter.getItem(arg2);
				Intent intent = new Intent(MyNoteActivity.this, MyNoteDetailActivity.class);
				Bundle bundle = new Bundle();
				bundle.putSerializable("note", note);
				intent.putExtras(bundle);
				startActivity(intent);
			}
		});

		gridView.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				Toast.makeText(MyNoteActivity.this, "你选择了" + arg2 + "哥哥哥",
						Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});
	}
	private void getData() {
		list.clear();
		try {	
			noteDBManger.open();
		    list = noteDBManger.getdiaries(user_name);
			//startManagingCursor(cursor);
			noteDBManger.close();
			myAdapter = new MyAdapter(context,list);
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
