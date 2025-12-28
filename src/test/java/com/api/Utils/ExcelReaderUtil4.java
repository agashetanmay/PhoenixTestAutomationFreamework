package com.api.Utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.api.request.model.userCredentials;
import com.dataProviders.api.bean.createJobBean;
import com.dataProviders.api.bean.userBean;
import com.poiji.bind.Poiji;

public class ExcelReaderUtil4 {

	
	public static void main(String[] args) {
		
		Iterator<createJobBean> iterator = ExcelReaderUtil3.loadTestData("createJobTestData", createJobBean.class);
		 
		
		while(iterator.hasNext()) {
			
			System.out.println(iterator.next());
		}
	}
	
}

