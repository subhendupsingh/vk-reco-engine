package com.vk.recoengine.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SdPayments implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5429876344725134384L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private Date paymentDate;
	private String suborderCode;
	private String reason;
	private String sku;
	private String invoice;
	private Double sp;
	private Double sellerFundedCashBack;
	private Double benefitPassedToBuyer;
	private Double packingCharge;
	private Double marketingFees;
	private Double paymentsCollectionFees;
	private Double courierFees;
	private Double returnPackingFees;
	private Double returnPaymentCollectionFees;
	private Double revShipping;
	private Date orderDate;
	private Double serviceTax;
	private Double commission;
	private Double netPayable;
	private String nature;
	private String referenceNo;
	private String fulfillmentMode;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
	public String getSuborderCode() {
		return suborderCode;
	}
	public void setSuborderCode(String suborderCode) {
		this.suborderCode = suborderCode;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public String getInvoice() {
		return invoice;
	}
	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}
	public Double getSp() {
		return sp;
	}
	public void setSp(Double sp) {
		this.sp = sp;
	}
	public Double getSellerFundedCashBack() {
		return sellerFundedCashBack;
	}
	public void setSellerFundedCashBack(Double sellerFundedCashBack) {
		this.sellerFundedCashBack = sellerFundedCashBack;
	}
	public Double getBenefitPassedToBuyer() {
		return benefitPassedToBuyer;
	}
	public void setBenefitPassedToBuyer(Double benefitPassedToBuyer) {
		this.benefitPassedToBuyer = benefitPassedToBuyer;
	}
	public Double getPackingCharge() {
		return packingCharge;
	}
	public void setPackingCharge(Double packingCharge) {
		this.packingCharge = packingCharge;
	}
	public Double getMarketingFees() {
		return marketingFees;
	}
	public void setMarketingFees(Double marketingFees) {
		this.marketingFees = marketingFees;
	}
	public Double getPaymentsCollectionFees() {
		return paymentsCollectionFees;
	}
	public void setPaymentsCollectionFees(Double paymentsCollectionFees) {
		this.paymentsCollectionFees = paymentsCollectionFees;
	}
	public Double getCourierFees() {
		return courierFees;
	}
	public void setCourierFees(Double courierFees) {
		this.courierFees = courierFees;
	}
	public Double getReturnPackingFees() {
		return returnPackingFees;
	}
	public void setReturnPackingFees(Double returnPackingFees) {
		this.returnPackingFees = returnPackingFees;
	}
	public Double getReturnPaymentCollectionFees() {
		return returnPaymentCollectionFees;
	}
	public void setReturnPaymentCollectionFees(Double returnPaymentCollectionFees) {
		this.returnPaymentCollectionFees = returnPaymentCollectionFees;
	}
	public Double getRevShipping() {
		return revShipping;
	}
	public void setRevShipping(Double revShipping) {
		this.revShipping = revShipping;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public Double getServiceTax() {
		return serviceTax;
	}
	public void setServiceTax(Double serviceTax) {
		this.serviceTax = serviceTax;
	}
	public Double getCommission() {
		return commission;
	}
	public void setCommission(Double commission) {
		this.commission = commission;
	}
	public String getNature() {
		return nature;
	}
	public void setNature(String nature) {
		this.nature = nature;
	}
	public String getReferenceNo() {
		return referenceNo;
	}
	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}
	public Double getNetPayable() {
		return netPayable;
	}
	public void setNetPayable(Double netPayable) {
		this.netPayable = netPayable;
	}
	public String getFulfillmentMode() {
		return fulfillmentMode;
	}
	public void setFulfillmentMode(String fulfillmentMode) {
		this.fulfillmentMode = fulfillmentMode;
	}
	
	
}
