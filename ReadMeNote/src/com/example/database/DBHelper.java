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
			+ Constants.UserTable.TIME + " text)";
	private final String NoteListTable = "create table "
			+ Constants.NoteListTable.TABLE_NAME + " ("
			+ Constants.NoteListTable.ID + " integer primary key, "
			+ Constants.NoteListTable.USER_NAME + " text, "
			+ Constants.NoteListTable.MOOD + " integer, "
			+ Constants.NoteListTable.NOTE_TITLE + " text, "
			+ Constants.NoteListTable.ADDNOTE_DETAILS + " text, "
			+ Constants.NoteListTable.NOTE_TIME + " text)";
	private final String PictureTable = "create table "
			+ Constants.PictureTable.TABLE_NAME + " ("
			+ Constants.PictureTable.ID + " integer primary key, "
			+ Constants.PictureTable.USER_NAME + " text, "
			+ Constants.PictureTable.PICTURE + " text, "
			+ Constants.PictureTable.NOTE_TIME + " text)";
	private final String RecordAppendixTable = "create table "
			+ Constants.RecordAppendixTable.TABLE_NAME + " ("
			+ Constants.RecordAppendixTable.ID + " integer primary key, "
			+ Constants.RecordAppendixTable.USER_NAME + " text, "
			+ Constants.RecordAppendixTable.TYPE + " text, "
			+ Constants.RecordAppendixTable.PATH + " text, "
			+ Constants.RecordAppendixTable.NOTE_TIME + " text)";

	public DBHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, Constants.DATABASE_NAME, null, Constants.VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		try {
			
			db.execSQL(USER_TABLE);
			db.execSQL(NoteListTable);
			db.execSQL(PictureTable);
			db.execSQL(RecordAppendixTable);
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
		db.execSQL("drop table if exists " + Constants.NoteListTable.TABLE_NAME);
		
		db.execSQL("drop table if exists " + Constants.UserTable.TABLE_NAME);
		
		db.execSQL("drop table if exists " + Constants.PictureTable.TABLE_NAME);
		
		db.execSQL("drop table if exists " + Constants.RecordAppendixTable.TABLE_NAME);
		onCreate(db);
		System.out.println("update successfully");
	}

}
