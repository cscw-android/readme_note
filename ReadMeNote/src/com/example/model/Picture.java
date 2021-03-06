package com.example.model;

import java.io.Serializable;

import android.graphics.Bitmap;

public class Picture implements Serializable{
	
	/**ID*/
	public  int picture_id;
	/**用户名*/
	public String user_name;
	/**图片内容*/
	public byte[] picture;
	/**创建时间*/
	public String note_time;
	
	public int getPicture_id() {
		return picture_id;
	}
	public void setPicture_id(int picture_id) {
		this.picture_id = picture_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public byte[] getPicture() {
		return picture;
	}
	public void setPicture(byte[] picture) {
		this.picture = picture;
	}
	public String getNote_time() {
		return note_time;
	}
	public void setNote_time(String note_time) {
		this.note_time = note_time;
	}
}
