package com.vk.recoengine.dto;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class PO {
	private String poCode;
	private Date poCreationDate;
	private String type;
	private String poCreatedBy;
	private Date poApprovedDate;
	private Date deliveryDate;
	private String itemName;
	private String sku;
	private String category;
	private String vendor;
	private String vendorCode;
	private String vendorSkuCode;
	private int orderedQuantity;
	private int receivedQty;
	private int rejectedQty;
	private int pendingQuantity;
	private int ageingDays;
	private Double percentPending;
	private Double percentRejection;
	private String facility;
	private Double unitPrice;
	private Double subTotal;
	private Double vat;
	private Double cst;
	private Double total;
	private String status;
	private Date updated;
	
	public PO(){}
	
	
	
	public PO(String poCode, Date poCreationDate,
			String type, String poCreatedBy, Date poApprovedDate,
			Date deliveryDate, String itemName, String sku, String category,
			String vendor, String vendorCode, String vendorSkuCode,
			int orderedQuantity, int receivedQty, int rejectedQty,
			int pendingQuantity, int ageingDays, Double percentPending,
			Double percentRejection, String facility, Double unitPrice,
			Double subTotal, Double vat, Double cst, Double total,
			String status, Date updated) {
		super();
		this.poCode = poCode;
		this.poCreationDate = poCreationDate;
		this.type = type;
		this.poCreatedBy = poCreatedBy;
		this.poApprovedDate = poApprovedDate;
		this.deliveryDate = deliveryDate;
		this.itemName = itemName;
		this.sku = sku;
		this.category = category;
		this.vendor = vendor;
		this.vendorCode = vendorCode;
		this.vendorSkuCode = vendorSkuCode;
		this.orderedQuantity = orderedQuantity;
		this.receivedQty = receivedQty;
		this.rejectedQty = rejectedQty;
		this.pendingQuantity = pendingQuantity;
		this.ageingDays = ageingDays;
		this.percentPending = percentPending;
		this.percentRejection = percentRejection;
		this.facility = facility;
		this.unitPrice = unitPrice;
		this.subTotal = subTotal;
		this.vat = vat;
		this.cst = cst;
		this.total = total;
		this.status = status;
		this.updated = updated;
	}


	public String getType() {
		return type;
	}



	public void setType(String type) {
		this.type = type;
	}



	public String getPoCreatedBy() {
		return poCreatedBy;
	}



	public void setPoCreatedBy(String poCreatedBy) {
		this.poCreatedBy = poCreatedBy;
	}



	public Date getPoApprovedDate() {
		return poApprovedDate;
	}



	public void setPoApprovedDate(Date poApprovedDate) {
		this.poApprovedDate = poApprovedDate;
	}



	public Date getDeliveryDate() {
		return deliveryDate;
	}



	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}



	public String getItemName() {
		return itemName;
	}



	public void setItemName(String itemName) {
		this.itemName = itemName;
	}



	public String getCategory() {
		return category;
	}



	public void setCategory(String category) {
		this.category = category;
	}



	public String getVendorCode() {
		return vendorCode;
	}



	public void setVendorCode(String vendorCode) {
		this.vendorCode = vendorCode;
	}



	public String getVendorSkuCode() {
		return vendorSkuCode;
	}



	public void setVendorSkuCode(String vendorSkuCode) {
		this.vendorSkuCode = vendorSkuCode;
	}



	public int getOrderedQuantity() {
		return orderedQuantity;
	}



	public void setOrderedQuantity(int orderedQuantity) {
		this.orderedQuantity = orderedQuantity;
	}



	public int getRejectedQty() {
		return rejectedQty;
	}



	public void setRejectedQty(int rejectedQty) {
		this.rejectedQty = rejectedQty;
	}



	public int getPendingQuantity() {
		return pendingQuantity;
	}



	public void setPendingQuantity(int pendingQuantity) {
		this.pendingQuantity = pendingQuantity;
	}



	public int getAgeingDays() {
		return ageingDays;
	}



	public void setAgeingDays(int ageingDays) {
		this.ageingDays = ageingDays;
	}



	public Double getPercentPending() {
		return percentPending;
	}



	public void setPercentPending(Double percentPending) {
		this.percentPending = percentPending;
	}



	public Double getPercentRejection() {
		return percentRejection;
	}



	public void setPercentRejection(Double percentRejection) {
		this.percentRejection = percentRejection;
	}



	public String getFacility() {
		return facility;
	}



	public void setFacility(String facility) {
		this.facility = facility;
	}



	public Double getCst() {
		return cst;
	}



	public void setCst(Double cst) {
		this.cst = cst;
	}



	public Date getUpdated() {
		return updated;
	}



	public void setUpdated(Date updated) {
		this.updated = updated;
	}



	public String getPoCode() {
		return poCode;
	}
	public void setPoCode(String poCode) {
		this.poCode = poCode;
	}
	public Date getPoCreationDate() {
		return poCreationDate;
	}
	public void setPoCreationDate(Date poCreationDate) {
		this.poCreationDate = poCreationDate;
	}
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public String getVendor() {
		return vendor;
	}
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
	
	public int getReceivedQty() {
		return receivedQty;
	}

	public void setReceivedQty(int receivedQty) {
		this.receivedQty = receivedQty;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public Double getSubTotal() {
		return subTotal;
	}
	public void setSubTotal(Double subTotal) {
		this.subTotal = subTotal;
	}
	public Double getVat() {
		return vat;
	}
	public void setVat(Double vat) {
		this.vat = vat;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}



	@Override
	public String toString() {
		return "PurchaseOrders [poCode=" + poCode
				+ ", poCreationDate=" + poCreationDate + ", type=" + type
				+ ", poCreatedBy=" + poCreatedBy + ", poApprovedDate="
				+ poApprovedDate + ", deliveryDate=" + deliveryDate
				+ ", itemName=" + itemName + ", sku=" + sku + ", category="
				+ category + ", vendor=" + vendor + ", vendorCode="
				+ vendorCode + ", vendorSkuCode=" + vendorSkuCode
				+ ", orderedQuantity=" + orderedQuantity + ", receivedQty="
				+ receivedQty + ", rejectedQty=" + rejectedQty
				+ ", pendingQuantity=" + pendingQuantity + ", ageingDays="
				+ ageingDays + ", percentPending=" + percentPending
				+ ", percentRejection=" + percentRejection + ", facility="
				+ facility + ", unitPrice=" + unitPrice + ", subTotal="
				+ subTotal + ", vat=" + vat + ", cst=" + cst + ", total="
				+ total + ", status=" + status + ", updated=" + updated + "]";
	}

	
	
	
}
