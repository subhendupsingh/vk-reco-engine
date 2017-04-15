package com.vk.recoengine.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.codehaus.jackson.annotate.JsonBackReference;

@Entity
public class SdRules implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6025680382755740729L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String status;
	private String shipping;
	private String revShipping;
	private String paymentCollectionFees;
	private String packingFees;
	private String marketingFees;
	@ManyToOne
	@JoinColumn(name="sd_portal_id")
	@JsonBackReference
	private SnapdealPortal sdPortal;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getShipping() {
		return shipping;
	}
	public void setShipping(String shipping) {
		this.shipping = shipping;
	}
	public String getRevShipping() {
		return revShipping;
	}
	public void setRevShipping(String revShipping) {
		this.revShipping = revShipping;
	}
	public String getPaymentCollectionFees() {
		return paymentCollectionFees;
	}
	public void setPaymentCollectionFees(String paymentCollectionFees) {
		this.paymentCollectionFees = paymentCollectionFees;
	}
	public String getPackingFees() {
		return packingFees;
	}
	public void setPackingFees(String packingFees) {
		this.packingFees = packingFees;
	}
	public String getMarketingFees() {
		return marketingFees;
	}
	public void setMarketingFees(String marketingFees) {
		this.marketingFees = marketingFees;
	}
	public SnapdealPortal getSdPortal() {
		return sdPortal;
	}
	public void setSdPortal(SnapdealPortal sdPortal) {
		this.sdPortal = sdPortal;
	}
	
	
}
