package com.study.springcloud.config.client;

import lombok.Data;

@Data
public class Config {
   private String pwd,url,port;

public String getPwd() {
	return pwd;
}

public void setPwd(String pwd) {
	this.pwd = pwd;
}
   
}
