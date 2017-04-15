package com.vk.recoengine.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.annotation.Resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.poi.openxml4j.exceptions.NotOfficeXmlFileException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;
import com.vk.recoengine.dto.PO;
import com.vk.recoengine.entities.CustomerData;
import com.vk.recoengine.entities.PhysicalReturns;
import com.vk.recoengine.entities.ProductCategories;
import com.vk.recoengine.entities.PurchaseOrders;
import com.vk.recoengine.entities.SdPayments;
import com.vk.recoengine.entities.SdReturns;
import com.vk.recoengine.entities.SdSales;
import com.vk.recoengine.entities.SkuSettings;
import com.vk.recoengine.service.ExcelToDatabase;
import com.vk.recoengine.utils.CellProcessorUtil;

@Path("/file")
@Component
public class UploadController extends BaseController{

	@Resource(name="configProperties")
	private Properties properties;

	@Autowired
	private ExcelToDatabase excelToDatabaseService;

	//Column indexes for SD+ Comp Order Report
	private final int ORDER_DATE=0;
	private final int SUBORDER_CODE=2;
	private final int ORDER_CODE=3;
	private final int PRODUCT_NAME=4;
	private final int SKU=5;
	private final int SUPC=6;
	private final int ORDER_STATE=7;
	private final int SELLING_PRICE=8;
	private final int INVOICE=10;
	private final int VAT=13;
	private final int CST=14;
	private final int FULFILLMENT_MODE=16;
	private final int TYPE_OF_RETURN=24;
	private final int BUYER_NAME=30;
	private final int BUYER_ADDRESS=31;
	private final int BUYER_CITY=32;
	private final int BUYER_STATE=33;
	private final int BUYER_PIN_CODE=34;
	private final int RETURN_DATE=23;

	//Column indexes for SD Payments
	private final int PAYMENT_DATE=34;
	private final int PYMNT_SUBORDER_CODE=3;
	private final int REASON=4;
	private final int PYMNT_SKU=6;
	private final int PYMNT_INVOICE=7;
	private final int PYMNT_SELLING_PRICE=8;
	private final int SELLER_FUNDED_CASHBACK=10;
	private final int BENEFIT_PASSED_TO_BUYER=11;
	private final int PACKING_CHARGES=17;
	private final int MARKETING_FEES=19;
	private final int PAYMENT_COLLECTION_FEES=20;
	private final int COURIER_FEES=21;
	private final int RETURNS_PACKAGING_FEES=24;
	private final int RETURNS_PAYMENT_COLLECTION_FEES=26;
	private final int REV_SHIPPING_FEES=27;
	private final int SERVICE_TAX=29;
	private final int KK_CESS=31;
	private final int SB_CESS=30;
	private final int COMMISSION=32;
	private final int NET_PAYABLE=33;
	private final int REF_NO=35;
	private final int PYMNT_ORDER_DATE=36;
	private final int PYMNT_FULFILLMENT_MODE=41;

	//Column indexes for Uniware Returns
	private final int UNI_RET_SALE_ORDER_CODE=1;
	private final int UNI_RET_CHANNEL=3;
	private final int UNI_RET_SKU=6;
	private final int UNI_RET_SP=11;
	private final int UNI_RET_INVOICE=36;
	private final int UNI_RET_ORDER_DATE=35;
	private final int UNI_RET_RETURN_DATE=0;


