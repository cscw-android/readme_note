package com.example.readmenote;

import android.os.Build;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.view.Menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_note);
		gridView = (GridView) findViewById(R.id.gridView1);
		sv = (SearchView) findViewById(R.id.sv);
		 //设置该searchview是否缩小为图标
		sv.setIconifiedByDefault(true);
		 //设置该searchview是否显示搜索按钮
		sv.setSubmitButtonEnabled(true);
		sv.setQueryHint(" 查找");

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		for (int i = 0; i < 10; i++) {
			map.put("title", "我的笔记");
			map.put("text",
					"因为突发奇想，发现网上的例子的网格布局，只有图片的实例，没有加文字说明！ 后来找到个是来自《Android核心技术与实例详解》一书的实例 ，使用的适配是SimpleAdapter，而我想用继承BaseAdapter类，重写getView方法.经过自己的资料的查阅与调试 ，完成了个小例子；");
			map.put("image", R.drawable.sb3);
			list.add(map);
		}

		SimpleAdapter simpleAdapter = new SimpleAdapter(MyNoteActivity.this,
				list, R.layout.note_show, new String[] { "title", "text",
						"image" }, new int[] { R.id.title, R.id.text,
						R.id.image });
		gridView.setAdapter(simpleAdapter);
		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Toast.makeText(MyNoteActivity.this, "你点击了" + arg2 + "个",
						Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(MyNoteActivity.this, detail.class);
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
}
