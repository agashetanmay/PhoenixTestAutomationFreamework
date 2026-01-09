package com.database.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.dataProviders.api.bean.createJobBean;
import com.database.DatabaseManager;
import com.github.javafaker.Faker;

public class createJobPayloadDataDao {

	private static final String SQL_QUERRY = """
			select
			first_name,
			last_name,
			mobile_number,
			mobile_number_alt,
			email_id,
			email_id_alt,
			flat_number,
			apartment_name,
			street_name,
			landmark,
			area,
			pincode,
			country,
			state,
			serial_number,
			imei1,
			imei2,
			popurl,
			dop,
			mst_model_id,
			mst_service_location_id,
			mst_platform_id,
			mst_warrenty_status_id,
			mst_oem_id,
			mst_problem_id,
			remark

			from tr_customer
			inner join tr_customer_address
			on tr_customer.tr_customer_address_id = tr_customer_address.id

			inner join tr_customer_product
			on tr_customer_product.tr_customer_id=tr_customer.id

			inner join tr_job_head
			on tr_job_head.tr_customer_id=tr_customer.id

			inner join map_job_problem
			on map_job_problem.tr_job_head_id = tr_job_head.id
			limit 5;
			
			""";
    private createJobPayloadDataDao () {}
   private static Faker faker = new Faker(new Locale("en-IND"));
	
	public static List<createJobBean> getCreateJobPayloadData() {
		Connection connection=null;
		Statement statement ;
		ResultSet resultset = null;
		
		
		List<createJobBean> beanList = new  ArrayList<createJobBean>();
		try {
			connection = DatabaseManager.getConnection();
			statement = connection.createStatement();
			resultset = statement.executeQuery(SQL_QUERRY);
			
			while(resultset.next()) {
				String serielNumberIMEI = faker.numerify("###############");
				createJobBean bean = new createJobBean();
				bean.setCustomer__first_name(resultset.getString("first_name"));
				bean.setCustomer__last_name(resultset.getString("last_name"));
				bean.setCustomer__mobile_number(resultset.getString("mobile_number"));
				bean.setCustomer__mobile_number_alt(resultset.getString("mobile_number_alt"));
				bean.setCustomer__email_id(resultset.getString("email_id"));
				bean.setCustomer__email_id_alt(resultset.getString("email_id_alt"));
				bean.setCustomer_address__flat_number(resultset.getString("flat_number"));
				bean.setCustomer_address__apartment_name(resultset.getString("apartment_name"));
				bean.setCustomer_address__street_name(resultset.getString("street_name"));
				bean.setCustomer_address__landmark(resultset.getString("landmark"));
				bean.setCustomer_address__area(resultset.getString("area"));
		        bean.setCustomer_address__country(resultset.getString("country"));
				bean.setCustomer_address__pincode(resultset.getString("pincode"));
				bean.setCustomer_address__state(resultset.getString("state"));
				bean.setCustomer_product__serial_number(serielNumberIMEI);
				bean.setCustomer_product__imei1(serielNumberIMEI);
				bean.setCustomer_product__imei2(serielNumberIMEI);
				bean.setCustomer_product__popurl(resultset.getString("popurl"));
				bean.setCustomer_product__dop(resultset.getString("dop"));
				bean.setCustomer_product__mst_model_id(resultset.getString("mst_model_id"));
				bean.setMst_service_location_id(resultset.getString("mst_service_location_id"));
				bean.setMst_platform_id(resultset.getString("mst_platform_id"));
				bean.setMst_warrenty_status_id(resultset.getString("mst_warrenty_status_id"));
				bean.setMst_oem_id(resultset.getString("mst_oem_id"));
				bean.setProblems__id(resultset.getString("mst_problem_id"));
				bean.setProblems__remark(resultset.getString("remark"));
			    bean.setCustomer_product__product_id("1");
			    beanList.add(bean);
		      	
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	       return beanList;
	}
}
