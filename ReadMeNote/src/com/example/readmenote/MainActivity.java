package com.example.readmenote;

import java.sql.Date;
import java.text.SimpleDateFormat;

import android.os.Bundle;
import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TabHost;
import android.widget.TextView;

public class MainActivity extends TabActivity implements
		OnCheckedChangeListener {
	private TextView time_textview;
	private TabHost tabhost;
	private Intent my_note_intent, new_note_intent, share_intent;
	private RadioButton button1, button2, button3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// ȫ����ʾ
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		// ��ȡ��ǰʱ��ķ���
		settime();
		// ��ȡ��activity���tabhost
		tabhost = getTabHost();
		button1 = (RadioButton) findViewById(R.id.tab1);
		button2 = (RadioButton) findViewById(R.id.tab2);
		button3 = (RadioButton) findViewById(R.id.tab3);

		// �����½�3����Intent����Activity���л�
		my_note_intent = new Intent(this, MyNoteActivity.class);
		new_note_intent = new Intent(this, NewNoteActivity.class);
		share_intent = new Intent(this, ShareActivity.class);

		// ÿһ����ǩ������
		View myTab = (View) LayoutInflater.from(this).inflate(R.layout.tabmim,
				null);
		TextView text0 = (TextView) myTab.findViewById(R.id.tab_label);
		ImageView img0 = (ImageView) myTab.findViewById(R.id.imageView1);
		text0.setText("�ҵıʼ�");
		img0.setImageResource(R.drawable.my_note);

		View newTab = (View) LayoutInflater.from(this).inflate(R.layout.tabmim,
				null);
		TextView text1 = (TextView) newTab.findViewById(R.id.tab_label);
		ImageView img1 = (ImageView) newTab.findViewById(R.id.imageView1);
		text1.setText("��ӱʼ�");
		img1.setImageResource(R.drawable.new_note);

		View shareTab = (View) LayoutInflater.from(this).inflate(
				R.layout.tabmim, null);
		TextView text2 = (TextView) shareTab.findViewById(R.id.tab_label);
		ImageView img2 = (ImageView) shareTab.findViewById(R.id.imageView1);
		text2.setText("����ʼ�");
		img2.setImageResource(R.drawable.share);

		// ��tabhost�����tab
		tabhost.addTab(tabhost.newTabSpec("TAB1").setIndicator(myTab)
				.setContent(my_note_intent));
		tabhost.addTab(tabhost.newTabSpec("TAB2").setIndicator(newTab)
				.setContent(new_note_intent));
		tabhost.addTab(tabhost.newTabSpec("TAB3").setIndicator(shareTab)
				.setContent(share_intent));
		// ��������ť���ü���
		button1.setOnCheckedChangeListener(this);
		button2.setOnCheckedChangeListener(this);
		button3.setOnCheckedChangeListener(this);
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		// TODO Auto-generated method stub
		if (isChecked) {
			switch (buttonView.getId()) {
			case R.id.tab1:
				this.tabhost.setCurrentTabByTag("TAB1 ");
				break;

			case R.id.tab2:
				this.tabhost.setCurrentTabByTag("TAB2");
				break;

			case R.id.tab3:
				this.tabhost.setCurrentTabByTag(" TAB3");
				break;
			}
		}
	}

	// ��ȡʱ��ķ���
	private void settime() {
		// TODO Auto-generated method stub yyyy��MM��dd�� E hh:mm:ss
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy��MM��dd��  E  ");
		Date curDate = new Date(System.currentTimeMillis());// ��ȡ��ǰʱ��
		String str = formatter.format(curDate);
		time_textview = (TextView) findViewById(R.id.time);
		time_textview.setText(str);
	}
}
