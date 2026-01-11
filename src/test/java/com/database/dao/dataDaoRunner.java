package com.database.dao;

import java.sql.SQLException;

import com.database.model.JobHeadDBmodel;

public class dataDaoRunner {

	public static void main(String[] args) throws SQLException {
		
		JobHeadDBmodel jobDB = JobHeadDao.getDataFromJobHead(152136);
		
		System.out.println(jobDB);
	}
}
