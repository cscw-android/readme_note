package com.example.readmenote;

import java.io.FileNotFoundException;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.example.database.Note;
import com.example.database.NoteDBManger;
import com.iflytek.speech.ErrorCode;
import com.iflytek.speech.ISpeechModule;
import com.iflytek.speech.InitListener;
import com.iflytek.speech.RecognizerListener;
import com.iflytek.speech.RecognizerResult;
import com.iflytek.speech.SpeechConstant;
import com.iflytek.speech.SpeechRecognizer;
import com.iflytek.speech.SpeechUtility;
import com.iflytek.speech.util.ApkInstaller;
import com.iflytek.speech.util.JsonParser;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.RemoteException;
import android.provider.MediaStore;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.speech.RecognizerIntent;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class NewNoteActivity extends Activity {

	TextView addnote_time_textview;
	ImageButton addnote_moodTagging;
	// 传过去的resultCode
	final int MOOD = 1;
	final int GESTURE = 2;
	final int CAMERA = 98;
	final int PICTURE = 99;

	Note note = new Note();

	final int THING = 56;

	String res = null;
	String name_appendix = null;
	String path_appendix = null;
	String imageId = null;//心情图标id
	Bitmap bitmap = null;// Bitmap是Android系统中的图像处理的最重要类之一,用于后面的图片按钮处理(选择图片)
	Bitmap bitmap_painting = null;//涂鸦图片
	EditText user_detail, user_title;

	private ImageButton addnote_save, addnote_picture, addnote_record,
			addnote_recordinput;
	private ImageButton addnote_painting, addnote_addthing;
	 
	protected static final String TAG = "IatDemo";
	// 这是语音部分的请求码
	private static final int REQUEST_CODE_SEARCH = 817;
	private Toast mToast;

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
		
		// 设置申请到的应用appid
		SpeechUtility.getUtility(this).setAppid("51ece17f");
		// 初始化识别对象
		SpeechRecognizer mIat = new SpeechRecognizer(this, mInitListener);
		mToast = Toast.makeText(this, "", Toast.LENGTH_LONG);
		// 转写会话
		mIat.setParameter(SpeechConstant.PARAMS, "asr_ptt=1");
		mIat.startListening(mRecognizerListener);
		// 转写会话停止
		mIat.stopListening(mRecognizerListener);
		// 转写会话取消
		mIat.cancel(mRecognizerListener);
	}

	public void initialize_button_variables() {
		addnote_moodTagging = (ImageButton) findViewById(R.id.addnote_moodTagging);
		user_title = (EditText) findViewById(R.id.user_title);
		user_detail = (EditText) findViewById(R.id.user_detail);
		addnote_save = (ImageButton) findViewById(R.id.gallery_menu_save);
		addnote_picture = (ImageButton) findViewById(R.id.gallery_menu_picture);
		addnote_record = (ImageButton) findViewById(R.id.gallery_menu_record);
		addnote_recordinput = (ImageButton) findViewById(R.id.gallery_menu_sound_import);
		addnote_painting = (ImageButton) findViewById(R.id.gallery_menu_painting);
		addnote_addthing = (ImageButton) findViewById(R.id.gallery_menu_add_thing);
	}

	public void button_set() {
		
		addnote_moodTagging.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(NewNoteActivity.this,
						AddNote_moodtagging.class);
				startActivityForResult(intent, MOOD);
			}
		});
		
		addnote_save.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				SqliteTask sqliteTask = new SqliteTask(NewNoteActivity.this);
				note.setTitle(null);
				sqliteTask.execute(note);
			}
		});

		/**
		 * 
		 */
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
				// 声明dialog里面的两个按钮
				ImageButton choose_camera, choose_picture;

				choose_camera = (ImageButton) choose
						.findViewById(R.id.choose_camera);
				choose_picture = (ImageButton) choose
						.findViewById(R.id.choose_picture);
				// 分别设置监听器
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
				Intent intent = new Intent(NewNoteActivity.this,AddNote_record.class);
				startActivity(intent);
			}
		});
		
		

		// 为语音按钮设置单击事件监听器
		addnote_recordinput.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// 检测是否安装了讯飞语音服务
				if (SpeechUtility.getUtility(NewNoteActivity.this)
						.queryAvailableEngines() == null
						|| SpeechUtility.getUtility(NewNoteActivity.this)
								.queryAvailableEngines().length <= 0) {
					AlertDialog.Builder dialog = new AlertDialog.Builder(
							NewNoteActivity.this);
					dialog.setMessage(getString(R.string.download_confirm_msg));
					dialog.setNegativeButton(R.string.dialog_cancel_button,
							null);
					dialog.setPositiveButton(
							getString(R.string.dialog_confirm_button),
							new DialogInterface.OnClickListener() {
								public void onClick(
										DialogInterface dialoginterface, int i) {
									String url = SpeechUtility.getUtility(
											NewNoteActivity.this)
											.getComponentUrl();
									String assetsApk = "SpeechService_1.0.1017.apk";
									processInstall(NewNoteActivity.this, url,
											assetsApk);
								}
							});
					dialog.show();
					return;
				}
				Intent intent = new Intent();
				// 指定action，调用讯飞的对话默认窗口
				intent.setAction("com.iflytek.speech.action.voiceinput");
				intent.putExtra(SpeechConstant.PARAMS, "asr_ptt=0");
				intent.putExtra(SpeechConstant.VAD_EOS, "1000");
				// 设置弹出框的两个按钮的名称
				intent.putExtra("title_done", "确定");
				intent.putExtra("title_cancle", "取消");
				startActivityForResult(intent, REQUEST_CODE_SEARCH);
			}
		});

		addnote_painting.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast gesture_toast = Toast.makeText(getApplicationContext(),
						"请涂鸦,单次点击只能涂鸦一次哦~亲^_^", 7000);
				gesture_toast.show();
				Intent intent = new Intent(NewNoteActivity.this,
						AddNote_painting.class);
				startActivityForResult(intent, GESTURE);
			}
		});

		addnote_addthing.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(NewNoteActivity.this,
						ThingDetail.class);
				startActivityForResult(intent, THING);

			}
		});

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == THING && resultCode == THING) {
			// 把传过来的东西拿出来
			// Toast.makeText(NewNoteActivity.this, "放了",
			// Toast.LENGTH_SHORT).show();
			Bundle bundle = data.getExtras();
			 name_appendix = bundle.getString("name");//附件名称
			 path_appendix = bundle.getString("path");//附件路径
			// 设置字体的颜色
			SpannableString ss = new SpannableString("你所添加的文件名字是:" + name_appendix);
			ss.setSpan(new ForegroundColorSpan(Color.RED), 0, ss.length(),
					Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
			SpannableString ss1 = new SpannableString("你所添加的文件路径是" + path_appendix);
			ss1.setSpan(new ForegroundColorSpan(Color.BLUE), 0, ss1.length(),
					Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

			// 将选择的图片追加到EditText中光标所在位置
			int index = user_detail.getSelectionStart(); // 获取光标所在位置
			Editable edit_text = user_detail.getEditableText();
			if (index < 0 || index >= edit_text.length()) {
				edit_text.append(ss);
			} else {
				edit_text.insert(index, ss);
			}
			int index1 = user_detail.getSelectionStart(); // 获取光标所在位置
			Editable edit_text1 = user_detail.getEditableText();
			if (index1 < 0 || index1 >= edit_text1.length()) {
				edit_text1.append(ss1);
			} else {
				edit_text1.insert(index, ss1);
			}
		}

		if (requestCode == MOOD) {
			// 心情图标
			Bundle b = data.getExtras();
		    imageId = b.getString("imageId");//心情图标id
			addnote_moodTagging
					.setImageResource(addnote_moodTagging_itemSource[Integer
							.parseInt(imageId)]);

		}
		if (requestCode == GESTURE) {
			AddNote_painting painting = new AddNote_painting();
		    bitmap_painting = painting.getBitmap();//涂鸦的图片
			// 接下来的代码跟上面的注释是一样的，不累赘注释
			ImageSpan imageSpan = new ImageSpan(NewNoteActivity.this, bitmap_painting);
			SpannableString spannableString = new SpannableString("[local]" + 1
					+ "[/local]");
			spannableString.setSpan(imageSpan, 0,
					"[local]1[local]".length() + 1,
					Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			int index = user_detail.getSelectionStart();
			Editable edit_text = user_detail.getEditableText();
			if (index < 0 || index >= edit_text.length()) {
				edit_text.append(spannableString);
			} else {
				edit_text.insert(index, spannableString);
			}

		}
		if (resultCode == RESULT_OK) {

			if (requestCode == PICTURE) {// 选择添加相册里的图片

				Uri uri = data.getData();//相册 图片的位置
				// 将Image转为Bitmap
				ContentResolver cr = this.getContentResolver();

				try {
					// Bitmap可以获取图像文件信息，进行图像剪切、旋转、缩放等操作，并可以指定格式保存图像文件。
					// 用InputStream产生Bitmap
					Bitmap bitmapfirst = BitmapFactory.decodeStream(cr
							.openInputStream(uri));
					// 改成缩略图，变成自己想要的大小，下面有这个方法
					bitmap = resizeImage(bitmapfirst, 200, 200);//相册图片的缩略图
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
			if (requestCode == CAMERA) {// 选择拍照
				// 取得Intent中的Bundle对象
				Bundle extras = data.getExtras();
				// 返回相机的数据，并且转换为Bitmap类型
				Bitmap bitmapfirst1 = (Bitmap) extras.get("data");
				// 跟上面的一样
				bitmap = resizeImage(bitmapfirst1, 200, 200);//照相后图像的缩略图
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

			// 语音部分结果的取得
			if (requestCode == REQUEST_CODE_SEARCH) {
				// 取得识别的字符串
				ArrayList<String> results = data
						.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
				 res = results.get(0);//语音文本
				// 语音转写的结果返回的Editext
				EditText editor = ((EditText) findViewById(R.id.user_detail));
				/**String text = editor.getText().toString() + iattext;
				editor.setText(text);
				setText()方法与append()方法的区别。即s=s+a-->s.append(a)的区别
				append方法用来累积字符串的,用途是当需要大量的字符串拼接时使用  
				  优点效率比+=要高很多 （+=内存中是相当于创建副本重新赋值，StringBuffer是指针的引用）
				 */
				editor.append(res);
			}

		}
	}

	// 将图片按比例变成缩略图的方法
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

	/**
	 * 初期化监听器。
	 */
	// 语音监听器
	private InitListener mInitListener = new InitListener() {

		@Override
		public void onInit(ISpeechModule module, int code) {
			Log.d(TAG, "SpeechRecognizer init() code = " + code);
			if (code == ErrorCode.SUCCESS) {
				// findViewById(R.id.iat_recognize_bind).setEnabled(true);
				addnote_recordinput.setEnabled(true);
			}
		}
	};

	/**
	 * 识别回调。
	 */
	// 转写回调的一系列回调方法
	private RecognizerListener mRecognizerListener = new RecognizerListener.Stub() {

		@Override
		// 录音音量回调
		public void onVolumeChanged(int v) throws RemoteException {
			showTip("onVolumeChanged：" + v);
		}

		@Override
		// 录音开始回调
		public void onBeginOfSpeech() throws RemoteException {
			// TODO Auto-generated method stub
			showTip("onBeginOfSpeech");
		}

		@Override
		// 录音结束回调
		public void onEndOfSpeech() throws RemoteException {
			// TODO Auto-generated method stub
			showTip("onEndOfSpeech");
		}

		@Override
		// 错误回调
		public void onError(int errorCode) throws RemoteException {
			// TODO Auto-generated method stub
			showTip("onError Code：" + errorCode);
		}

		// 语音转写结果回调
		@Override
		public void onResult(final RecognizerResult result, boolean arg1)
				throws RemoteException {
			// TODO Auto-generated method stub
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					if (null != result) {
						// 显示
						Log.d(TAG,
								"recognizer result：" + result.getResultString());
						String iattext = JsonParser.parseIatResult(result
								.getResultString());
						EditText editor = ((EditText) findViewById(R.id.user_detail));
						/**String text = editor.getText().toString() + iattext;
						editor.setText(text);
						setText()方法与append()方法的区别。即s=s+a-->s.append(a)的区别
						append方法用来累积字符串的,用途是当需要大量的字符串拼接时使用  
						  优点效率比+=要高很多 （+=内存中是相当于创建副本重新赋值，StringBuffer是指针的引用）
						 */
						editor.append(iattext);
					} else {
						Log.d(TAG, "recognizer result : null");
						showTip("无识别结果");
					}
				}
			});
		}
	};

	// 安装语音组件
	protected void processInstall(NewNoteActivity newNoteActivity, String url,
			String assetsApk) {
		// TODO Auto-generated method stub
		// 直接下载方式
		// ApkInstaller.openDownloadWeb(context, url);
		// 本地安装方式的方法
		if (!ApkInstaller.installFromAssets(newNoteActivity, assetsApk)) {
			Toast.makeText(NewNoteActivity.this, "安装失败", Toast.LENGTH_SHORT)
					.show();
		}
	}

	// 语音部分公用的一个提示方法
	protected void showTip(final String str) {
		// TODO Auto-generated method stub
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				mToast.setText(str);
				mToast.show();
			}
		});
	}
	/**
	 * 
	 * @author 海文
	 * 异步保存数据
	 *
	 */
	class SqliteTask extends AsyncTask{

		@Override
		protected void onPostExecute(Object result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			Toast.makeText(NewNoteActivity.this
					, result.toString() 
					, 4000)
					.show();
		}

		Context context;
		public SqliteTask(Context ctx){
			context = ctx;
		}
		
		@Override
		protected Object doInBackground(Object... arg0) {
			// TODO Auto-generated method stub
			Note note = (Note)arg0[0];
			int id = 0;
			note.setId(id++);
			String result;
			System.out.println(note.getId());
			try{
			NoteDBManger noteDBManger = new NoteDBManger(context);
			noteDBManger.open();
			noteDBManger.addnote(note);
			result = "保存成功";
			
			}catch(Exception e){
				e.printStackTrace();
				result = "保存失败";
			}
			return result;
		}
		
	}

}
