package com.vk.recoengine.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SdReturns implements Serializable{

	private static final long serialVersionUID = -111027497049895410L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private Date orderDate;
	private String suborderCode;
	private String orderCode;
	private String productName;
	private String sku;
	private String supc;
	private String orderState;
	private Double sp;
	private String invoice;
	private Double vat;
	private Double cst;
	private String fulfillmentMode;
	private String typeOfReturn;
	private Date created;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	
	public String getSuborderCode() {
		return suborderCode;
	}
	public void setSuborderCode(String suborderCode) {
		this.suborderCode = suborderCode;
	}
	public String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public String getSupc() {
		return supc;
	}
	public void setSupc(String supc) {
		this.supc = supc;
	}
	public String getOrderState() {
		return orderState;
	}
	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}
	public Double getSp() {
		return sp;
	}
	public void setSp(Double sp) {
		this.sp = sp;
	}
	public String getInvoice() {
		return invoice;
	}
	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}
	public Double getVat() {
		return vat;
	}
	public void setVat(Double vat) {
		this.vat = vat;
	}
	public Double getCst() {
		return cst;
	}
	public void setCst(Double cst) {
		this.cst = cst;
	}
	public String getFulfillmentMode() {
		return fulfillmentMode;
	}
	public void setFulfillmentMode(String fulfillmentMode) {
		this.fulfillmentMode = fulfillmentMode;
	}
	public String getTypeOfReturn() {
		return typeOfReturn;
	}
	public void setTypeOfReturn(String typeOfReturn) {
		this.typeOfReturn = typeOfReturn;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	
}