	private final double weightSlabDivider=0.5;
	@POST
	@Path("/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadFile(
			@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail,
			@FormDataParam("fileType") String fileType) {

		String uploadedFileLocation = properties.getProperty("file.upload.path") + fileDetail.getFileName();

		// save it
		writeToFile(uploadedInputStream, uploadedFileLocation);

		String output = "File uploaded to : " + uploadedFileLocation+"\n"+fileType;
		if(!fileType.isEmpty()){
			if(fileType.trim().equalsIgnoreCase("SKU_SETTINGS")){
				saveSKUSettingsData(uploadedFileLocation);
			}else if(fileType.trim().equalsIgnoreCase("SD_PLUS_SALES")||fileType.trim().equalsIgnoreCase("SD_DS_SALES")){
				saveSdPlusSalesData(uploadedFileLocation,fileType);
			}else if(fileType.trim().equalsIgnoreCase("SD_PAYMENTS")){
				saveSdPayments(uploadedFileLocation);
			}else if(fileType.trim().equalsIgnoreCase("UNI_SALES")){
				saveUniSales(uploadedFileLocation);
			}else if(fileType.trim().equalsIgnoreCase("UNI_RETURNS")){
				saveUniReturns(uploadedFileLocation);
			}else if(fileType.trim().equalsIgnoreCase("PURCHASE_ORDER")){
				savePurchaseOrders(uploadedFileLocation);
			}
		}
		return Response.status(200).entity(output).build();

	}

