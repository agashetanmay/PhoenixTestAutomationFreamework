package com.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.database.DatabaseManager;
import com.database.model.MapJobProblemDBModel;


public class mapJobProblemDao {
	
	private static final String PROBLEM_QUERRY = """
			
			select * from map_job_problem where tr_job_head_id = ?
			
			""";
	
	private mapJobProblemDao() {}
	
	public static  MapJobProblemDBModel getProblemDetails(int tr_job_head_id) {
		 MapJobProblemDBModel  mapJobProbleDBModel= null;
		try {
			 Connection connection = DatabaseManager.getConnection();
			 
			 PreparedStatement ps = connection.prepareStatement(PROBLEM_QUERRY);
			 
			 ps.setInt(1, tr_job_head_id);
			 
			 ResultSet result = ps.executeQuery();
			 
			 while(result.next()) {
				 mapJobProbleDBModel = new  MapJobProblemDBModel(
						 result.getInt("tr_job_head_id"), 
						 result.getInt("id"),
						 result.getInt("mst_problem_id"), 
						 result.getString("remark"));   
			 }
		}catch(Exception e) {
			e.getMessage();
		}
		return  mapJobProbleDBModel;
		
		
	}

}
