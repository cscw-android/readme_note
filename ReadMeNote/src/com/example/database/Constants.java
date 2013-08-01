package com.example.database;

public class Constants {
	

		/**��ݿ�����*/
		public static final String DATABASE_NAME = "Notes.db";
		/**��ݿ�汾*/
		public static final int Version = 1;

		

	
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
			public static final String user_name = "user_name";
		
			/**标题*/
			public static final String note_TITLE = "noteTitle";
			//图片
			public static final String addnote_picture = "addnote_picture";
			//录音
			public static final String addnote_record = "addnote_record";
			//语音
			public static final String addnote_recordinput = "addnote_recordinput";
			//涂鸦
			public static final String addnote_painting = "addnote_painting";
			//附件
			public static final String addnote_addthing = "addnote_addthing";
			//概要
			public static final String note_SUMMARY = "noteSummary";
			
			/**创建时间*/
			public static final String note_TIME = "noteTime";

		}
	
}
