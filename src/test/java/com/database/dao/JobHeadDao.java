package com.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.database.DatabaseManager;
import com.database.model.JobHeadDBmodel;

public class JobHeadDao {
	
	private static final String JOB_HEAD_QUERY = """
			select * from tr_job_head where tr_customer_id = ?
			""";
	
	private JobHeadDao() {}
	
	public static JobHeadDBmodel getDataFromJobHead(int tr_customer_id) {
		JobHeadDBmodel JobHeadDBmodel = null;
		try {
			Connection connection = DatabaseManager.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(JOB_HEAD_QUERY);
			preparedStatement.setInt(1, tr_customer_id);
			ResultSet result = preparedStatement.executeQuery();
			
			while(result.next()) {
				 JobHeadDBmodel = new JobHeadDBmodel(result.getInt("id"),
						result.getString("job_number"),
						result.getInt("tr_customer_id"),
						result.getInt("tr_customer_product_id"),
						result.getInt("mst_service_location_id"),
						result.getInt("mst_platform_id"),
						result.getInt("mst_oem_id"),
						result.getInt("mst_warrenty_status_id"));
			}
			
		}
		catch(SQLException e) {
			e.getMessage();
		}
		 return JobHeadDBmodel;

	}

}
