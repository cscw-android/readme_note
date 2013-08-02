package com.example.database;

public class Note {
	
	/**ID*/
	public  int note_id;
	//用户名
	public String user_name;

	//心情
	public String mood;
	
	/**标题*/
	public String noteTitle;
	//图片
	public byte[] addnote_picture;
	//录音
	public byte[] addnote_record;
	//语音
	public String addnote_recordinput;
	//涂鸦
	public byte[] addnote_painting;
	//附件名称
	public String name_appendix;
	//附件路径
	public String path_appendix;

	//概要
	public String noteSummary;
	
	/**创建时间*/
	public String noteTime;
	
	
	
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

	public String getMood() {
		return mood;
	}

	public void setMood(String mood) {
		this.mood = mood;
	}

	public String getNoteTitle() {
		return noteTitle;
	}

	public void setNoteTitle(String noteTitle) {
		this.noteTitle = noteTitle;
	}

	public byte[] getAddnote_picture() {
		return addnote_picture;
	}

	public void setAddnote_picture(byte[] addnote_picture) {
		this.addnote_picture = addnote_picture;
	}

	public byte[] getAddnote_record() {
		return addnote_record;
	}

	public void setAddnote_record(byte[] addnote_record) {
		this.addnote_record = addnote_record;
	}

	public String getAddnote_recordinput() {
		return addnote_recordinput;
	}

	public void setAddnote_recordinput(String addnote_recordinput) {
		this.addnote_recordinput = addnote_recordinput;
	}

	public byte[] getAddnote_painting() {
		return addnote_painting;
	}

	public void setAddnote_painting(byte[] addnote_painting) {
		this.addnote_painting = addnote_painting;
	}

	public String getName_appendix() {
		return name_appendix;
	}

	public void setName_appendix(String name_appendix) {
		this.name_appendix = name_appendix;
	}

	public String getPath_appendix() {
		return path_appendix;
	}

	public void setPath_appendix(String path_appendix) {
		this.path_appendix = path_appendix;
	}

	public String getNoteSummary() {
		return noteSummary;
	}

	public void setNoteSummary(String noteSummary) {
		this.noteSummary = noteSummary;
	}

	public String getNoteTime() {
		return noteTime;
	}

	public void setNoteTime(String noteTime) {
		this.noteTime = noteTime;
	}

	
}
