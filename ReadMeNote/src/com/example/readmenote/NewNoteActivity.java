package com.example.readmenote;

import java.io.FileNotFoundException;
import java.sql.Date;
import java.text.SimpleDateFormat;

import addnote.function.Photo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.app.Activity;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class NewNoteActivity extends Activity {

	TextView addnote_time_textview;
	ImageButton addnote_moodTagging;
	final int MOOD = 1;
	final int CAMERA = 98;//����ȥ��resultCode
	final int PICTURE = 99;
	Bitmap bitmap = null;//Bitmap��Androidϵͳ�е�ͼ���������Ҫ��֮һ,���ں����ͼƬ��ť����
	EditText user_detail;

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
		
		// ��ʼ������button����
		initialize_button_variables();
		// ���ڰ���������
		button_set();
		addnote_moodTagging = (ImageButton) findViewById(R.id.addnote_moodTagging);
		addnote_moodTagging.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(NewNoteActivity.this,
						AddNote_moodtagging.class);
				startActivityForResult(intent, MOOD);
			}
		});
	}

	

	public void initialize_button_variables() {
		user_detail = (EditText) findViewById(R.id.user_detail);
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
				final Dialog choose = new Dialog(NewNoteActivity.this,
						R.style.draw_dialog);
				choose.setContentView(R.layout.addnote_picture);
				// ���ñ���ģ������
				WindowManager.LayoutParams winlp = choose.getWindow()
						.getAttributes();
				winlp.alpha = 0.9f; // 0.0-1.0
				choose.getWindow().setAttributes(winlp);
				choose.show();// ��ʾ������
				//����dialog�����������ť
				ImageButton choose_camera, choose_picture;

				choose_camera = (ImageButton) choose
						.findViewById(R.id.choose_camera);
				choose_picture = (ImageButton) choose
						.findViewById(R.id.choose_picture);
				//�ֱ����ü�����
				choose_camera.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						// ��ϵͳ�������intent action ���
						Intent camera = new Intent(
								MediaStore.ACTION_IMAGE_CAPTURE);
						startActivityForResult(camera, CAMERA);
						
					}
				});
				choose_picture.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// ���ֻ�����intent action���
						Intent intent = new Intent();
						intent.setType("image/*");
						intent.setAction(Intent.ACTION_GET_CONTENT);
						// ȡ����Ƭ�󷵻ر�����
						startActivityForResult(intent, PICTURE);
					}
				});
				

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
				Intent intent = new Intent(NewNoteActivity.this,ThingDetail.class);
				startActivity(intent);
				
				
			}
		});

	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
			if (requestCode == MOOD) {//����ͼ��
				Bundle b = data.getExtras();
				String imageId = b.getString("imageId");
				addnote_moodTagging
						.setImageResource(addnote_moodTagging_itemSource[Integer
								.parseInt(imageId)]);
			}
			if (requestCode == PICTURE) {//ѡ�����������ͼƬ
				Uri uri = data.getData();
				// ��ImageתΪBitmap
				ContentResolver cr = this.getContentResolver();

				try {
					// Bitmap���Ի�ȡͼ���ļ���Ϣ������ͼ����С���ת�����ŵȲ�����������ָ����ʽ����ͼ���ļ���
					// ��InputStream����Bitmap
					Bitmap bitmapfirst = BitmapFactory.decodeStream(cr
							.openInputStream(uri));
					// �ĳ�����ͼ������Լ���Ҫ�Ĵ�С���������������
					bitmap = resizeImage(bitmapfirst, 200, 200);
					// ��bitmap��ʾ�ڱ������ImageView����
					// img1.setImageBitmap(bitmap);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// ��EditText����ʾͼƬ�ķ���ImageSpan
				// ����Bitmap���󴴽�ImageSpan����
				ImageSpan imageSpan = new ImageSpan(NewNoteActivity.this,
						bitmap);
				// ����һ��SpannableString�����Ա������ImageSpan�����װ��ͼ��
				// (����������Ҳ���"[local]"+1+"[/local]")
				SpannableString spannableString = new SpannableString("[local]"
						+ 1 + "[/local]");
				// ��ImageSpan�����滻face ������Ҫ�滻�����ݣ��ӵڼ�����ʼ���ڼ���������[5,10)������5
				// ��������10�����һ���ǹ淶�Ĳ�����
				spannableString.setSpan(imageSpan, 0,
						"[local]1[local]".length() + 1,
						Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
				// ��ѡ���ͼƬ׷�ӵ�EditText�й������λ��
				int index = user_detail.getSelectionStart(); // ��ȡ�������λ��
				Editable edit_text = user_detail.getEditableText();
				if (index < 0 || index >= edit_text.length()) {
					edit_text.append(spannableString);
				} else {
					edit_text.insert(index, spannableString);
				}

			}
			if (requestCode == CAMERA) {//ѡ������
				// ȡ��Intent�е�Bundle����
				Bundle extras = data.getExtras();
				// ������������ݣ�����ת��ΪBitmap����
				Bitmap bitmapfirst1 = (Bitmap) extras.get("data");
				// �������һ��
				bitmap = resizeImage(bitmapfirst1, 200, 200);
				ImageSpan imageSpan = new ImageSpan(NewNoteActivity.this,
						bitmap);
				SpannableString spannableString = new SpannableString("[local]"
						+ 1 + "[/local]");
				spannableString.setSpan(imageSpan, 0,
						"[local]1[local]".length() + 1,
						Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
				// ��ѡ���ͼƬ׷�ӵ�EditText�й������λ��
				int index = user_detail.getSelectionStart(); // ��ȡ�������λ��
				Editable edit_text = user_detail.getEditableText();
				if (index < 0 || index >= edit_text.length()) {
					edit_text.append(spannableString);
				} else {
					edit_text.insert(index, spannableString);
				}

			}

		}
	}


   //��ͼƬ�������������ͼ�ķ���
	private Bitmap resizeImage(Bitmap bitmapfirst, int newWidth, int newHeight) {
		int width = bitmapfirst.getWidth();
		int height = bitmapfirst.getHeight();
		// ������ת���ɵĿ���
		// int newWidth = 200;
		// int newHeight = 200;
		// �������������
		float scanleWidth = (float) newWidth / width;
		float scanleHeight = (float) newHeight / height;
		// ��������ͼƬ�õ�matrix���� Matrix
		Matrix matrix = new Matrix();
		// ����ͼƬ
		matrix.postScale(scanleWidth, scanleHeight);
		// ��תͼƬ
		// matrix.postRotate(90);
		// �����µ�ͼƬBitmap
		Bitmap resizedBitmap = Bitmap.createBitmap(bitmapfirst, 0, 0, width,
				height, matrix, true);
		return resizedBitmap;
	}
}
