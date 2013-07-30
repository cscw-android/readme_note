package com.example.readmenote;

import java.sql.Date;
import java.text.SimpleDateFormat;

import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class NewNoteActivity extends Activity {

	TextView addnote_time_textview;
	ImageButton addnote_moodTagging;

	private ImageButton addnote_save, addnote_picture, addnote_record,
			addnote_recordinput;
	private ImageButton addnote_painting, addnote_addthing;

	int[] addnote_moodTagging_itemSource = new int[] { R.drawable.appkefu_f000,
			R.drawable.appkefu_f011, R.drawable.appkefu_f001,
			R.drawable.appkefu_f012, R.drawable.appkefu_f003,
			R.drawable.appkefu_f015, R.drawable.appkefu_f004,
			R.drawable.appkefu_f013, R.drawable.appkefu_f005,
			R.drawable.appkefu_f014, R.drawable.appkefu_f006,
			R.drawable.appkefu_f016, R.drawable.appkefu_f007,
			R.drawable.appkefu_f017, R.drawable.appkefu_f008,
			R.drawable.appkefu_f018, R.drawable.appkefu_f009,
			R.drawable.appkefu_f019, R.drawable.appkefu_f010,
			R.drawable.appkefu_f020, R.drawable.appkefu_f022,
			R.drawable.appkefu_f031, R.drawable.appkefu_f021,
			R.drawable.appkefu_f032, R.drawable.appkefu_f023,
			R.drawable.appkefu_f035, R.drawable.appkefu_f024,
			R.drawable.appkefu_f033, R.drawable.appkefu_f025,
			R.drawable.appkefu_f034, R.drawable.appkefu_f026,
			R.drawable.appkefu_f036, R.drawable.appkefu_f027,
			R.drawable.appkefu_f037, R.drawable.appkefu_f028,
			R.drawable.appkefu_f038, R.drawable.appkefu_f029,
			R.drawable.appkefu_f039, R.drawable.appkefu_f020,
			R.drawable.appkefu_f040, R.drawable.appkefu_f042,
			R.drawable.appkefu_f051, R.drawable.appkefu_f041,
			R.drawable.appkefu_f052, R.drawable.appkefu_f043,
			R.drawable.appkefu_f055, R.drawable.appkefu_f044,
			R.drawable.appkefu_f053, R.drawable.appkefu_f045,
			R.drawable.appkefu_f054, R.drawable.appkefu_f046,
			R.drawable.appkefu_f056, R.drawable.appkefu_f047,
			R.drawable.appkefu_f057, R.drawable.appkefu_f048,
			R.drawable.appkefu_f058, R.drawable.appkefu_f049,
			R.drawable.appkefu_f059, R.drawable.appkefu_f050,
			R.drawable.appkefu_f060, R.drawable.appkefu_f072,
			R.drawable.appkefu_f061, R.drawable.appkefu_f071,
			R.drawable.appkefu_f062, R.drawable.appkefu_f073,
			R.drawable.appkefu_f065, R.drawable.appkefu_f074,
			R.drawable.appkefu_f063, R.drawable.appkefu_f075,
			R.drawable.appkefu_f064, R.drawable.appkefu_f076,
			R.drawable.appkefu_f066, R.drawable.appkefu_f077,
			R.drawable.appkefu_f067, R.drawable.appkefu_f078,
			R.drawable.appkefu_f068, R.drawable.appkefu_f079,
			R.drawable.appkefu_f069, R.drawable.appkefu_f090,
			R.drawable.appkefu_f070, R.drawable.appkefu_f096,
			R.drawable.appkefu_f091, R.drawable.appkefu_f097,
			R.drawable.appkefu_f092, R.drawable.appkefu_f098,
			R.drawable.appkefu_f093, R.drawable.appkefu_f099,
			R.drawable.appkefu_f094, R.drawable.appkefu_f100,
			R.drawable.appkefu_f095, R.drawable.appkefu_f104,
			R.drawable.appkefu_f105, R.drawable.appkefu_f103,
			R.drawable.appkefu_f151, R.drawable.appkefu_f101,
			R.drawable.appkefu_f095, };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_new_note);
		// 初始化变量button变量
		initialize_button_variables();
		// 关于按键的设置
		button_set();
		addnote_moodTagging = (ImageButton) findViewById(R.id.addnote_moodTagging);
		addnote_moodTagging.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(NewNoteActivity.this,
						AddNote_moodtagging.class);
				startActivityForResult(intent, 1);
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		Bundle b = data.getExtras();
		String imageId = b.getString("imageId");
		addnote_moodTagging
				.setImageResource(addnote_moodTagging_itemSource[Integer
						.parseInt(imageId)]);
	}

	public void initialize_button_variables() {
		addnote_save = (ImageButton) findViewById(R.id.gallery_menu_save);
		addnote_picture = (ImageButton) findViewById(R.id.gallery_menu_picture);
		addnote_record = (ImageButton) findViewById(R.id.gallery_menu_record);
		addnote_recordinput = (ImageButton) findViewById(R.id.gallery_menu_sound_import);
		addnote_painting = (ImageButton) findViewById(R.id.gallery_menu_painting);
		addnote_addthing = (ImageButton) findViewById(R.id.gallery_menu_add_thing);
	}

	public void button_set() {
		addnote_save.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

			}
		});

		addnote_picture.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Dialog choose = new Dialog(NewNoteActivity.this,
						R.style.draw_dialog);
				choose.setContentView(R.layout.addnote_picture);
				// 设置背景模糊参数
				WindowManager.LayoutParams winlp = choose.getWindow()
						.getAttributes();
				winlp.alpha = 0.9f; // 0.0-1.0
				choose.getWindow().setAttributes(winlp);
				choose.show();// 显示弹出框
			}
		});

		addnote_record.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});

		addnote_recordinput.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

			}
		});

		addnote_painting.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

			}
		});

		addnote_addthing.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

			}
		});
	}
}
