package com.vk.recoengine.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseController {
	@PersistenceContext
	public EntityManager entityManager;
	
	@Autowired
	public EntityManagerFactory entityManagerFactory;
	
	static DataFormatter dataFormatter = new DataFormatter();

	static String getStringValue(Cell cell) {
	    return dataFormatter.formatCellValue(cell);
	}
	
	public Date getCellDateValue(Cell cell){
		try {
			Date date=cell.getDateCellValue();
			if(date!=null){
				return date;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
			try {
				String stringDate=getStringValue(cell);
				SimpleDateFormat sdf=new SimpleDateFormat("dd/mm/yyyy HH:mm a");
				return sdf.parse(stringDate);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				return null;
			}
			
		}
		return null;
	}
	
	public Double getCellDoubleValue(Cell cell){
		try {
			cell.setCellType(Cell.CELL_TYPE_STRING);
			String value=cell.getStringCellValue();
			return Double.parseDouble(value);
		} catch (Exception e) {
			
			e.printStackTrace();
			return 0.0;
		}
	}
	
	public Date parseDateForSure(String dateString,int cnt){
		SimpleDateFormat sdf;
		int counter=cnt;
		Date date = null;
		String dateFormat="dd/MM/yyyy";
		try{
			if(counter==0){
				dateFormat="dd-MMM-yyyy";
			}else if(counter==1){
				dateFormat="MM/dd/yyyy";
			}else if(counter==2){
				dateFormat="dd-MM-yyyy";
			}else if(counter==3){
				dateFormat="MM-dd-yyyy";
			}else if(counter==4){
				dateFormat="yyyy/MM/dd";
			}else if(counter==5){
				dateFormat="yyyy-MM-dd";
			}else if(counter==6){
				dateFormat="dd/MM/yyyy HH:mm:ss";
			}else if(counter==7){
				dateFormat="MM/dd/yyyy HH:mm:ss";
			}else if(counter==8){
				dateFormat="dd-MM-yyyy HH:mm:ss";
			}else if(counter==9){
				dateFormat="MM-dd-yyyy HH:mm:ss";
			}else if(counter==10){
				dateFormat="yyyy/MM/dd HH:mm:ss";
			}else if(counter==11){
				dateFormat="yyyy-MM-dd HH:mm:ss";
			}else if(counter==12){
				dateFormat="dd/MM/yyyy HH:mm:ss a";
			}else if(counter==13){
				dateFormat="MM/dd/yyyy HH:mm:ss a";
			}else if(counter==14){
				dateFormat="dd-MM-yyyy HH:mm:ss a";
			}else if(counter==15){
				dateFormat="MM-dd-yyyy HH:mm:ss a";
			}else if(counter==16){
				dateFormat="yyyy/MM/dd HH:mm:ss a";
			}else if(counter==17){
				dateFormat="yyyy-MM-dd HH:mm:ss a";
			}else if(counter==18){
				dateFormat="dd/MM/yyyy";
			}
			if(counter<19){
				sdf=new SimpleDateFormat(dateFormat);
				date= sdf.parse(dateString);
			}
		}catch(Exception e){
			//System.out.println("Error in date parsing for sure with pattern "+dateFormat);
			counter=counter+1;
			parseDateForSure(dateString,counter);
		}
		
		return date;
	}
}
