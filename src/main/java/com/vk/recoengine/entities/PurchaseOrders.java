package com.vk.recoengine.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PurchaseOrders {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String poCode;
	private Date poCreationDate;
	private String sku;
	private String vendor;
	private int receivedQty;
	private Double unitPrice;
	private Double subTotal;
	private Double vat;
	private Double total;
	private String status;
	
	public PurchaseOrders(){}
	
	public PurchaseOrders(int id, String poCode, Date poCreationDate,
			String sku, String vendor, int receivedQty, Double unitPrice,
			Double subTotal, Double vat, Double total, String status) {
		super();
		this.id = id;
		this.poCode = poCode;
		this.poCreationDate = poCreationDate;
		this.sku = sku;
		this.vendor = vendor;
		this.receivedQty = receivedQty;
		this.unitPrice = unitPrice;
		this.subTotal = subTotal;
		this.vat = vat;
		this.total = total;
		this.status = status;
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
		return "PurchaseOrders [id=" + id + ", poCode=" + poCode
				+ ", poCreationDate=" + poCreationDate + ", sku=" + sku
				+ ", vendor=" + vendor + ", receivedQty=" + receivedQty
				+ ", unitPrice=" + unitPrice + ", subTotal=" + subTotal
				+ ", vat=" + vat + ", total=" + total + ", status=" + status
				+ "]";
	}
	
	
}
