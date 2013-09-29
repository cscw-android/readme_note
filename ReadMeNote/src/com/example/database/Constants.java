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
			public static final String USER_NAME = "user_name";
			/**密码*/
			public static final String PASSWORD = "password";
			/**注册时间*/
			public static final String TIME = "time";
			/**有道通行证*/
			public static final String YOUDAO = "youdao";
			/**Access Token*/
			public static final String Access_Token = "access_token";
			/**Access Token Secret*/
			public static final String Access_Token_Secret = "access_token_secret";
			

		}
		
		/**有道通行证表*/
		public static class YoudaoTable{
			/**表名*/
			public static final String TABLE_NAME = "youdao";
			/**ID*/
			public static final String ID = "youdao_id";
			/**有道通行证*/
			public static final String YOUDAO = "youdao";
			/**Access Token*/
			public static final String Access_Token = "access_token";
			/**Access Token Secret*/
			public static final String Access_Token_Secret = "access_token_secret";
			/**使用状态*/
			public static final String CONDITION = "condition";
		}
		
		/**笔记表*/
		public static class NoteListTable {
			/**表名*/
			public static final String TABLE_NAME = "note_list";
			/**ID*/
			public static final String ID = "note_id";
			/**用户名*/
			public static final String USER_NAME = "user_name";
			/**心情*/
			public static final String MOOD = "mood";
			/**标题*/
			public static final String NOTE_TITLE = "note_title";
		
			/**文本内容*/
			public static final String ADDNOTE_DETAILS = "addnote_details";
			
			/**创建时间*/
			public static final String NOTE_TIME = "note_time";

		}
		
		/**图片表*/
		public static class PictureTable {
			/**表名*/
			public static final String TABLE_NAME = "picture_list";
			/**ID*/
			public static final String ID = "picture_id";
			/**用户名*/
			public static final String USER_NAME = "user_name";
			/**图片*/
			public static final String PICTURE = "picture";
		
			/**创建时间*/
			public static final String NOTE_TIME = "note_time";

		}
		
		/**录音附件表*/
		public static class RecordAppendixTable {
			/**表名*/
			public static final String TABLE_NAME = "record_appendix_list";
			/**ID*/
			public static final String ID = "record_appendix_id";
			/**用户名*/
			public static final String USER_NAME = "user_name";
			/**名称*/
			public static final String TYPE = "type";
			/**路径*/
			public static final String PATH = "path";
			/**创建时间*/
			public static final String NOTE_TIME = "note_time";

		}
}
