package com.example.model;

public class Youdao {
	/**ID*/
	public int youdao_id ;
	/**有道通行证*/
	public String youdao;
	/**Access Token*/
	public String access_token;
	/**Access Token Secret*/
	public String access_token_secret;
	/**使用状态
	 * 0表示未被使用，
	 * 1表示已被使用*/
	public String condition;
	public int getYoudao_id() {
		return youdao_id;
	}
	public void setYoudao_id(int youdao_id) {
		this.youdao_id = youdao_id;
	}
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
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
}
