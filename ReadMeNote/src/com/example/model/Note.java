package com.example.model;

import java.util.Date;
import java.util.List;

public class Note {

	/**ID*/
	public  int note_id;
	/**用户名*/
	public String user_name;

	/**心情*/
	public int mood;
	/**标题*/
	public String note_title;
	/**图片*/
	public List<Picture> picture_list;
	/**录音和附件*/
	public List<RecordAppendix> record_appendix_list;
	
	/**详细信息*/
	public String addnote_details;
	/**创建时间*/
	public String note_time;
	
	
	public int getNote_id() {
		return note_id;
	}
	public void setNote_id(int note_id) {
		this.note_id = note_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public int getMood() {
		return mood;
	}
	public void setMood(int mood) {
		this.mood = mood;
	}
	public String getNote_title() {
		return note_title;
	}
	public void setNote_title(String note_title) {
		this.note_title = note_title;
	}
	public String getAddnote_details() {
		return addnote_details;
	}
	public void setAddnote_details(String addnote_details) {
		this.addnote_details = addnote_details;
	}
	public String getnote_time() {
		return note_time;
	}
	public void setnote_time(String note_time) {
		this.note_time = note_time;
	}
	
	public List<Picture> getPicture_list() {
		return picture_list;
	}
	public void setPicture_list(List<Picture> picture_list) {
		this.picture_list = picture_list;
	}
	public List<RecordAppendix> getRecord_appendix_list() {
		return record_appendix_list;
	}
	public void setRecord_appendix_list(List<RecordAppendix> record_appendix_list) {
		this.record_appendix_list = record_appendix_list;
	}
}
