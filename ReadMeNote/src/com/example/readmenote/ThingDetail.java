package com.example.readmenote;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ThingDetail extends Activity {
	Button button2;
	Intent it;
	ListView listView;
	TextView textView;
	File currentParent;
	File[] currentFiles;
	final int THING = 56;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.thing_detail);
		button2 = (Button) findViewById(R.id.but_thing);
		listView = (ListView) findViewById(R.id.listView_thing);
		textView = (TextView) findViewById(R.id.thing_text);
		// 获得系统的sd卡目录
		File root = new File("/mnt/sdcard/");
		if (root.exists()) {
			currentParent = root;
			currentFiles = root.listFiles();
			// 使用当前目录来填充list
			inflateListView(currentFiles);
		}
		// 为list设置单击事件
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// 如果用户点击了文件，弹出对话框
				if (currentFiles[arg2].isFile())
					addThing(currentFiles[arg2]);
				// 获取用户点击的文件下的所有文件
				File[] tmp = currentFiles[arg2].listFiles();
				if (tmp == null || tmp.length == 0) {
					// Toast.makeText(ThingDetail.this, "没有文件哦",
					// Toast.LENGTH_LONG).show();
				} else {
					currentParent = currentFiles[arg2];
					currentFiles = tmp;
					// 再次更新list
					inflateListView(currentFiles);
				}

			}

			private void addThing(final File file) {
				// TODO Auto-generated method stub
				new AlertDialog.Builder(ThingDetail.this)
						.setIcon(R.drawable.super_mono_3d_part2_42)
						.setTitle("确定添加此附件？")
						.setPositiveButton("确定",
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										// 得到添加的附件的名字和路径

										String name = file.getName();
										String path = file.getPath();
										Toast.makeText(ThingDetail.this,
												"你已经成功添加" + file.getName(),
												Toast.LENGTH_SHORT).show();
										Intent intent = getIntent();
										// 将要传递的内容放在bundle里面

										Bundle bundle = new Bundle();
										bundle.putString("name", name);
										bundle.putString("path", path);
										intent.putExtras(bundle);
										ThingDetail.this.setResult(THING,
												intent);
										ThingDetail.this.finish();

									}
								})
						.setNegativeButton("取消",
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										// TODO Auto-generated method stub
										Toast.makeText(ThingDetail.this,
												"你未添加" + file.getName(),
												Toast.LENGTH_SHORT).show();

									}
								}).show();

			}

		});
		// 按钮的作用是返回上一级目录
		button2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					if (!currentParent.getCanonicalPath().equalsIgnoreCase(
							"/mnt/sdcard/")) {
						// 获取上一级目录
						currentParent = currentParent.getParentFile();
						currentFiles = currentParent.listFiles();
						// 更新list
						inflateListView(currentFiles);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		});
	}

	private void inflateListView(File[] files) {
		// 创建一个List集合，元素是Map
		List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < files.length; i++) {
			Map<String, Object> listItem = new HashMap<String, Object>();
			if (files[i].isDirectory()) {
				// 如果当前是文件夹，则显示folder图标
				listItem.put("picture", R.drawable.folder);
			} else {
				listItem.put("picture", R.drawable.file);
			}

			listItem.put("fileName", files[i].getName());
			listItems.add(listItem);
		}
		SimpleAdapter simpleAdapter = new SimpleAdapter(ThingDetail.this,
				listItems, R.layout.lines,
				new String[] { "fileName", "picture" }, new int[] {
						R.id.filesname, R.id.image_name });
		listView.setAdapter(simpleAdapter);
		try {
			textView.setText("当前路径为" + currentParent.getCanonicalPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
