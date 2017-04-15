package com.vk.recoengine.service;

import java.util.List;

import com.vk.recoengine.entities.CustomerData;
import com.vk.recoengine.entities.PhysicalReturns;
import com.vk.recoengine.entities.ProductCategories;
import com.vk.recoengine.entities.PurchaseOrders;
import com.vk.recoengine.entities.SdPayments;
import com.vk.recoengine.entities.SdReturns;
import com.vk.recoengine.entities.SdSales;
import com.vk.recoengine.entities.SkuSettings;


public interface ExcelToDatabase {
	
	public void saveSkuSettings(List<SkuSettings> skus);
	public ProductCategories getProductCategoryById(int id);
	public void savePhysicalReturn(PhysicalReturns returns);
	public void saveSdSale(SdSales sale);
	public void saveCustomerDate(CustomerData customer);
	public void saveSdPayment(SdPayments payment);
	public void saveSdReturn(SdReturns sdReturn);
	public void savePurchaseOrder(PurchaseOrders po);
}
