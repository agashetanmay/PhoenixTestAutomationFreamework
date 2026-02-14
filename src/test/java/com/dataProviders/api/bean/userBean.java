package com.dataProviders.api.bean;

import com.opencsv.bean.CsvBindByName;
import com.poiji.annotation.ExcelCellName;

public class userBean {    // this class is useed to map the instance variable with excle and CSV
	                         //And call this class in dataprovider class to map the data from excel
	@CsvBindByName(column="username")   
	@ExcelCellName("username")
	private String username;
	
	@ExcelCellName("password")
	@CsvBindByName(column="password")
	private String password;
	
	public userBean() {
		
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "userBean [username=" + username + ", password=" + password + "]";
	}
	
	
	

}
