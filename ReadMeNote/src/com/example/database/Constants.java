package com.example.database;

public class Constants {
	

	
		public static final String DATABASE_NAME = "Notes.db";
	
		public static final int VERSION = 1;

		

	
		/**用户表*/
		public static class UserTable {
			/**表名*/
			public static final String TABLE_NAME = "user";
			/**ID*/
			public static final String ID = "user_id";
			/**用户名*/
			public static final String USER_NAME = "username";
			/**密码*/
			public static final String PASSWORD = "password";
			/**标志*/
			public static final String MARK = "mark";

		}
		/**笔记表*/
		public static class NotesListTable {
			/**表名*/
			public static final String TABLE_NAME = "notesList";
			/**ID*/
			public static final String ID = "note_id";
			//用户名
			public static final String USER_NAME = "user_name";
		
			//心情
			public static final String MOOD = "mood";
			
			/**标题*/
			public static final String NOTE_TITLE = "noteTitle";
			//图片
			public static final String ADDNOTE_PICTURE = "addnote_picture";
			//录音
			public static final String ADDNOTE_RECORD = "addnote_record";
			//语音
			public static final String ADDNOTE_RECORDINPUT = "addnote_recordinput";
			//涂鸦
			public static final String ADDNOTE_PAINTING = "addnote_painting";
			//附件名称
			public static final String NAME_APPENDIX = "name_appendix";
			//附件地址
			public static final String PATH_APPENDIX = "path_appendix";
			//概要
			public static final String NOTE_SUMMARY = "noteSummary";
			
			/**创建时间*/
			public static final String NOTE_TIME = "noteTime";

		}
	
}
