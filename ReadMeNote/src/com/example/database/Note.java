package com.example.database;

import java.net.URL;

public class Note {
	
	public String title;
	public int id;
	public URL url;
	public String addnote_picture;
	public String summary;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public URL getUrl() {
		return url;
	}
	public void setUrl(URL url) {
		this.url = url;
	}
	public String getAddnote_picture() {
		return addnote_picture;
	}
	public void setAddnote_picture(String addnote_picture) {
		this.addnote_picture = addnote_picture;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
}