	// save uploaded file to new location
	private void writeToFile(InputStream uploadedInputStream,
			String uploadedFileLocation) {

		try {
			OutputStream out = new FileOutputStream(new File(
					uploadedFileLocation));
			int read = 0;
			byte[] bytes = new byte[1024];

			out = new FileOutputStream(new File(uploadedFileLocation));
			while ((read = uploadedInputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			out.flush();
			out.close();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public Response saveSKUSettingsData(String filePath){
		String skucode;
		int counter=0,rowNum = 0,cellNum=0;
		File file =new File(filePath);
		List<SkuSettings> skus=new ArrayList<SkuSettings>();
		FileInputStream fis=null;
		XSSFWorkbook workbook=null;
		StringBuilder errorMesssges=new StringBuilder();
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
		DataFormatter formatter=new DataFormatter();
		try {
			fis=new FileInputStream(file);
			workbook=new XSSFWorkbook(fis);
			XSSFSheet sheet=workbook.getSheetAt(0);
			Iterator<Row> rowItertor=sheet.iterator();
			while(rowItertor.hasNext()){
				Double length = 0.0,width = 0.0,height = 0.0,weight = 0.0;
				int volumetricSlab=1,deadWeightSlab=1;
				SkuSettings sku=new SkuSettings();
				Row row=rowItertor.next();
				if(!isRowEmpty(row)){
					rowNum=row.getRowNum();
					if(rowNum>0){
						cellNum=1;
						skucode=formatter.formatCellValue(row.getCell(0));
						sku.setTitle(formatter.formatCellValue(row.getCell(1)));
						cellNum=0;
						sku.setSku(skucode);
						cellNum=2;
						if(row.getCell(2).getCellType()==Cell.CELL_TYPE_NUMERIC && row.getCell(2).getNumericCellValue()>0){
							length=row.getCell(2).getNumericCellValue();
							sku.setLength(length);
						}else{
							errorMesssges.append(skucode+"("+rowNum+","+cellNum+")"+" Invalid length value");
							sku.setLength(null);
						}
						
						cellNum=3;
						if(row.getCell(3).getCellType()==Cell.CELL_TYPE_NUMERIC && row.getCell(3).getNumericCellValue()>0){
							width=row.getCell(3).getNumericCellValue();
							sku.setWidth(width);
						}else{
							errorMesssges.append(skucode+"("+rowNum+","+cellNum+")"+" Invalid width value");
							sku.setWidth(null);
						}
						cellNum=4;
						if(row.getCell(4).getCellType()==Cell.CELL_TYPE_NUMERIC && row.getCell(4).getNumericCellValue()>0){
							height=row.getCell(4).getNumericCellValue();
							sku.setHeight(height);
						}else{
							errorMesssges.append(skucode+"("+rowNum+","+cellNum+")"+" Invalid height value");
							sku.setHeight(null);
						}
						cellNum=5;
						if(row.getCell(5).getCellType()==Cell.CELL_TYPE_NUMERIC && row.getCell(5).getNumericCellValue()>0){
							weight=row.getCell(5).getNumericCellValue();
							sku.setWeight(weight);
						}else{
							errorMesssges.append(skucode+"("+rowNum+","+cellNum+")"+" Invalid weight value");
							sku.setWeight(null);
						}

						if(length>0 && width>0 && height>0){
							double divider=5000.00;
							volumetricSlab=getWeightSlab((length*width*height)/divider,"V");
							sku.setWieghtSlab(volumetricSlab);
						}

						if(weight>0){
							deadWeightSlab=getWeightSlab(weight,"W");
							sku.setDeadWieghtSlab(deadWeightSlab);
						}

						if(deadWeightSlab>volumetricSlab){
							sku.setChargedWeightslab(deadWeightSlab);
						}else{
							sku.setChargedWeightslab(volumetricSlab);
						}
						cellNum=7;
						if(row.getCell(6)!=null && row.getCell(6).getCellType()==Cell.CELL_TYPE_NUMERIC && row.getCell(6).getNumericCellValue()>0){
							int category=((Double)row.getCell(6).getNumericCellValue()).intValue();
							ProductCategories productCategory=excelToDatabaseService.getProductCategoryById(category);
							sku.setCategory(productCategory);
						}else{
							sku.setCategory(null);
							errorMesssges.append(skucode+"("+rowNum+","+cellNum+")"+" Invalid category value");
						}
						
						if(row.getCell(7)!=null && row.getCell(7).getDateCellValue()!=null){
							sku.setFromDate(row.getCell(7).getDateCellValue());
						}else{
							try {
								sku.setFromDate(sdf.parse("01-07-2016"));
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						
						if(row.getCell(8)!=null && row.getCell(8).getDateCellValue()!=null){
							sku.setToDate(row.getCell(8).getDateCellValue());
						}else{
							sku.setToDate(new Date());
						}
						counter++;
						skus.add(sku);
					}
					
					
				}else{
					break;
				}
			}
			fis.close();
			workbook.close();
			excelToDatabaseService.saveSkuSettings(skus);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}catch(IllegalStateException e){
			System.out.println("##### There is a data type mismatch error in row number: "+rowNum+" & column no "+cellNum);
			e.printStackTrace();
		}catch(NotOfficeXmlFileException e){
			System.out.println("Not a valid xls file!");
		}
		
		if(errorMesssges.toString().isEmpty()){
			return Response.status(200).entity("Success, "+counter+" records processed").build();
		}else{
			return Response.status(201).entity(counter+" records processed with errors").build();
		}
	}

	public void saveSdPlusSalesData(String filePath,String fileType){
		File file=new File(filePath);
		FileInputStream fis;
		XSSFWorkbook workbook;
		String orderState,typeOfReturn="Delivered";
		try {
			fis=new FileInputStream(file);
			workbook=new XSSFWorkbook(fis);
			XSSFSheet sheet=workbook.getSheetAt(0);
			Iterator<Row> rowIterator=sheet.iterator();
			while(rowIterator.hasNext()){
				SdSales sale=new SdSales();
				PhysicalReturns returns=new PhysicalReturns();
				CustomerData customer=new CustomerData();

				Row row=rowIterator.next();
				if(row.getRowNum()>0 && !isRowEmpty(row)){
					if(!row.getCell(ORDER_STATE).getStringCellValue().isEmpty() && 
							!row.getCell(ORDER_STATE).getStringCellValue().trim().equalsIgnoreCase("Order Cancelled")){
						System.out.println(row.getCell(SUBORDER_CODE).getStringCellValue());
						sale.setOrderDate(row.getCell(ORDER_DATE).getDateCellValue());
						sale.setSuborderCode(row.getCell(SUBORDER_CODE).getStringCellValue());
						sale.setOrderCode(row.getCell(ORDER_CODE).getStringCellValue());
						sale.setProductName(row.getCell(PRODUCT_NAME).getStringCellValue());
						sale.setSku(row.getCell(SKU).getStringCellValue());
						sale.setSupc(row.getCell(SUPC).getStringCellValue());
						orderState=row.getCell(ORDER_STATE).getStringCellValue();
						sale.setOrderState(row.getCell(ORDER_STATE).getStringCellValue());
						if(!row.getCell(SELLING_PRICE).getStringCellValue().isEmpty()){
							sale.setSp(Double.parseDouble(row.getCell(SELLING_PRICE).getStringCellValue()));
						}
						sale.setInvoice(row.getCell(INVOICE).getStringCellValue());
						if(!row.getCell(VAT).getStringCellValue().isEmpty()){
							sale.setVat(Double.parseDouble(row.getCell(VAT).getStringCellValue()));
						}

						if(!row.getCell(CST).getStringCellValue().isEmpty()){
							sale.setCst(Double.parseDouble(row.getCell(CST).getStringCellValue()));
						}
						if(fileType.equalsIgnoreCase("SD_DS_SALES")){
							sale.setFulfillmentMode("DS");
						}else{
							sale.setFulfillmentMode("SD+");
						}

						if(!row.getCell(TYPE_OF_RETURN).getStringCellValue().isEmpty()){
							if(row.getCell(TYPE_OF_RETURN).getStringCellValue().trim().equalsIgnoreCase("Courier Return")){
								typeOfReturn="RTO";
							}else if(row.getCell(TYPE_OF_RETURN).getStringCellValue().trim().equalsIgnoreCase("Buyer Return")){
								typeOfReturn="RPU";
							}
							sale.setTypeOfReturn(typeOfReturn);
						}
						sale.setCreated(new Date());
						excelToDatabaseService.saveSdSale(sale);

						if((orderState.trim().equalsIgnoreCase("Return Accepted") || orderState.trim().equalsIgnoreCase("Return Delivered")||
								orderState.trim().equalsIgnoreCase("Return Completed")) 
								&& (typeOfReturn.equalsIgnoreCase("RTO"))
								&& !fileType.equalsIgnoreCase("SD_DS_SALES")){

							returns.setFulfillmentMode("SD+");
							returns.setInvoice(row.getCell(INVOICE).getStringCellValue());
							returns.setOrderDate(row.getCell(ORDER_DATE).getDateCellValue());
							returns.setReturnDate(getCellDateValue(row.getCell(RETURN_DATE)));
							returns.setSku(row.getCell(SKU).getStringCellValue());
							if(!row.getCell(SELLING_PRICE).getStringCellValue().isEmpty()){
								returns.setSp(Double.parseDouble(row.getCell(SELLING_PRICE).getStringCellValue()));
							}
							returns.setSubordercode(row.getCell(SUBORDER_CODE).getStringCellValue());
							returns.setCreated(new Date());
							excelToDatabaseService.savePhysicalReturn(returns);
						}

						if(orderState.trim().equalsIgnoreCase("Return Accepted") || orderState.trim().equalsIgnoreCase("Return Delivered")||
								orderState.trim().equalsIgnoreCase("Return Completed")||orderState.trim().equalsIgnoreCase("Returned")||
								orderState.trim().equalsIgnoreCase("Return Undelivered")||orderState.trim().equalsIgnoreCase("Disputed")||
								orderState.trim().equalsIgnoreCase("Return Initiated")||orderState.trim().equalsIgnoreCase("In Transit")||
								orderState.trim().equalsIgnoreCase("Lost in Transit")){

							SdReturns sdReturns=new SdReturns();
							sdReturns.setOrderDate(row.getCell(ORDER_DATE).getDateCellValue());
							sdReturns.setSuborderCode(row.getCell(SUBORDER_CODE).getStringCellValue());
							sdReturns.setOrderCode(row.getCell(ORDER_CODE).getStringCellValue());
							sdReturns.setProductName(row.getCell(PRODUCT_NAME).getStringCellValue());
							sdReturns.setSku(row.getCell(SKU).getStringCellValue());
							sdReturns.setSupc(row.getCell(SUPC).getStringCellValue());
							orderState=row.getCell(ORDER_STATE).getStringCellValue();
							sdReturns.setOrderState(row.getCell(ORDER_STATE).getStringCellValue());
							if(!row.getCell(SELLING_PRICE).getStringCellValue().isEmpty()){
								sdReturns.setSp(Double.parseDouble(row.getCell(SELLING_PRICE).getStringCellValue()));
							}
							sdReturns.setInvoice(row.getCell(INVOICE).getStringCellValue());
							if(!row.getCell(VAT).getStringCellValue().isEmpty()){
								sdReturns.setVat(Double.parseDouble(row.getCell(VAT).getStringCellValue()));
							}

							if(!row.getCell(CST).getStringCellValue().isEmpty()){
								sdReturns.setCst(Double.parseDouble(row.getCell(CST).getStringCellValue()));
							}

							if(fileType.equalsIgnoreCase("SD_DS_SALES")){
								sale.setFulfillmentMode("DS");
							}else{
								sale.setFulfillmentMode("SD+");
							}

							if(!row.getCell(TYPE_OF_RETURN).getStringCellValue().isEmpty()){
								if(row.getCell(TYPE_OF_RETURN).getStringCellValue().trim().equalsIgnoreCase("Courier Return")){
									typeOfReturn="RTO";
								}else if(row.getCell(TYPE_OF_RETURN).getStringCellValue().trim().equalsIgnoreCase("Buyer Return")){
									typeOfReturn="RPU";
								}
								sdReturns.setTypeOfReturn(typeOfReturn);
							}
							sdReturns.setCreated(new Date());
							excelToDatabaseService.saveSdReturn(sdReturns);

						}
					}

					//Saving customer data irrespective of order state - Fucks Given Zero!
					customer.setName(row.getCell(BUYER_NAME).getStringCellValue());
					customer.setAddress(row.getCell(BUYER_ADDRESS).getStringCellValue());
					customer.setCity(row.getCell(BUYER_CITY).getStringCellValue());
					customer.setState(row.getCell(BUYER_STATE).getStringCellValue());
					customer.setPinCode(row.getCell(BUYER_PIN_CODE).getStringCellValue());
					customer.setChannel("SD+");
					customer.setSku(row.getCell(SKU).getStringCellValue());
					customer.setCreated(new Date());
					excelToDatabaseService.saveCustomerDate(customer);
				}
			}

			fis.close();
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}catch(NotOfficeXmlFileException e){
			System.out.println("Not a valid xls file!");
		}
	}

	public int getWeightSlab(Double weight,String slabType){
		if(slabType.equalsIgnoreCase("V")){
			double division=weight/weightSlabDivider;
			Double ceil= Math.ceil(division);
			return ceil.intValue();
		}else if(slabType.equalsIgnoreCase("W")){
			double division=weight/500.0;
			Double ceil= Math.ceil(division);
			return ceil.intValue();
		}else{
			return 0;
		}		
	}

	public static boolean isRowEmpty(Row row) {
		for (int c = row.getFirstCellNum(); c < row.getLastCellNum(); c++) {
			Cell cell = row.getCell(c);
			if (cell != null && cell.getCellType() != Cell.CELL_TYPE_BLANK)
				return false;
		}
		return true;
	}

	public void saveSdPayments(String filePath){
		File file=new File(filePath);
		FileInputStream fis;
		try {
			fis=new FileInputStream(file);
			XSSFWorkbook workbook=new XSSFWorkbook(fis);
			XSSFSheet sheet=workbook.getSheetAt(0);
			Iterator<Row> rowIterator=sheet.iterator();
			while(rowIterator.hasNext()){
				SdPayments payment=new SdPayments();
				Row row=rowIterator.next();
				if(!isRowEmpty(row) && row.getRowNum()>2){
					payment.setPaymentDate(stringTodate(row.getCell(PAYMENT_DATE).getStringCellValue(), "dd-MMM-yyyy"));
					payment.setOrderDate(stringTodate(row.getCell(PYMNT_ORDER_DATE).getStringCellValue(), "dd-MMM-yyyy"));
					payment.setSuborderCode(row.getCell(PYMNT_SUBORDER_CODE).getStringCellValue());
					payment.setReason(row.getCell(REASON).getStringCellValue());
					payment.setSku(row.getCell(PYMNT_SKU).getStringCellValue());
					payment.setInvoice(row.getCell(PYMNT_INVOICE).getStringCellValue());
					payment.setSp(row.getCell(PYMNT_SELLING_PRICE).getNumericCellValue());
					payment.setSellerFundedCashBack(row.getCell(SELLER_FUNDED_CASHBACK).getNumericCellValue());
					payment.setBenefitPassedToBuyer((row.getCell(BENEFIT_PASSED_TO_BUYER).getNumericCellValue()));
					payment.setPackingCharge((row.getCell(PACKING_CHARGES).getNumericCellValue()));
					payment.setMarketingFees((row.getCell(MARKETING_FEES).getNumericCellValue()));
					payment.setPaymentsCollectionFees((row.getCell(PAYMENT_COLLECTION_FEES).getNumericCellValue()));
					payment.setCourierFees((row.getCell(COURIER_FEES).getNumericCellValue()));
					payment.setReturnPackingFees((row.getCell(RETURNS_PACKAGING_FEES).getNumericCellValue()));
					payment.setReturnPaymentCollectionFees((row.getCell(RETURNS_PAYMENT_COLLECTION_FEES).getNumericCellValue()));
					payment.setRevShipping((row.getCell(REV_SHIPPING_FEES).getNumericCellValue()));
					Double serviceTax=(row.getCell(SERVICE_TAX).getNumericCellValue())
							+(row.getCell(KK_CESS).getNumericCellValue())
							+ (row.getCell(SB_CESS).getNumericCellValue());
					payment.setServiceTax(serviceTax);
					payment.setNetPayable((row.getCell(NET_PAYABLE).getNumericCellValue()));
					payment.setCommission((row.getCell(COMMISSION).getNumericCellValue()));
					payment.setReferenceNo(row.getCell(REF_NO).getStringCellValue());

					String type=row.getCell(2).getStringCellValue();
					if(!type.isEmpty()){
						if(type.trim().equalsIgnoreCase("COD RTS to Vendor")){
							payment.setNature("RPU");
						}else if(type.trim().equalsIgnoreCase("COD Return to Vendor")){
							payment.setNature("RTO");
						}else if(type.trim().equalsIgnoreCase("NCOD RTS to Vendor")){
							payment.setNature("RPU");
						}else if(type.trim().equalsIgnoreCase("NCOD Ret to Vendor")){
							payment.setNature("RTO");
						}else if(type.trim().equalsIgnoreCase("COD Vendor Invoice")){
							payment.setNature("Delivered");
						}else if(type.trim().equalsIgnoreCase("NCOD Vendor Invoice")){
							payment.setNature("Delivered");
						}else if(type.trim().equalsIgnoreCase("NCOD Vendor DM")){
							payment.setNature("Delivered");
						}
					}

					String fulFllmentMode=row.getCell(PYMNT_FULFILLMENT_MODE).getStringCellValue();
					if(!fulFllmentMode.isEmpty()){
						if(fulFllmentMode.equalsIgnoreCase("FC_VOI")){
							payment.setFulfillmentMode("SD+");
						}else{
							payment.setFulfillmentMode("DS");
						}
					}

					excelToDatabaseService.saveSdPayment(payment);
				}
			}

			fis.close();
			workbook.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(NotOfficeXmlFileException e){
			System.out.println("Not a valid xls file!");
		}
	}

	public Double checkEmptyAndGiveDouble(String value){
		if(!value.isEmpty()){
			return Double.parseDouble(value);
		}else{
			return 0.0;
		}
	}

	public Date stringTodate(String dateString,String format){
		if(!dateString.isEmpty() && !format.isEmpty()){
			SimpleDateFormat sdf=new SimpleDateFormat(format);
			try {
				Date date=sdf.parse(dateString);
				return date;
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return null;
	}

	public void saveUniSales(String filePath){
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		try {

			br = new BufferedReader(new FileReader(filePath));
			while ((line = br.readLine()) != null) {

				// use comma as separator
				String[] country = line.split(cvsSplitBy);

				System.out.println("Country [code= " + country[4] + " , name=" + country[5] + "]");

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	
	public void saveUniReturns(String filePath){
		File file=new File(filePath);
		FileInputStream fis;
		XSSFWorkbook workbook;
		DataFormatter formatter=new DataFormatter();
		try {
			fis=new FileInputStream(file);
			workbook=new XSSFWorkbook(fis);
			XSSFSheet sheet=workbook.getSheetAt(0);
			Iterator<Row> rowIterator=sheet.iterator();
			
			while(rowIterator.hasNext()){
				Row row=rowIterator.next();
				if(row.getRowNum()>0){
					String channel=row.getCell(UNI_RET_CHANNEL).getStringCellValue();
					if(channel.equalsIgnoreCase("SNAPDEAL_VENDORSKART")||channel.equalsIgnoreCase("SNAPDEAL_VENDORSKART_DROPSHIP")||channel.equalsIgnoreCase("SNAPDEAL_PLUS")){
						PhysicalReturns physicalReturn=new PhysicalReturns();
						if(row.getCell(UNI_RET_RETURN_DATE).getCellType()==Cell.CELL_TYPE_STRING){
							physicalReturn.setReturnDate(parseDateForSure(row.getCell(UNI_RET_RETURN_DATE).getStringCellValue(),0));
						}else if(row.getCell(UNI_RET_RETURN_DATE).getCellType()==Cell.CELL_TYPE_NUMERIC){
							physicalReturn.setReturnDate(row.getCell(UNI_RET_RETURN_DATE).getDateCellValue());
						}
						physicalReturn.setCreated(new Date());
						physicalReturn.setFulfillmentMode("DS");
						if(channel.equalsIgnoreCase("SNAPDEAL_PLUS")){
							physicalReturn.setFulfillmentMode("SD+");
						}
						if(!row.getCell(UNI_RET_INVOICE).getStringCellValue().isEmpty()){
							if(row.getCell(UNI_RET_INVOICE).getStringCellValue().contains(":")){
								String parts[]=row.getCell(UNI_RET_INVOICE).getStringCellValue().split(":");
								physicalReturn.setInvoice(parts[1].trim());
							}else{
								physicalReturn.setInvoice(row.getCell(UNI_RET_INVOICE).getStringCellValue());
							}
						}
						if(row.getCell(UNI_RET_ORDER_DATE).getCellType()==Cell.CELL_TYPE_STRING){
							physicalReturn.setOrderDate(parseDateForSure(row.getCell(UNI_RET_ORDER_DATE).getStringCellValue(),0));
						}else if(row.getCell(UNI_RET_ORDER_DATE).getCellType()==Cell.CELL_TYPE_NUMERIC){
							physicalReturn.setOrderDate(row.getCell(UNI_RET_ORDER_DATE).getDateCellValue());
						}
						
						physicalReturn.setSku(row.getCell(UNI_RET_SKU).getStringCellValue());
						physicalReturn.setSp(row.getCell(UNI_RET_SP).getNumericCellValue());
						
						physicalReturn.setSubordercode(formatter.formatCellValue(row.getCell(UNI_RET_SALE_ORDER_CODE)));
						excelToDatabaseService.savePhysicalReturn(physicalReturn);
					}
				}
			}
			fis.close();
			workbook.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(NotOfficeXmlFileException e){
			System.out.println("Not a valid xls file!");
		}
	}
	
	public void savePurchaseOrders(String filePath){
		try(ICsvBeanReader beanReader = new CsvBeanReader(new FileReader(filePath), CsvPreference.STANDARD_PREFERENCE))
        {
            //First column is header
            beanReader.getHeader(true);
            final CellProcessor[] processors = CellProcessorUtil.getPurchaseOrdersProcessors();
 
            PurchaseOrders purchaseOrders;
            while ((purchaseOrders = beanReader.read(PurchaseOrders.class, CellProcessorUtil.getPurchaseOrdersHeader(),CellProcessorUtil.getPurchaseOrdersProcessors())) != null) {
                if(purchaseOrders.getStatus().equalsIgnoreCase("COMPLETE")){
            	System.out.println(purchaseOrders.getSku());
                excelToDatabaseService.savePurchaseOrder(purchaseOrders);
                }
            }
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

}
