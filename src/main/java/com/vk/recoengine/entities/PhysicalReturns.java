package com.vk.recoengine.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PhysicalReturns {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String subordercode;
	private Date orderDate;
	private Date returnDate;
	private String sku;
	private Double sp;
	private String invoice;
	private String fulfillmentMode;
	private Date created;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getSubordercode() {
		return subordercode;
	}
	public void setSubordercode(String subordercode) {
		this.subordercode = subordercode;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public Date getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
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
	public String getFulfillmentMode() {
		return fulfillmentMode;
	}
	public void setFulfillmentMode(String fulfillmentMode) {
		this.fulfillmentMode = fulfillmentMode;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	
	
}
