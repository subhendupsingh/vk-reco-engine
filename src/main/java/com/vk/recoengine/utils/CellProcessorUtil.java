package com.vk.recoengine.utils;


import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ParseDate;
import org.supercsv.cellprocessor.ParseDouble;
import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;

public class CellProcessorUtil {

	 public static CellProcessor[] getPurchaseOrdersProcessors() {
	        final CellProcessor[] processors = new CellProcessor[] {
	                new NotNull(), // poCode
	                new NotNull(new ParseDate("dd/MM/yyyy HH:mm",true)), // poCreationDate
	                null,
	                null,
	                null,
	                null,
	                null,
	                new NotNull(), // sku
	                null,
	                new Optional(), // vendor
	                null,
	                null,
	                null,
	                new NotNull(new ParseInt()), // receivedQty,
	                null,
	                null,
	                null,
	                null,
	                null,
	                null,
	                new NotNull(new ParseDouble()), //unitPrice
	        		new Optional(new ParseDouble()), // subTotal
	        		new NotNull(new ParseDouble()), // vat
	        		null,
	        		new NotNull(new ParseDouble()), // total
	        		new Optional(), // status,
	        		null
	        };
	        return processors;
	    }
	 
	 public static String[] getPurchaseOrdersHeader(){
		 return new String[]{"poCode","poCreationDate",null,null,null,null,null,"sku",null,"vendor",null,null,null,"receivedQty",null,null,null,null,null,null,"unitPrice","subTotal","vat",null,"total","status",null};
	 }
}
