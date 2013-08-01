package com.example.readmenote;

import android.app.Activity;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.view.View;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.text.format.DateFormat;
import android.util.Log;
import android.media.MediaRecorder;
import android.media.MediaPlayer;
import java.io.IOException;
import java.util.Calendar;
import java.util.Locale;

public class AddNote_record extends Activity {
	private static final String LOG_TAG = "AudioRecordTest";
	private static String mFileName = null;
	private RecordButton mRecordButton = null;
	private MediaRecorder mRecorder = null;
	private PlayButton mPlayButton = null;
	private MediaPlayer mPlayer = null;
	private static int saveornot = 0;// 在本activity中判断是否是无录音就回到添加笔记界面，1代表有录音
	private static int new_saveornot = 0;// 在本NewNoteActivity中判断是否是无录音就回到添加笔记界面，1代表有录音

	private void onRecord(boolean start) {
		if (start) {
			startRecording();
		} else {
			stopRecording();
		}
	}

	private void onPlay(boolean start) {
		if (start) {
			startPlaying();
		} else {
			stopPlaying();
		}
	}

	private void startPlaying() {
		mPlayer = new MediaPlayer();
		if (saveornot == 0) {
			Toast record_toast = Toast.makeText(getApplicationContext(),
					"无录音可播放", 7000);
			record_toast.show();
		}
		try {
			mPlayer.setDataSource(mFileName);
			mPlayer.prepare();
			mPlayer.start();
		} catch (IOException e) {
			Log.e(LOG_TAG, "prepare() failed");
		}
	}

	private void stopPlaying() {
		mPlayer.release();
		mPlayer = null;
		Intent intent = new Intent(AddNote_record.this, NewNoteActivity.class);
		if (saveornot == 1) {
			new_saveornot = 1;
			Toast record_toast = Toast.makeText(getApplicationContext(),
					"录音存放在sdcard/ReadMe" + mFileName, 7000);
			record_toast.show();
			saveornot = 0;
		} else {
			Toast record_toast = Toast.makeText(getApplicationContext(),
					"无录音保存", 7000);
			record_toast.show();
			saveornot = 0;
		}
		AddNote_record.this.setResult(3, intent);
		AddNote_record.this.finish();

	}

	private void startRecording() {
		saveornot = 1;
		mRecorder = new MediaRecorder();
		mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
		mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
		mRecorder.setOutputFile(mFileName);
		mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
		try {
			mRecorder.prepare();
		} catch (IOException e) {
			Log.e(LOG_TAG, "prepare() failed");
		}
		mRecorder.start();
	}

	private void stopRecording() {
		mRecorder.stop();
		mRecorder.release();
		mRecorder = null;
		Toast record_toast = Toast.makeText(getApplicationContext(),
				"文件以ReadMe开头以时间命名，保存在sd卡根目录下", 7000);
		record_toast.show();
	}

	class RecordButton extends Button {
		boolean mStartRecording = true;
		OnClickListener clicker = new OnClickListener() {
			public void onClick(View v) {
				onRecord(mStartRecording);
				if (mStartRecording) {
					setText("停止录音");
				} else {
					setText("开始录音");
				}
				mStartRecording = !mStartRecording;
			}
		};

		public RecordButton(Context ctx) {
			super(ctx);
			setText("开始录音");
			setOnClickListener(clicker);

		}
	}

	class PlayButton extends Button {
		boolean mStartPlaying = true;
		OnClickListener clicker = new OnClickListener() {
			public void onClick(View v) {
				onPlay(mStartPlaying);
				if (mStartPlaying) {
					setText("停止播放");
				} else {
					setText("开始播放");
				}
				mStartPlaying = !mStartPlaying;
			}
		};

		public PlayButton(Context ctx) {
			super(ctx);
			setText("开始播放");
			setOnClickListener(clicker);
		}
	}

	public AddNote_record() {
		mFileName = Environment.getExternalStorageDirectory().getAbsolutePath();
		mFileName += "/"
				+ "ReadMe"
				+ new DateFormat().format("yyyyMMdd_hhmmss",
						Calendar.getInstance(Locale.CHINA)) + ".amr";
	}

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		LinearLayout ll = new LinearLayout(this);

		mRecordButton = new RecordButton(this);
		ll.setOrientation(LinearLayout.VERTICAL);
		ll.setGravity(Gravity.CENTER);
		ll.addView(mRecordButton, new LinearLayout.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.WRAP_CONTENT, 0));
		mPlayButton = new PlayButton(this);
		ll.addView(mPlayButton, new LinearLayout.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.WRAP_CONTENT, 0));
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(ll);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			Intent intent = new Intent(AddNote_record.this,
					NewNoteActivity.class);
			if (saveornot == 1) {
				new_saveornot = 1;
				Toast record_toast = Toast.makeText(getApplicationContext(),
						"录音存放在sdcard/ReadMe" + mFileName, 7000);
				record_toast.show();
				saveornot = 0;
			} else {
				Toast record_toast = Toast.makeText(getApplicationContext(),
						"无录音保存", 7000);
				record_toast.show();
				saveornot = 0;
			}
			AddNote_record.this.setResult(3, intent);
			AddNote_record.this.finish();
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public void onPause() {
		super.onPause();
		if (mRecorder != null) {
			mRecorder.release();
			mRecorder = null;
		}
		if (mPlayer != null) {
			mPlayer.release();
			mPlayer = null;
		}
	}

	public static String getFileName() {
		return mFileName;
	}

	public static int getnew_saveornot() {
		return new_saveornot;
	}
}