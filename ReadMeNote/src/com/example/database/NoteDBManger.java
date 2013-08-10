package com.example.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.model.Note;
import com.example.model.Picture;
import com.example.model.RecordAppendix;
import com.example.tools.BitMapTools;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Bitmap;
import android.util.Log;

/**
 * @description 对频道表的数据库管理类
 * 
 * */
public class NoteDBManger {
	private final String TAG = "noteDbManger";
	private SQLiteDatabase db;
	private final Context context;
	private final DBHelper dbHelper;

	public NoteDBManger(Context c) {
		context = c;
		dbHelper = new DBHelper(c, Constants.DATABASE_NAME, null,
				Constants.VERSION);
	}

	/**
	 * 关闭数据库
	 * 
	 * */

	public void close() {
		db.close();
	}

	/**
	 * 开启数据库
	 * */
	public void open() throws SQLiteException {

		try {
			db = dbHelper.getWritableDatabase();
		} catch (Exception e) {
			// TODO: handle exception
			Log.e(TAG, e.getMessage());
			db = dbHelper.getReadableDatabase();
		}

	}

	/**
	 * 增加表中数据
	 * 
	 * @param channel
	 *            Channel类
	 * 
	 * @return long 如果是正数则表示增加成功，反之不成功
	 * 
	 */
	public void addnote(Note note) {

		try {
			ContentValues contentValues = new ContentValues();
			contentValues.put(Constants.NoteListTable.USER_NAME,
					note.getUser_name());
			contentValues.put(Constants.NoteListTable.MOOD, note.getMood());
			contentValues.put(Constants.NoteListTable.NOTE_TITLE,
					note.getNote_title());
			contentValues.put(Constants.NoteListTable.ADDNOTE_DETAILS,
					note.getAddnote_details());
			contentValues.put(Constants.NoteListTable.NOTE_TIME,
					note.getnote_time());
			db.insert(Constants.NoteListTable.TABLE_NAME, null, contentValues);
			for (Picture picture : note.getPicture_list()) {
				contentValues.clear();
				contentValues.put(Constants.PictureTable.USER_NAME,
						picture.getUser_name());
				contentValues.put(Constants.PictureTable.PICTURE,
						BitMapTools.changeBitmap(picture.getPicture()));
				contentValues.put(Constants.PictureTable.NOTE_TIME,
						picture.getNote_time());
				db.insert(Constants.PictureTable.TABLE_NAME, null,
						contentValues);
			}
			for (RecordAppendix record_appendix : note
					.getRecord_appendix_list()) {
				contentValues.clear();
				contentValues.put(Constants.RecordAppendixTable.USER_NAME,
						record_appendix.getUser_name());
				contentValues.put(Constants.RecordAppendixTable.PATH,
						record_appendix.getPath());
				contentValues.put(Constants.RecordAppendixTable.TYPE,
						record_appendix.getType());
				contentValues.put(Constants.RecordAppendixTable.NOTE_TIME,
						record_appendix.getnote_time());
				db.insert(Constants.RecordAppendixTable.TABLE_NAME, null,
						contentValues);
			}

		} catch (Exception e) {
			Log.e(TAG, e.getMessage());

		}

	}

	/**
	 * 删除表中的记录
	 * 
	 * @param whereClausein
	 *            删除条件 如：( id>? and time>?)
	 * @param whereArgs
	 *            条件里的参数 用来替换"?" 第1个参数，代表第1个问号；第2个参数，代表第2个问号；依此类推......
	 * @return 返回删除的条数 也可以作为判断值，如果是正数则表示删除成功，反之不成功
	 */
	public int deletenote(String whereClause, String[] whereArgs) {
		return db.delete(Constants.NoteListTable.TABLE_NAME, whereClause,
				whereArgs);
	}

