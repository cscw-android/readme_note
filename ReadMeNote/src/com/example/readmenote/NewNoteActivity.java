package com.example.readmenote;

import java.io.FileNotFoundException;
import java.sql.Date;
import java.text.SimpleDateFormat;

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
	final int CAMERA = 98;//传过去的resultCode
	final int PICTURE = 99;
	Bitmap bitmap = null;//Bitmap是Android系统中的图像处理的最重要类之一,用于后面的图片按钮处理
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
				// 设置背景模糊参数
				WindowManager.LayoutParams winlp = choose.getWindow()
						.getAttributes();
				winlp.alpha = 0.9f; // 0.0-1.0
				choose.getWindow().setAttributes(winlp);
				choose.show();// 显示弹出框
				//声明dialog里面的两个按钮
				ImageButton choose_camera, choose_picture;

				choose_camera = (ImageButton) choose
						.findViewById(R.id.choose_camera);
				choose_picture = (ImageButton) choose
						.findViewById(R.id.choose_picture);
				//分别设置监听器
				choose_camera.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						// 打开系统的相机的intent action 语句
						Intent camera = new Intent(
								MediaStore.ACTION_IMAGE_CAPTURE);
						startActivityForResult(camera, CAMERA);
						
					}
				});
				choose_picture.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// 打开手机相册的intent action语句
						Intent intent = new Intent();
						intent.setType("image/*");
						intent.setAction(Intent.ACTION_GET_CONTENT);
						// 取得照片后返回本界面
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
			if (requestCode == MOOD) {//心情图标
				Bundle b = data.getExtras();
				String imageId = b.getString("imageId");
				addnote_moodTagging
						.setImageResource(addnote_moodTagging_itemSource[Integer
								.parseInt(imageId)]);
			}
			if (requestCode == PICTURE) {//选择添加相册里的图片
				Uri uri = data.getData();
				// 将Image转为Bitmap
				ContentResolver cr = this.getContentResolver();

				try {
					// Bitmap可以获取图像文件信息，进行图像剪切、旋转、缩放等操作，并可以指定格式保存图像文件。
					// 用InputStream产生Bitmap
					Bitmap bitmapfirst = BitmapFactory.decodeStream(cr
							.openInputStream(uri));
					// 改成缩略图，变成自己想要的大小，下面有这个方法
					bitmap = resizeImage(bitmapfirst, 200, 200);
					// 将bitmap显示在本界面的ImageView里面
					// img1.setImageBitmap(bitmap);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// 在EditText中显示图片的方法ImageSpan
				// 根据Bitmap对象创建ImageSpan对象
				ImageSpan imageSpan = new ImageSpan(NewNoteActivity.this,
						bitmap);
				// 创建一个SpannableString对象，以便插入用ImageSpan对象封装的图像
				// (括号里面的我不懂"[local]"+1+"[/local]")
				SpannableString spannableString = new SpannableString("[local]"
						+ 1 + "[/local]");
				// 用ImageSpan对象替换face 参数（要替换的内容，从第几个开始，第几个结束，[5,10)，包括5
				// ，不包括10，最后一个是规范的参数）
				spannableString.setSpan(imageSpan, 0,
						"[local]1[local]".length() + 1,
						Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
				// 将选择的图片追加到EditText中光标所在位置
				int index = user_detail.getSelectionStart(); // 获取光标所在位置
				Editable edit_text = user_detail.getEditableText();
				if (index < 0 || index >= edit_text.length()) {
					edit_text.append(spannableString);
				} else {
					edit_text.insert(index, spannableString);
				}

			}
			if (requestCode == CAMERA) {//选择拍照
				// 取得Intent中的Bundle对象
				Bundle extras = data.getExtras();
				// 返回相机的数据，并且转换为Bitmap类型
				Bitmap bitmapfirst1 = (Bitmap) extras.get("data");
				// 跟上面的一样
				bitmap = resizeImage(bitmapfirst1, 200, 200);
				ImageSpan imageSpan = new ImageSpan(NewNoteActivity.this,
						bitmap);
				SpannableString spannableString = new SpannableString("[local]"
						+ 1 + "[/local]");
				spannableString.setSpan(imageSpan, 0,
						"[local]1[local]".length() + 1,
						Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
				// 将选择的图片追加到EditText中光标所在位置
				int index = user_detail.getSelectionStart(); // 获取光标所在位置
				Editable edit_text = user_detail.getEditableText();
				if (index < 0 || index >= edit_text.length()) {
					edit_text.append(spannableString);
				} else {
					edit_text.insert(index, spannableString);
				}

			}

		}
	}


   //将图片按比例变成缩略图的方法
	private Bitmap resizeImage(Bitmap bitmapfirst, int newWidth, int newHeight) {
		int width = bitmapfirst.getWidth();
		int height = bitmapfirst.getHeight();
		// 定义欲转换成的宽、高
		// int newWidth = 200;
		// int newHeight = 200;
		// 计算宽、高缩放率
		float scanleWidth = (float) newWidth / width;
		float scanleHeight = (float) newHeight / height;
		// 创建操作图片用的matrix对象 Matrix
		Matrix matrix = new Matrix();
		// 缩放图片
		matrix.postScale(scanleWidth, scanleHeight);
		// 旋转图片
		// matrix.postRotate(90);
		// 创建新的图片Bitmap
		Bitmap resizedBitmap = Bitmap.createBitmap(bitmapfirst, 0, 0, width,
				height, matrix, true);
		return resizedBitmap;
	}
}
