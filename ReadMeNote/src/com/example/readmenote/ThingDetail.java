package com.example.readmenote;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import android.app.Activity;
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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.thing_detail);
		button2 =(Button)findViewById(R.id.but_thing);
		listView = (ListView)findViewById(R.id.listView_thing);
		textView = (TextView)findViewById(R.id.thing_text);
		//���ϵͳ��sd��Ŀ¼
		File root = new File("/mnt/sdcard/");
		if(root.exists()){
			currentParent = root;
			currentFiles = root.listFiles();
			//ʹ�õ�ǰĿ¼�����list
			inflateListView(currentFiles);
		}
		//Ϊlist���õ����¼�
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// ����û�������ļ���ֱ�ӷ��أ������κδ���
				if(currentFiles[arg2].isFile()) return;
				//��ȡ�û�������ļ��µ������ļ�
				File[] tmp = currentFiles[arg2].listFiles();
				if(tmp == null || tmp.length == 0){
					Toast.makeText(ThingDetail.this, "û���ļ�Ŷ", Toast.LENGTH_LONG).show();
				}else{
					currentParent = currentFiles[arg2];
					currentFiles = tmp;
					//�ٴθ���list
					inflateListView(currentFiles);
				}
				
			}
		});
		//��ť�������Ƿ�����һ��Ŀ¼
		button2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try{
					if(!currentParent.getCanonicalPath().equalsIgnoreCase("/mnt/sdcard/")){
						//��ȡ��һ��Ŀ¼
						currentParent = currentParent.getParentFile();
						currentFiles = currentParent.listFiles();
						//����list
						inflateListView(currentFiles);
					}
				}catch(IOException e){
					e.printStackTrace();
				}
			}

			
		});
	}
	private void inflateListView(File[] files) {
		// ����һ��List���ϣ�Ԫ����Map
		List<Map<String,Object>> listItems = new ArrayList<Map<String,Object>>();
		for(int i = 0;i<files.length;i++){
			Map<String,Object> listItem = new HashMap<String,Object>();
			if(files[i].isDirectory()){
				//�����ǰ���ļ��У�����ʾfolderͼ��
				listItem.put("picture", R.drawable.folder);
			}else{
				listItem.put("picture", R.drawable.file);
			}
			
			listItem.put("fileName", files[i].getName());
			listItems.add(listItem);
		}
		SimpleAdapter simpleAdapter = new SimpleAdapter(ThingDetail.this,listItems,
				R.layout.lines,new String[]{"fileName","picture"},new int[]{R.id.filesname,R.id.image_name});
		listView.setAdapter(simpleAdapter);
		try {
			textView.setText("��ǰ·��Ϊ"+currentParent.getCanonicalPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
