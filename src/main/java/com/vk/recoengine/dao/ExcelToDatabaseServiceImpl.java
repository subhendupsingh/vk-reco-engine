package com.vk.recoengine.dao;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vk.recoengine.entities.CustomerData;
import com.vk.recoengine.entities.PhysicalReturns;
import com.vk.recoengine.entities.ProductCategories;
import com.vk.recoengine.entities.PurchaseOrders;
import com.vk.recoengine.entities.SdPayments;
import com.vk.recoengine.entities.SdReturns;
import com.vk.recoengine.entities.SdSales;
import com.vk.recoengine.entities.SkuSettings;
import com.vk.recoengine.service.ExcelToDatabase;

@Service("excelToDatabaseService")
@Transactional
public class ExcelToDatabaseServiceImpl extends BaseServiceImpl implements ExcelToDatabase{

	public void saveSkuSettings(List<SkuSettings> skus) {
		if(skus!=null && skus.size()>0){
			for(SkuSettings sku:skus){
				entityManager.persist(sku);
			}
		}
	}

	public ProductCategories getProductCategoryById(int id) {
		return entityManager.find(ProductCategories.class, id);
	}

	public void savePhysicalReturn(PhysicalReturns returns) {
		entityManager.persist(returns);
	}

	public void saveSdSale(SdSales sale) {
		entityManager.persist(sale);
	}

	public void saveCustomerDate(CustomerData customer) {
		entityManager.persist(customer);
	}

	public void saveSdPayment(SdPayments payment) {
		entityManager.persist(payment);
	}

	public void saveSdReturn(SdReturns sdReturn) {
		entityManager.persist(sdReturn);
		
	}

	@Override
	public void savePurchaseOrder(PurchaseOrders po) {
		entityManager.persist(po);		
	}
	

}
