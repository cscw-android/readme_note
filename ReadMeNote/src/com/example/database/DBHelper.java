package com.example.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {
	private final String TAG = "DBHelper";

	
	private final String USER_TABLE = "create table "
			+ Constants.UserTable.TABLE_NAME + " (" + Constants.UserTable.ID
			+ " integer primary key, " + Constants.UserTable.USER_NAME
			+ " text, " + Constants.UserTable.PASSWORD + " text, "
			+ Constants.UserTable.MARK + " integer)";
	private final String NotesListTable = "create table "
			+ Constants.NotesListTable.TABLE_NAME + " ("
			+ Constants.NotesListTable.ID + " integer primary key, "
			+ Constants.NotesListTable.USER_NAME + " text, "
			+ Constants.NotesListTable.MOOD + " blog, "
			+ Constants.NotesListTable.NOTE_TITLE + " text, "
			+ Constants.NotesListTable.ADDNOTE_PICTURE + " blog, "
			+ Constants.NotesListTable.ADDNOTE_RECORD + " blog, "
			+ Constants.NotesListTable.ADDNOTE_RECORDINPUT + " text, "
			+ Constants.NotesListTable.ADDNOTE_PAINTING + " blog, "
			+ Constants.NotesListTable.NAME_APPENDIX + " text, "
			+ Constants.NotesListTable.PATH_APPENDIX + " text, "
			+ Constants.NotesListTable.NOTE_SUMMARY + " text, "
			+ Constants.NotesListTable.NOTE_TIME + " text)";

	public DBHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, Constants.DATABASE_NAME, null, Constants.VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		try {
			
			db.execSQL(USER_TABLE);
			db.execSQL(NotesListTable);
			System.out.println("created successfully");
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
		}

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		Log.w(TAG, "Upgrading from version " + oldVersion + " to" + newVersion
				+ ",which will destroy all old data");
		db.execSQL("drop table if exists " + Constants.NotesListTable.TABLE_NAME);
		
		db.execSQL("drop table if exists " + Constants.UserTable.TABLE_NAME);
		onCreate(db);
		System.out.println("update successfully");
	}

}
