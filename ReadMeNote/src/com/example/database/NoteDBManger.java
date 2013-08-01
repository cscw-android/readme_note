package com.example.database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
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
				Constants.Version);
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
	public long addnote(Note note) {

		try {
			ContentValues contentValues = new ContentValues();
			contentValues.put(Constants.NotesListTable.ID, note.getId());
			
			return db.insert(Constants.NotesListTable.TABLE_NAME, null,
					contentValues);

		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
			return -1;
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
		return db.delete(Constants.NotesListTable.TABLE_NAME, whereClause,
				whereArgs);
	}

	/**
	 * 查找表中记录
	 * 
	 * @return Cursor
	 */
	public Cursor getdiaries() {
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
		 */
		Cursor c = db.query(Constants.NotesListTable.TABLE_NAME, null, null,
				null, null, null, null);
		return c;
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
	public int updatenote(Note note, String whereClause,
			String[] whereArgs) {

		try {
			ContentValues contentValues = new ContentValues();
			contentValues.put(Constants.NotesListTable.ID, note.getId());
			contentValues.put(Constants.NotesListTable.addnote_picture,
					note.getAddnote_picture());
			contentValues.put(Constants.NotesListTable.note_SUMMARY, note.getSummary());
			return db.update(Constants.NotesListTable.TABLE_NAME, contentValues,
					Constants.NotesListTable.addnote_picture+"=?", whereArgs);
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
			return -1;
		}

	}

}