	/**
	 * 查找表中记录
	 * 
	 * @return List<Note>
	 */
	public List<Note> getdiaries(String user_name) {
		/**
		 * 查询数据
		 * 
		 * @param table
		 *            表名
		 * @param columns
		 *            要查询的列名
		 * @param selection
		 *            查询条件 如：( id=?)
		 * @param selectionArgs
		 *            条件里的参数，用来替换"?"
		 * @param orderBy
		 *            排序 如：id desc
		 * @return 返回Cursor
		 *  String[] columns={"kind","textnum","region"};
		 *  //你要的数据 String 条件字段="NUMWEEK=? and YEAR=?", String[] selectionArgs={”星期一"，"2013"}；
		 */
		List<Note> list = new ArrayList<Note>();
		
		
		Cursor cn = db.query(Constants.NoteListTable.TABLE_NAME, null, "user_name=?", new String[]{user_name},
				 null, null, null);
		if (cn.moveToFirst()) {
			do {
				Note note = new Note();
				List<Picture> picture_list = new ArrayList<Picture>();
				List<RecordAppendix> record_appendix_list = new ArrayList<RecordAppendix>();
				String note_time = cn.getString(cn
						.getColumnIndex(Constants.NoteListTable.NOTE_TIME));
				
				
				note.setNote_id(cn.getInt(cn.getColumnIndex(Constants.NoteListTable.ID)));
				note.setUser_name(user_name);
				note.setMood(cn.getInt(cn.getColumnIndex(Constants.NoteListTable.MOOD)));
				note.setNote_title(cn.getString(cn
						.getColumnIndex(Constants.NoteListTable.NOTE_TITLE)));
				note.setAddnote_details(cn.getString(cn
						.getColumnIndex(Constants.NoteListTable.ADDNOTE_DETAILS)));
				note.setnote_time(note_time);
				
				Cursor cp = db.query(Constants.PictureTable.TABLE_NAME, null, "user_name=? and note_time=?",
						new String[]{user_name,note_time}, null, null, "picture_id asc");
				if (cp.moveToFirst()) {
					do {
						Picture picture = new Picture();
						Bitmap bitmap = BitMapTools
								.getBitmap(
										cp.getBlob(cp
												.getColumnIndex(Constants.PictureTable.PICTURE)),
										40, 40);
						picture.setPicture_id(cp.getInt(cp.getColumnIndex(Constants.PictureTable.ID)));
						picture.setUser_name(user_name);
						picture.setNote_time(note_time);
						picture.setPicture(bitmap);
						picture_list.add(picture);
						
					}while (cp.moveToNext());
				}
					
				Cursor cra = db.query(Constants.RecordAppendixTable.TABLE_NAME, null, "user_name=? and note_time=?",
						new String[]{user_name,note_time}, null, null, "record_appendix_id asc");
				if (cra.moveToFirst()) {
					do {
						RecordAppendix recordAppendix = new RecordAppendix();
						recordAppendix.setRecord_appendix_id(cra
								.getInt(cra.getColumnIndex(Constants.RecordAppendixTable.ID)));
						recordAppendix.setUser_name(user_name);
						recordAppendix.setPath(cra.getString(cra.
								getColumnIndex(Constants.RecordAppendixTable.PATH)));
						recordAppendix.setType(cra.getString(cra.
								getColumnIndex(Constants.RecordAppendixTable.TYPE)));
						recordAppendix.setnote_time(note_time);
						record_appendix_list.add(recordAppendix);
					}while (cra.moveToNext());
				}
				note.setPicture_list(picture_list);
				note.setRecord_appendix_list(record_appendix_list);
				list.add(note);
				/*picture_list.clear();
				record_appendix_list.clear();*/
			} while (cn.moveToNext());
			
		}
		Log.i(TAG, String.valueOf(list.size()));
		System.out.println(list.size());
		return list;
		
	}

	/**
	 * 更改表中的记录
	 * 
	 * @param channel
	 *            Channel类
	 * 
	 * @param whereClause
	 *            修改条件 如：( id=?)
	 * 
	 * @param whereArgs
	 *            条件里的参数 用来替换"?" 第1个参数，代表第1个问号；第2个参数，代表第2个问号；依此类推......
	 * 
	 * @return 返回修改的条数 也可以作为判断值，如果是正数则表示更改成功，反之不成功
	 */
	public int updatenote(Note note, String whereClause, String[] whereArgs) {

		try {
			ContentValues contentValues = new ContentValues();

			contentValues.put(Constants.NoteListTable.NOTE_TITLE,
					note.getNote_title());

			return db.update(Constants.NoteListTable.TABLE_NAME, contentValues,
					Constants.NoteListTable.NOTE_TITLE + "=?", whereArgs);
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
			return -1;
		}

	}

	public boolean check(String user_name){
		Cursor cursor = db.query(Constants.UserTable.TABLE_NAME, null, "user_name=?", new String[]{user_name},
				 null, null, null);
		
		if (!cursor.moveToFirst()) {
			
			return true;
		}
		else
			return false;
	}
	
	public boolean check(String user_name,String password){
		Cursor cursor = db.query(Constants.UserTable.TABLE_NAME, null, "user_name=? and password=?", new String[]{user_name,password},
				 null, null, null);
		if (!cursor.moveToFirst()) {
			return true;
		}
		else
			return false;
	}
	
	public void addUser(String user_name,String register_time,String password) {

		try {
			ContentValues contentValues = new ContentValues();
			contentValues.put(Constants.UserTable.USER_NAME,user_name);
			contentValues.put(Constants.UserTable.TIME,register_time);
			contentValues.put(Constants.UserTable.PASSWORD, password);
			db.insert(Constants.UserTable.TABLE_NAME, null, contentValues);
		}catch(Exception e){
			Log.e(TAG, e.getMessage());
		}
	}
}
