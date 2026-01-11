package com.api.response.model;

import com.database.model.CustomerAddressDBModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor             // jackson data bind required no Argument constructor for execution
public class CreateJobResponseModel {
	
	private String message;
	private CreateJobDataModel data;
	    

}
