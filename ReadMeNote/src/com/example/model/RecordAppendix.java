package com.example.model;

public class RecordAppendix {
	/**ID*/
	public int record_appendix_id;
	/**用户名*/
	public String user_name;
	/**路径*/
	public String path;
	
	/**类型（录音or附件）*/
	public String type;
	/**时间*/
	public String note_time;
	
	public int getRecord_appendix_id() {
		return record_appendix_id;
	}
	public void setRecord_appendix_id(int record_appendix_id) {
		this.record_appendix_id = record_appendix_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getnote_time() {
		return note_time;
	}
	public void setnote_time(String note_time) {
		this.note_time = note_time;
	}
}
