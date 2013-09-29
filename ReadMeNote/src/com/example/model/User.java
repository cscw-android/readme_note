package com.example.model;

public class User {
	
	/**ID*/
	public int user_id ;
	/**用户名*/
	public String user_name;
	/**密码*/
	public String password;
	/**注册时间*/
	public String time;
	/**有道通行证*/
	public String youdao;
	/**Access Token*/
	public String access_token;
	/**Access Token Secret*/
	public String access_token_secret;
	
	public String getYoudao() {
		return youdao;
	}
	public void setYoudao(String youdao) {
		this.youdao = youdao;
	}
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public String getAccess_token_secret() {
		return access_token_secret;
	}
	public void setAccess_token_secret(String access_token_secret) {
		this.access_token_secret = access_token_secret;
	}
	
	
	
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	
}
